package cn.jiutwo.dao.login;

import cn.jiutwo.pojo.Envelope;
import cn.jiutwo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作用户dao
 * @author Deng Hongwei
 */
public interface UserMapper {

    UserMapper mapper = null;




    /**
     * 查询学号是否已注册
     * @param sno
     * @return
     */
    User findSno(@Param("sno") String sno);

    /**
     * 判断邮箱是否已被注册
     * @param email
     * @return
     */
    User findEmail(@Param("email") String email);

    /**
     * 注册用户
     * @param user
     */
    void regist(User user);

    /**
     * 根据激活码查询用户对象
     * @param emailCode
     * @return
     */
    User findEmailCode(@Param("emailCode") String emailCode);

    /**
     * 修改相应用户邮箱激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 用户登入
     * @param sno
     * @param password
     * @return
     */
    User findUser(@Param("sno") String sno, @Param("password") String password);







    /**
     * 根据用户sno和页码查询所有收件人
     * @param sno
     * @return
     */
    List<Envelope> findReceiveEnvelope(@Param("addressee_sno") String sno);


    /**
     * 根据用户sno和页码查询所有发件人
     * @param sno
     * @return
     */
    List<Envelope> findSendEnvelope(@Param("addresser_sno") String sno);

    /**
     * 根据邮件eid删除邮件信息
     * @param eid
     */
    void delEnvelopeById(@Param("eid") String eid);

    /**
     * 根据eid查询邮件信息
     * @param eid
     * @return
     */
    Envelope findContentById(@Param("eid") String eid);

    /**
     * 将传进来的envelope存入数据库
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
