package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author axuan
 */
@Data
@AllArgsConstructor
public class Collect implements Serializable {
    private int collectId;//收藏编号
    private String answerKeyId;//收藏的题解编号
    private String userId;//收藏题解的用户编号
    private String groupName;//收藏夹名称

    public Collect(){
        super();
    }


}
