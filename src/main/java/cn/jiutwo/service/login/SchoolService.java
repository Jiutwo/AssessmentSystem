package cn.jiutwo.service.login;



import cn.jiutwo.pojo.School;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SchoolService {

    /**
     * 根据注册码寻找相应学校
     * @param schoolCode
     * @return
     */
    School findSchoolCode(String schoolCode);

    /**
     * 寻找所有的省份
     * @return
     */
    List<String> findProvince();

    /**
     * 根据省份，寻找所有的学校名字
     * @return
     */
    List<String> findSName(String string);

    /**
     * 根据province和SchoolName查找相应School并返回School
     * @param province
     * @param schoolName
     * @return
     */
    String findSchoolId(String province, String schoolName);



}
