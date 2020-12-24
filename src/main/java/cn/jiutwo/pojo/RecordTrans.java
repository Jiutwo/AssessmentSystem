package cn.jiutwo.pojo;

/**
 * Record对象转化为RecordTrans对象
 *
 * @author Jiutwo
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordTrans {

    private String rid; // 做题记录的id
    private String userId; // 学生方id
    /**
     * 考核任务id, 可以由此查到每道题的编号
     */
    private String taskId; // 考核任务id
    private String record;
    /**
     * 每道题得分
     */
    private float[] allScore;
    private int status;
    /**
     * 记录每道题的对错
     */
    private int[] isRight;
    /**
     * 提交此做题记录的时间
     */
    private String time;
    /**
     * 每种类型题目的数量，数据库中通过‘#’分隔，然后读取的时候转换为整型
     */
    private int[] count;
    /**
     * 此记录总得分
     */
    private int score;

    private String[] topicid;
}
