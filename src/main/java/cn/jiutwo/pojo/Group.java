package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 组实体
 *
 * 一个组对应一个考核方
 * 每个学生归属于多个组
 * 每个考核方可以创建多个组
 *
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
public class Group {

    private String gid;
    private String groupName;
    private String founderId;

    public Group(String gid, String groupName) {
        this.gid = gid;
        this.groupName = groupName;
    }
}
