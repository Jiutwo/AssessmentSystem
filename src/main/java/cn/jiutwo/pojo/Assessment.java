package cn.jiutwo.pojo;

/**
 * 教师实体类
 */
public class Assessment {
    private String uid;//用户id
    private String sno;//学号
    private String username;//用户名，账号
    private String password;//密码
    private String schoolId;//学校id
    private String groupId;//分组id，默认值为0
    private String email;//邮箱
    private String emailCode;//邮箱激活码（要求唯一）uuid
    private String status;//激活状态，Y代表激活，N代表未激活

    public Assessment() {
    }

    public Assessment(String uid, String sno, String username, String password, String schoolId, String groupId, String email, String emailCode, String status) {
        this.uid = uid;
        this.sno = sno;
        this.username = username;
        this.password = password;
        this.schoolId = schoolId;
        this.groupId = groupId;
        this.email = email;
        this.emailCode = emailCode;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "uid='" + uid + '\'' +
                ", sno='" + sno + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", email='" + email + '\'' +
                ", emailCode='" + emailCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
