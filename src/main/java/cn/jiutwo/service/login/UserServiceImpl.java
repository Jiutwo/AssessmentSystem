package cn.jiutwo.service.login;


import cn.jiutwo.dao.login.UserMapper;
import cn.jiutwo.pojo.Envelope;
import cn.jiutwo.pojo.PageBean;
import cn.jiutwo.pojo.User;
import cn.jiutwo.utils.MailUtils;
import cn.jiutwo.utils.Md5Util;
import cn.jiutwo.utils.UuidUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户登录，消息发送
 * @author Deng Hongwei
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;
    /**
     * 通过set方法注入
     *
     */
    public void setUserMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询学号是否已注册
     * @param sno
     * @return
     */
    @Override
    public User findSno(String sno) {
        return mapper.findSno(sno);
    }

    /**
     * 判断邮箱是否已被注册，是则返回true，否返回false
     * @param email
     * @return
     */
    @Override
    public boolean findEmail(String email) {
        User user = mapper.findEmail(email);
        if (user != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 完成用户的注册
     * @param user
     * @return
     */
    @Override
    public void regist(User user) {
        //1.1设置uid和激活码，uuid，唯一字符串
        user.setEmailCode(UuidUtil.getUuid());
        user.setUid(UuidUtil.getUuid());
        //1.1设置激活状态
        user.setStatus("N");
        //1.3设置密码，给密码加密
        try {
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2.1保存用户信息
        mapper.regist(user);
        //1.4设置邮件内容
        String content = "<a href = 'http://localhost:8080/login/setStatus?emailCode="+user.getEmailCode()+"'>点击激活【在线考核交流平台】</a>";
        //1.5发送邮件
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
    }

    /**
     * 激活邮箱
     * @param emailCode
     * @return
     */
    @Override
    public boolean setStatus(String emailCode) {
        //1.根据激活码查询用户对象
        User user = mapper.findEmailCode(emailCode);
        if (user != null){
            //1.2用户存在，修改相应用户邮箱激活状态
            mapper.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 用户登入
     * @param sno
     * @param password
     * @return
     */
    @Override
    public User login(String sno, String password) {
        //1.根据学号和密码查询用户对象
        User user = null;
        try {
            user = mapper.findUser(sno, Md5Util.encodeByMd5(password));
        } catch (Exception e) {
        }
        return user;
    }



    /**/
    /**/
    /**/




    /**
     * 条件查询收到的信
     * @param sno
     * @return
     */
    @Override
    public List<Envelope> findReEnvelope(String sno) {
        //1.创建空的PageBean对象

        System.out.println("////////////");
        System.out.println(sno);
        List<Envelope> list = mapper.findReceiveEnvelope(sno);

        System.out.println("//////////000000000");
        System.out.println(list);
        return list;
    }

    /**
     * 条件查询发出的信
     * @param _sno
     * @return
     */
    @Override
    public List<Envelope> findSendEnvelope(String _sno) {
        //1.创建空的PageBean对象
        PageBean<Envelope> pb = new PageBean<Envelope>();
        List<Envelope> list = mapper.findSendEnvelope(_sno);

        return list;
    }

    /**
     * 根据eid删除信
     * @param eid
     */
    @Override
    public void delEnvelopeById(String eid) {
        //1.根据eid删除邮件信息
        mapper.delEnvelopeById(eid);
    }

    /**
     * 根据eid查询信的内容
     * @param eid
     * @return
     */
    @Override
    public Envelope findContentById(String eid) {
        //1.根据eid查询邮件信息
        return mapper.findContentById(eid);
    }

    /**
     * 写信,保存入数据库
     * @param envelope
     */
    @Override
    public void addEnvelope(Envelope envelope) {
        //1.将传进来的envelope存入数据库
        //设置eid的uuid
        envelope.setEid(UuidUtil.getUuid());
        //设置日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        envelope.setDate(sdf.format(new Date()));
        mapper.addEnvelope(envelope);
    }

    @Override
    public void setStatus(int status, String eid) {
        mapper.setStatus(status, eid);
    }

}
