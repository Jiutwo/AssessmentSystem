package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;//用户id
    private String sno;//学号
    private String username;//用户名，账号
    private String password;//密码
    private String schoolId;//学校id
    private String schoolName;
    private String groupId;//分组id，默认值为0
    private String email;//邮箱
    private String emailCode;//邮箱激活码（要求唯一）uuid
    private String status;//激活状态，Y代表激活，N代表未激活

}
