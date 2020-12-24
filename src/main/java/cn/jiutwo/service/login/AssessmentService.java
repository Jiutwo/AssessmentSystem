package cn.jiutwo.service.login;

import cn.jiutwo.pojo.Assessment;
import org.springframework.stereotype.Service;

/**
 * 教师的service接口
 * @author Deng Hongwei
 */
@Service
public interface AssessmentService {

    /**
     * 查询工号是否已注册
     * @param sno
     * @return
     */
    Assessment findSno(String sno);

    /**
     * 判断邮箱是否已被注册
     * @param email
     * @return
     */
    boolean findEmail(String email);

    /**
     * 完成教师注册
     * @param assessment
     * @return
     */
    void regist(Assessment assessment);

    /**
     * 激活邮箱
     * @param emailCode
     * @return
     */
    boolean setStatus(String emailCode);

    /**
     * 教师登入
     * @param sno
     * @param password
     * @return
     */
    Assessment login(String sno, String password);
}
