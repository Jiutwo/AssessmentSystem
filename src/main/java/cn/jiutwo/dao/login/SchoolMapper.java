package cn.jiutwo.dao.login;

import cn.jiutwo.pojo.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolMapper {

    /**
     * 根据注册码寻找相应学校
     * @param schoolCode
     * @return
     */
    School findSchoolCode(@Param("schoolCode") String schoolCode);

    /**
     * 寻找所有的省份
     * @return
     */
    List<String> findProvince();

    /**
     * 根据省份，寻找所有的学校名字
     * @return
     */
    List<String> findSName(@Param("province") String sting);

    /**
     * 根据province和SchoolName查找相应School并返回School
     * @param province
     * @param schoolName
     * @return
     */
    String findSchoolId(@Param("province") String province, @Param("schoolName") String schoolName);



}
