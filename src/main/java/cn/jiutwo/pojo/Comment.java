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
public class Comment implements Serializable {
    private int commentId;                  //评论编号
    private String commentContent;          //评论内容
    private String userId;                     //评论者编号
    private String postId;                     //评论对象编号
    private String commentDate;               //评论时间
    private String replyId;                    //评论对象(评论)编号
    private String answerKeyId;                     //所属题解编号

    public Comment(String commentContent, String userId, String postId, String commentDate, String replyId, String answerKeyId) {
        this.commentContent = commentContent;
        this.userId = userId;
        this.postId = postId;
        this.commentDate = commentDate;
        this.replyId = replyId;
        this.answerKeyId = answerKeyId;
    }
}
