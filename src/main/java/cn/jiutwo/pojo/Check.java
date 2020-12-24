package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 考核方审核记录表
 *
 * 考核方批改，对每道题的对错和得分可以进行修改
 *
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
public class Check {

    private String cid;
    /**
     * 学生姓名
     */
    private String username;
    /**
     * 组名
     */
    private String groupName;
    /**
     * 考核方id
     */
    private String assessmentId;
    /**
     * 学生的做题记录id
     */
    private String recordId;
    /**
     * 学生的做题记录
     */
    private String record;
    /**
     * 每道题的对错
     * 0，1表示错、对
     */
    private String isRight;
    /**
     * 每道题的得分
     */
    private String allScore;
    /**
     * 每道题的答案
     */
    private String answer;
    /**
     * 审核状态
     */
    private int status;
    /**
     * 总分
     */
    private int score;
    /**
     * 提交时间
     */
    private String time;



}
