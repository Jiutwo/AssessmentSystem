package cn.jiutwo.service.login;


import cn.jiutwo.dao.login.AssessmentMapper;
import cn.jiutwo.dao.login.SchoolMapper;
import cn.jiutwo.pojo.School;

import java.util.List;

/**
 * @author Deng Hongwei
 */

public class SchoolServiceImpl implements SchoolService {

    SchoolMapper mapper;

    public void setSchoolMapper(SchoolMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 根据注册码寻找相应学校
     * @param schoolCode
     * @return
     */
    @Override
    public School findSchoolCode(String schoolCode) {
        return mapper.findSchoolCode(schoolCode);
    }

    /**
     * 寻找所有的省份
     * @return
     */
    @Override
    public List<String> findProvince() {
        //调用dao方法
        return mapper.findProvince();
    }

    /**
     * 根据省份，寻找所有的学校名字
     * @param string
     * @return
     */
    @Override
    public List<String> findSName(String string) {
        return mapper.findSName(string);
    }

    /**
     * 根据省份和学校名字查找相应学校类并返回学校类
     * @param province
     * @param schoolName
     * @return
     */
    @Override
    public String findSchoolId(String province, String schoolName) {
        return mapper.findSchoolId(province,schoolName);
    }
}
