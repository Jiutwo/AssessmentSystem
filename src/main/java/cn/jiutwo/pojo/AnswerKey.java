package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 代码题题解实体
 */
@Data
@AllArgsConstructor
public class AnswerKey {
    private String aid; // 题解id
    private String codeId; // 代码题id
    private String userId; // 用户id
    private String content; // 题解内容
    private int like; // 点赞数
    private int browse; // 浏览量



}




