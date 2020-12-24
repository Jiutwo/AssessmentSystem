package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户做题记录表
 *
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
public class Record {

    private String rid; // 做题记录的id
    private String userId; // 学生方id
    /**
     * 考核任务
     */
    private String taskId;
    /**
     * 记录作答
     */
    private String record;
    /**
     * 每道题得分
     */
    private String allScore;
    /**
     * 此做题记录的状态，是否需要考核，是否已考核
     */
    private int status;
    /**
     * 记录每道题的对错
     */
    private String isRight;
    /**
     * 提交此做题记录的时间
     */
    private String time;
    private String count; // 每种类型题目的数量，数据库中通过‘#’分隔，然后读取的时候转换为整型
    /**
     * 此记录总得分
     */
    private int score;

    /**
     * 所有题目的id
     */
    private String topicid;


}
