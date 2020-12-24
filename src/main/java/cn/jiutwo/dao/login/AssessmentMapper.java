package cn.jiutwo.dao.login;

import cn.jiutwo.pojo.Assessment;
import org.apache.ibatis.annotations.Param;

/**
 * 操作教师dao
 */
public interface AssessmentMapper {

    /**
     * 查询工号是否已注册
     * @param sno
     * @return
     */
    Assessment findSno(@Param("sno") String sno);

    /**
     * 判断邮箱是否已被注册
     * @param email
     * @return
     */
    Assessment findEmail(@Param("email") String email);

    /**
     * 注册教师用户
     * @param assessment
     */
    void regist(Assessment assessment);

    /**
     * 根据激活码查询用户对象
     * @param emailCode
     * @return
     */
    Assessment findEmailCode(@Param("emailCode") String emailCode);

    /**
     * 修改相应用户邮箱激活状态
     * @param assessment
     */
    void updateStatus(Assessment assessment);

    /**
     * 用户登入
     * @param sno
     * @param password
     * @return
     */
    Assessment findUser(@Param("sno") String sno, @Param("password") String password);
}
