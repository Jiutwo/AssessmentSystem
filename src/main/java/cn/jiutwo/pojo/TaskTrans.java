package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * task的转换实体类
 * 将字符串分离解析成数组，便于直接使用
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
public class TaskTrans {
    private String tid;
    private String title; // 考核任务标题
    private int[] count; // 每种类型题目的数量。”#“分隔，转化为整型,存入此数组中
    private String[] topicId; // 每道题目的id
    private float[] topicscore; // 每道题目的分值， 如果没有设置那么就是总分的平均分
    private String[] topicanswer; // 每道题目的答案
    private String createtime; // 创建时间
    private int status; // 考核任务发布的状态
    private String endtime; // 截止时间
    private String founderid; // 创建者id
    private String founder; // 创建者姓名
    private int allCount; // 所有题目的总题数
    private String completed; // 完成状态(0表示不适用此属性)， -1代表未完成. 1已完成

    public TaskTrans(String tid, String title, int[] count, String[] topicId, float[] topicscore, String[] topicanswer,
                     String createtime, int status, String endtime, String founderid, String founder, int allCount) {
        this.tid = tid;
        this.title = title;
        this.count = count;
        this.topicId = topicId;
        this.topicscore = topicscore;
        this.topicanswer = topicanswer;
        this.createtime = createtime;
        this.status = status;
        this.endtime = endtime;
        this.founderid = founderid;
        this.founder = founder;
        this.allCount = allCount;
    }

    public TaskTrans(String tid, String title, int[] count, String[] topicId, float[] topicscore,
                     String[] topicanswer, String createtime, int status, String endtime, String founderid, int allCount) {
        this.tid = tid;
        this.title = title;
        this.count = count;
        this.topicId = topicId;
        this.topicscore = topicscore;
        this.topicanswer = topicanswer;
        this.createtime = createtime;
        this.status = status;
        this.endtime = endtime;
        this.founderid = founderid;
        this.allCount = allCount;
    }
}
