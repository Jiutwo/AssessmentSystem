package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 填空题和论述题实体
 * @author Jiutwo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlankTopic {

    private String bid;
    private String content;
    private String answer;
    private String parse;
    /**
     * 题目类型：填空题或者论述题
     * 0表示论述数量，>0表示填空的数量
     */
    private int type;
    private String createtime;
    /**
     * 创建者
     */
    private String founder;

}
