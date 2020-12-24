package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

/**
 * 一次考核任务的实体，包括题目内容
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
public class OneTask {



    /**
     * 单选题，多选题实体集合
     */
    private List<MultipleTopic> singleTopic;
    private List<MultipleTopic> multipleTopic;
    private List<JudgeTopic> judgeTopic;
    private List<BlankTopic> blankTopic;
    private List<BlankTopic> essayTopic;
    private List<CodeTopic> codeTopic;

    private String createTime;
    private String endTime;
    /**
     * 每道题目的分数
     */
    private float[] score;
    /**
     * 每种类型题目的数量
     */
    private int[] count;
    private String founderName;

    public OneTask(String createTime, String endTime, float[] score, int[] count, String founderName) {
        this.createTime = createTime;
        this.endTime = endTime;
        this.score = score;
        this.count = count;
        this.founderName = founderName;
    }
}
