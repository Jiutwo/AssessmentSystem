package cn.jiutwo.controller.login.servlet;


import cn.jiutwo.pojo.Assessment;
import cn.jiutwo.pojo.Envelope;
import cn.jiutwo.pojo.PageBean;
import cn.jiutwo.pojo.User;
import cn.jiutwo.service.login.UserService;
import cn.jiutwo.service.login.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RequestMapping("/envelope")
@Controller
public class EnvelopeServlet extends BaseServlet {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    /**
     * 条件查询收到的信
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findReEnvelope")
    public void findReEnvelope(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.判断是学生还是教师
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        Assessment assessment = (Assessment) session.getAttribute("assessmentSession");
        List<Envelope> pb = null;
        //查询收到的信
        if (user != null) {
            //2.1.是学生，调用service查询
            System.out.println("我是学生");
            pb = userService.findReEnvelope(user.getSno());
        } else if (assessment != null) {
            //2.2.是教师，调用service查询
            System.out.println("我是老师");
            pb = userService.findReEnvelope(assessment.getSno());
        }
        //3.将PageBean存入request
        request.setAttribute("pb", pb);
        System.out.println(pb);
        //4.转发到receiveEnvelope.jsp
        request.getRequestDispatcher(request.getContextPath() + "../statics/page/receiveEnvelop.jsp").forward(request, response);

    }

    /**
     * 条件查询发送的信
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findSeEnvelope")
    public void findSeEnvelope(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.判断是学生还是教师
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        Assessment assessment = (Assessment) session.getAttribute("assessmentSession");
        List<Envelope> pb = null;
        //查询发出的信
        if (user != null) {
            //2.1.是学生，调用service查询
            pb = userService.findSendEnvelope(user.getSno());
        } else if (assessment != null) {
            //2.2.是教师，调用service查询
            pb = userService.findSendEnvelope(assessment.getSno());
        }
        //3.将PageBean存入request
        request.setAttribute("pb", pb);
        //4.转发到receiveEnvelope.jsp
        request.getRequestDispatcher(request.getContextPath() + "../statics/page/sendEnvelop.jsp").forward(request, response);

    }

    /**
     * 根据eid删除信,跳转回receiveEnvelope.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delEnvelope")
    public void delEnvelope(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取eid
        String eid = request.getParameter("eid");
        //2.调用service删除
        userService.delEnvelopeById(eid);
        //删除收到的信
        //3.跳转到查询所有receiveEnvelope.jsp
//        request.getRequestDispatcher(request.getContextPath() + "../statics/page/receiveEnvelop.jsp").forward(request, response);
        writeValue(true,response);
    }

    /**
     * 根据eid查询信的内容
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findContent")
    public void findContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取eid
        String eid = request.getParameter("eid");
        //2.调用service查询
        Envelope envelope = userService.findContentById(eid);
        //3.将envelope对象序列化为json,将json数据写回客户端
        writeValue(envelope, response);
    }

    /**
     * 写信,保存入数据库
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/writeEnvelope")
    public void writeEnvelope(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        Assessment assessment = (Assessment) session.getAttribute("assessmentSession");
        //判断上传的表单是普通表单还是带文件的表单，是返回true,否返回false；
        if (!ServletFileUpload.isMultipartContent(request)) {
            request.setAttribute("flag", false);//发送失败给与回馈
            request.getRequestDispatcher(request.getContextPath() + "../statics/page/writeEnvelope.jsp").forward(request, response);
        }//如果通过了这个if，说明我们的表单是带文件上传的

        //创建上传文件的保存目录，为了安全建议在WEB-INF目录下，用户无法访问
        String uploadpath ="D://upload";//获取上传文件的保存路径
        File uploadfile = new File(uploadpath);
        if (!uploadfile.exists()) {
            uploadfile.mkdir();//如果目录不存在就创建这样一个目录
        }

        /*//临时文件
        //临时路径，如果上传的文件超过预期的大小，我们将它存放到一个临时目录中，过几天自动删除，或者提醒用户转存为永久
        String tmppath = this.getServletContext().getRealPath("WEB-INF/tmp");
        File file = new File(tmppath);
        if (!file.exists()){
            file.mkdir();//如果目录不存在就创建这样临时目录
        }*/
        //处理上传的文件一般需要通过流来获取，我们可以通过request.getInputstream(),原生态文件上传流获取，十分麻烦
        //但是我们都建议使用Apache的文件上传组件来实现，common-fileupload,它需要依赖于common-io组件；

        try {
            //1、创建DiskFileItemFactory对象，处理文件上传路径或限制文件大小
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、获取ServletFileUpload
            ServletFileUpload upload = new ServletFileUpload(factory);
            //3、处理上传文件
            List<FileItem> fileItems = upload.parseRequest(request);
            Envelope envelope = new Envelope();
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) { //判断是普通表单还是带文件的表单
                    //getFieldName指的是前端表单控件的name
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");//处理乱码
                    System.out.println(name + ":" + value);
                    //设置收件人和邮件内容
                    if (name.equals("addresseeSno")){
                        envelope.setAddresseeSno(value);
                    }else if(name.equals("content")){
                        envelope.setContent(value);
                        System.out.println(envelope);

                    }
                } else {//判断它是带文件的表单
                    //======================处理文件=======================//

                    //拿到文件的名字
                    String uploadFileName = fileItem.getName();
                    System.out.println("上传的文件名：" + uploadFileName);

                    if (uploadFileName.trim().equals("") || uploadFileName == null) {
                        continue;
                    }

                    //获得上传的文件名，例如/img/girl/ooa.jpg,只需要ooa，其前面的后面的都不需要
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                    //获得文件的后缀名
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    /*
                     * 如果后缀名 fileExtName 不是我们需要的
                     *就直接return，不处理，告诉用户类型不对
                     * */
                    System.out.println("文件信息【文件名：" + fileName + "文件类型：" + fileExtName + "】");

                    //可以使用UUID(唯一通用识别码)来保证文件名的统一
                    String uuidFileName = UUID.randomUUID().toString();
                    String realPath = uploadpath + "/" + uuidFileName;

                    //=======================传输文件=========================//
                    //获得文件上传的流
                    InputStream inputStream = fileItem.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(realPath + "." + fileExtName);
                    //创建一个缓冲区
                    byte[] buffer = new byte[1024 * 1024];

                    //判断是否读取完毕
                    int len = 0;

                    //如果大于0，说明还存在数据
                    while ((len = inputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    String content = readFileContent(realPath + "." + fileExtName);
                    //设置内容
                    envelope.setContent(content);

                    //关闭流
                    fos.close();
                    inputStream.close();

                    System.out.println("文件上传成功！");
                    fileItem.delete();//上传成功，清除临时文件
                }
            }
            //设置发件人
            //判断是教师还是学生写信
            if (user != null) {
                envelope.setAddresserSno(user.getSno());
            } else if (assessment != null) {
                envelope.setAddresserSno(assessment.getSno());
            }
            System.out.println(envelope.toString());
            userService.addEnvelope(envelope);      //添加至信箱，即发信
            request.setAttribute("flag", true);//发送成功给与回馈
            request.getRequestDispatcher(request.getContextPath() + "../statics/page/writeEnvelope.jsp").forward(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }


}

