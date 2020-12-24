package cn.jiutwo.controller.login.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成任务分发
        //1.获取请求路径
        String uri = req.getRequestURI();//  /user/*
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println(methodName);
        //3.获取方法对象method
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(method);
        //4.执行方法
        method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接将传入的对象序列化为json，并且写回客户端
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化返回给json，返回
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * sno的后台校验,校验正确返回true，否则返回false
     * @param sno
     * @return
     */
    public boolean snoReg(String sno){
        String Reg = "^\\d{10}$";
        Pattern pattern = Pattern.compile(Reg);
        Matcher matcher = pattern.matcher(sno);
        return matcher.matches();
    }

    /**
     * username的后台校验,校验正确返回true，否则返回false
     * @param username
     * @return
     */
    public boolean uNameReg(String username){
        String Reg = "";
        Pattern pattern = Pattern.compile(Reg);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * password的后台校验,校验正确返回true，否则返回false
     * @param password
     * @return
     */
    public boolean pWordReg(String password){
        String Reg1 = "^[A-Za-z0-9]{6,16}$";
        String Reg2 = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$";
        Pattern pattern1 = Pattern.compile(Reg1);
        Pattern pattern2 = Pattern.compile(Reg2);
        Matcher matcher1 = pattern1.matcher(password);
        Matcher matcher2 = pattern1.matcher(password);
        return (matcher1.matches() && matcher2.matches());
    }

    /**
     * email的后台校验,校验正确返回true，否则返回false
     * @param email
     * @return
     */
    public boolean emailReg(String email){
        String Reg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern = Pattern.compile(Reg);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 返回文件文本内容
     * @param fileName
     * @return
     */
    public String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);

            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}

