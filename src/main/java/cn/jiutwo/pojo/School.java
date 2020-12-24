package cn.jiutwo.pojo;

/**
 * 学校实体类
 */
public class School {
    private String schoolId;//学校id
    private String schoolName;//学校名字
    private String province;//所在省或者直辖市
    private String schoolCode;//注册码或者说教员码

    public School() {
    }

    public School(String schoolId, String schoolName, String province, String schoolCode) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.province = province;
        this.schoolCode = schoolCode;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", province='" + province + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                '}';
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
}
