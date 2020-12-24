package cn.jiutwo.pojo;

/**
 * 由Check对象 到 CheckTrans对象
 *
 * @author Jiutwo
 */
public class CheckTrans {

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
    private String[] record;
    /**
     * 每道题的对错
     * 0，1表示错、对
     */
    private int[] isRight;
    /**
     * 每道题的得分
     */
    private float[] allScore;
    /**
     * 每道题的答案
     */
    private String[] answer;
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
