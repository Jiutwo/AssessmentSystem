package cn.jiutwo.service.login;


import cn.jiutwo.pojo.Envelope;
import cn.jiutwo.pojo.PageBean;
import cn.jiutwo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生的service接口
 */
@Service
public interface UserService {

     /**
      * 查询学号是否已注册
      * @param sno
      * @return
      */
     User findSno(String sno);

     /**
      * 判断邮箱是否已被注册
      * @param email
      * @return
      */
     boolean findEmail(String email);

     /**
      * 完成学生注册
      * @param user
      * @return
      */
     void regist(User user);

     /**
      * 激活邮箱
      * @param emailCode
      * @return
      */
     boolean setStatus(String emailCode);

     /**
      * 学生登入
      * @param sno
      * @param password
      * @return
      */
     User login(String sno, String password);





     /**/
     /**/
     /**/


     /**
      * 条件查询收到的信
      *@param sno
      * @return
      */
     List<Envelope> findReEnvelope(String sno);

     /**
      * 条件查询发出的信
      * @param sno
      * @return
      */
     List<Envelope> findSendEnvelope(String sno);

     /**
      * 根据eid删除信
      * @param eid
      */
     void delEnvelopeById(String eid);

     /**
      * 根据eid查询信的内容
      * @param eid
      * @return
      */
     Envelope findContentById(String eid);

     /**
      *写信,保存入数据库
      * @param envelope
      */
     void addEnvelope(Envelope envelope);

     /**
      * 标记消息状态
      * 消息的状态，0表示未读，1表示已读
      * @param status
      * @param eid
      */
     void setStatus(@Param("status") int status, @Param("eid") String eid);

}
