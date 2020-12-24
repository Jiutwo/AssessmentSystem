package cn.jiutwo.controller.login.servlet;


import cn.jiutwo.pojo.Assessment;
import cn.jiutwo.pojo.ResultInfo;
import cn.jiutwo.pojo.School;
import cn.jiutwo.pojo.User;
import cn.jiutwo.service.login.AssessmentService;
import cn.jiutwo.service.login.SchoolService;
import cn.jiutwo.service.login.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class loginServlet extends BaseServlet {
    @Autowired
    @Qualifier("SchoolServiceImpl")
    private SchoolService schoolService;
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("AssessmentServiceImpl")
    private AssessmentService assessmentService;

    /**
     * 点击"我是学生"后去除有关教师码的信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/removeSchoolSession")
    public String removeSchoolSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取值
        HttpSession session = request.getSession();
        //2.去除schoolSession
        session.removeAttribute("schoolSession");
        return "login";
    }

    /**
     * 点击"我是老师"后查询相应教师码是否正确，正确便存储shoolSession
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findSchoolCode")
    public String findSchoolCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取值
        String schoolCode = request.getParameter("teacherCode");
        //2.调用service查询
        School school = schoolService.findSchoolCode(schoolCode);
        //3.判断school是否为空
        HttpSession session = request.getSession();
        //4.响应数据
        if (school != null) {
            session.setAttribute("schoolSession", school);
            writeValue("OK", response);
        } else {

            writeValue("ON", response);
        }
        return "login";
    }

    /**
     * 查询学号是否已注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findSno")
    public String findSno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取值
        String sno = request.getParameter("sno");
        //2.调用service查询
        User user = userService.findSno(sno);
        Assessment assessment = assessmentService.findSno(sno);
        //响应数据
        if (user != null || assessment != null) {
            writeValue("ON", response);
        } else {
            writeValue("OK", response);
        }
        return "login";
    }

    @RequestMapping("/remvoeSession")
        public String remove(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("userSession");
            session.removeAttribute("assessmentSession");

            return "redirect:/index.jsp";
    }

    /**
     * 查找学校表并返回json数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findProvince")
    public String findProvince(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.判断是否有schoolSession
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("schoolSession");
        if (school != null) {
            //1.1响应数据
            writeValue(school, response);
        } else {
            //2.调用service查询
            List<String> strings = schoolService.findProvince();
            for (String string : strings) {
                System.out.println(string);
            }
            //2.响应数据
            writeValue(strings, response);
        }
        return "login";
    }

    /**
     * 根据Province查找所在省学校名字
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findSName")
    public String findSName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.判断是否有schoolSession
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("schoolSession");
        if (school != null) {
            //1.1响应数据
            writeValue(school, response);
        } else {
            //1.2.获取值
            String province = request.getParameter("province");
            //2.调用service查询
            List<String> strings = schoolService.findSName(province);
            for (String string : strings) {
            }
            //3.响应数据
            writeValue(strings, response);
        }
        return "login";
    }

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/regist")
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.判断是学生还是老师,再封装数据
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("schoolSession");
        ResultInfo info = new ResultInfo();
        if (school != null) {
            //3.1老师注册
            Assessment assessment = new Assessment();
            try {
                BeanUtils.populate(assessment, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            boolean flag1 = userService.findEmail(assessment.getEmail());
            boolean flag2 = assessmentService.findEmail(assessment.getEmail());
            if (false) {//!flag1 || !flag2
                //邮箱被注册
                info.setFlag(false);
                info.setErrorMsg("邮箱已被注册！");
            } else {
                assessment.setSchoolId((school.getSchoolId()));
                //校验assessment里数据是否满足格式
                if (!snoReg(assessment.getSno())) {
                    //4.1sno格式错误
                    info.setFlag(false);
                    info.setErrorMsg("学号格式有误！");
                }
//        else if (!uNameReg(assessment.getUsername())) {
//            //4.2username格式错误，跳转注册页面提示
//            info.setFlag(false);
//            info.setErrorMsg("姓名格式有误！");
//
//        }
                else if (!pWordReg(assessment.getPassword())) {
                    //username格式错误，跳转注册页面提示
                    info.setFlag(false);
                    info.setErrorMsg("密码格式有误！");

                } else if (!emailReg(assessment.getEmail())) {
                    //username格式错误，跳转注册页面提示
                    info.setFlag(false);
                    info.setErrorMsg("邮箱格式有误！");
                } else {
                    //所有格式都正确，调用userService完成注册
                    assessmentService.regist(assessment);
                    info.setFlag(true);
                }
            }

        } else {
            //3.2学生注册
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            boolean flag1 = userService.findEmail(user.getEmail());
            boolean flag2 = assessmentService.findEmail(user.getEmail());
            //判断邮箱是否已被注册
            if (flag1 || flag2) {
                //邮箱被注册

                info.setFlag(false);
                info.setErrorMsg("邮箱已被注册！");
                System.out.println("邮箱已被注册！");
            } else {
                //邮箱没有被注册
                    String province = request.getParameter("province");
                    String schoolName = request.getParameter("schoolName");
                    String schoolId = schoolService.findSchoolId(province, schoolName);
                    user.setSchoolId(schoolId);
                //校验user里数据是否满足格式
                if (!snoReg(user.getSno())) {
                    //sno格式错误
                    info.setFlag(false);
                    info.setErrorMsg("学号格式有误！");
                }
//        else if (!uNameReg(user.getUsername())) {
//            //username格式错误，跳转注册页面提示
//            info.setFlag(false);
//            info.setErrorMsg("姓名格式有误！");
//
//        }
                else if (!pWordReg(user.getPassword())) {
                    //username格式错误，跳转注册页面提示
                    info.setFlag(false);
                    info.setErrorMsg("密码格式有误！");

                } else if (!emailReg(user.getEmail())) {
                    //4.4username格式错误，跳转注册页面提示
                    info.setFlag(false);
                    info.setErrorMsg("邮箱格式有误！");

                } else {
                    //所有格式都正确，调用userService完成注册
                    userService.regist(user);
                    info.setFlag(true);
                }
            }
        }
        //4.将info对象序列化为json,将json数据写回客户端
        writeValue(info, response);

        return "login";
    }

    /**
     * 设置激活状态
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/setStatus")
    public String setStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String emailCode = request.getParameter("emailCode");
        if (emailCode != null) {
            HttpSession session = request.getSession();
            School school = (School) session.getAttribute("schoolSession");
            boolean flag;
            if (school != null){
                //2.1教师调用assessmentService完成激活
                flag = assessmentService.setStatus(emailCode);
            }else {
                //2.2学生调用userService完成激活
                flag = userService.setStatus(emailCode);
            }
            //3.判断是否激活
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href = 'http://localhost/page/login.jsp'>登入</a>";
            } else {
                //激活失败
                msg = "激活失败，激活码错误";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
        return "login";
    }

    /**
     * 用户登入
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取账号和密码
        String sno = request.getParameter("sno");
        String password = request.getParameter("password");

        //2.调用userService查询
        User user = userService.login(sno, password);
        Assessment assessment = assessmentService.login(sno, password);

        ResultInfo info = new ResultInfo();
        HttpSession session = request.getSession();
        if (user != null) {
            //2.1查询到学号密码都正确
            //2.2判断邮箱是否激活
            if (user.getStatus().equals("Y")) {
                session.setAttribute("userSession", user);
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("邮箱未激活!");
            }
        }else if (assessment != null) {
            //2.1查询到学号密码都正确
            //2.2判断邮箱是否激活
            if (assessment.getStatus().equals("Y")) {
                session.setAttribute("assessmentSession", assessment);
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("邮箱未激活!");
            }
        } else {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码或邮箱错误!");
        }
        //3.将info对象序列化为json,将json数据写回客户端
        writeValue(info, response);

        return "login";
    }

    @RequestMapping("/toLogin")
    public String toLoginPage() {
        return "login";
    }



    @RequestMapping("/toIndex")
    public String toIndex() {
        return "redirect:/index.jsp";
    }

    @RequestMapping("/newLogin")
    public String newLoginPage() {
        return "redirect:login";
    }


}

