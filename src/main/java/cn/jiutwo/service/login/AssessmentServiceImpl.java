package cn.jiutwo.service.login;


import cn.jiutwo.dao.login.AssessmentMapper;
import cn.jiutwo.pojo.Assessment;
import cn.jiutwo.utils.MailUtils;
import cn.jiutwo.utils.Md5Util;
import cn.jiutwo.utils.UuidUtil;

/**
 * @author Deng Hongwei
 */
public class AssessmentServiceImpl implements AssessmentService {

    AssessmentMapper mapper;

    public void setAssessmentMapper(AssessmentMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询工号是否已注册
     * @param sno
     * @return
     */
    @Override
    public Assessment findSno(String sno) {
        return mapper.findSno(sno);
    }

    /**
     * 判断邮箱是否已被注册
     * @param email
     * @return
     */
    @Override
    public boolean findEmail(String email) {
        Assessment assessment = mapper.findEmail(email);
        if (assessment != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 完成用户注册
     * @param assessment
     * @return
     */
    @Override
    public void regist(Assessment assessment) {
        //1.1设置uid和激活码，uuid，唯一字符串
        assessment.setEmailCode(UuidUtil.getUuid());
        assessment.setUid(UuidUtil.getUuid());
        //1.2设置激活状态
        assessment.setStatus("N");
        //1.3设置密码，给密码加密
        try {
            assessment.setPassword(Md5Util.encodeByMd5(assessment.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2.1保存用户信息
        mapper.regist(assessment);
        //1.4设置邮件内容
        String content = "<a href = 'http://localhost/login/setStatus?emailCode="+assessment.getEmailCode()+"'>点击激活【在线考核交流平台】</a>";
        //1.5发送邮件
        MailUtils.sendMail(assessment.getEmail(),content,"激活邮件");
    }

    /**
     * 激活邮箱
     * @param emailCode
     * @return
     */
    @Override
    public boolean setStatus(String emailCode) {
        //1.根据激活码查询用户对象
        Assessment assessment = mapper.findEmailCode(emailCode);
        if (assessment != null){
            //1.2用户存在，修改相应用户邮箱激活状态
            mapper.updateStatus(assessment);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 教师登入
     * @param sno
     * @param password
     * @return
     */
    @Override
    public Assessment login(String sno, String password) {
        //1.根据工号和密码查询用户对象
        Assessment assessment = null;
        try {
            assessment = mapper.findUser(sno, Md5Util.encodeByMd5(password));
        } catch (Exception e) {
        }
        return assessment;
    }
}
