package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 代码题实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeTopic {

    private String cid;
    private String title; // 代码题标题
    private String content; // 题目内容
    private String answer;
    private String parse;
    private String createtime;
    private String founder;

}
