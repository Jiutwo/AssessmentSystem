package cn.jiutwo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 多选题实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleTopic {
    /**
     * 题目id,此id中要包含题目类型信息
     */
    private String mid;
    private String content; // 题目内容
    private String optiona; // 选项
    private String optionb;
    private String optionc;
    private String optiond;
    private int type; // 单选题或多选题
    private String answer; // 单选或多选答案
    private String parse; // 解析
    private String founder; // 创造者
    private String createtime; // 创建时间



}
