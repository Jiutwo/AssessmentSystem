package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考核任务实体
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String tid;
    private String title; // 考核任务标题
    private String count; // 每种类型题目的数量。”#“分隔，转化为整型,存入此数组中
    private String topicid; // 每道题目的id
    private String topicscore; // 每道题目的分值， 如果没有设置那么就是总分的平均分
    private String topicanswer; // 每道题目的答案
    private String createtime; // 创建时间
    private int status; // 考核任务发布的状态
    private String endtime; // 截止时间
    private String founderid; // 创建者id
    private String founder; // 创建者姓名

    public Task(String tid, String title, String count, String topicid, String topicscore, String topicanswer,
                String createtime, int status, String endtime, String founderid) {
        this.tid = tid;
        this.title = title;
        this.count = count;
        this.topicid = topicid;
        this.topicscore = topicscore;
        this.topicanswer = topicanswer;
        this.createtime = createtime;
        this.status = status;
        this.endtime = endtime;
        this.founderid = founderid;
    }
}
