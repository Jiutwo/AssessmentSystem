package cn.jiutwo.dao;

import cn.jiutwo.pojo.AnswerKey;
import cn.jiutwo.pojo.Collect;
import cn.jiutwo.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 收藏， 评论， 题解
 * @author Zhou Xuan
 */

public interface CommentMapper {

    /**
     * 增加收藏，若不指定组名即添加到默认组
     * @param answerKeyId
     * @param userId
     * @return
     */
    boolean addCollect(@Param(value = "answerKeyId") String answerKeyId, @Param(value = "userId") String userId);

    /**
     * 增加收藏到指定组
     * @param answerKeyId
     * @param userId
     * @param groupName
     * @return
     */
    boolean addCollectToGroup(@Param(value = "answerKeyId") String answerKeyId, @Param(value = "userId") String userId, @Param(value = "groupName") String groupName);

    /**
     * 根据用户编号和新收藏名称，创建一个新组
     * @param userId
     * @param groupName
     * @return
     */
    boolean addNewGroupByUserIdAndGroupName(@Param(value = "userId") String userId, @Param(value = "groupName") String groupName);
    /**
     * 根据用户编号和题解编号，删除该收藏
     * @param answerKeyId
     * @param userId
     */
    boolean deleteCollect(@Param(value = "answerKeyId") String answerKeyId, @Param(value = "userId") String userId);

    /**
     * 根据用户编号和收藏名称，删除该收藏夹下的所有内容
     * @param userId
     * @param groupName
     * @return
     */
    boolean deleteGroupByUserIdAndGroupName(@Param(value = "userId") String userId, @Param(value = "groupName") String groupName);

    /**
     *根据题解编号和用户编号，给定新组名，改变该收藏所在的收藏组名
     * @param answerKeyId
     * @param userId
     * @return
     */
    boolean updateGroupNameByanswerKeyIdAndUserId(@Param(value = "answerKeyId") String answerKeyId, @Param(value = "userId") String userId, @Param(value = "groupName") String groupName);

    /**
     * 通过题解编号和用户编号，查询这个题解是否被收藏
     * @param answerKeyId
     * @param userId
     * @return 1代表存在 0代表不存在
     */
    int queryCollectExistByanswerKeyIdAndUserId(@Param(value = "answerKeyId") String answerKeyId, @Param(value = "userId") String userId);

    /**
     * 根据用户编号得到他的所有收藏
     * @param userId
     */
    List<Collect> getAllCollectionsByUserId(String userId);

    /**
     * 得到该用户的所有收藏名称
     * @param userId
     * @return
     */
    List<String> getAllGroupNamesByUserId(String userId);

    /**
     * 根据用户编号和收藏名称得到该收藏中的所有题解编号
     * @return
     */
    List<String> getAllanswerKeyIdsByGroupNameAndUserId(@Param(value = "userId") String userId, @Param(value = "groupName") String groupName);

    /**
     * 根据用户编号和收藏名称得到此个收藏名称有多少个，规定：每个收藏名称只能穿创建一次 ；若返回值大于1，则不允许后期此个收藏夹的创建
     * @param userId
     * @param groupName
     * @return
     */
    int getCountByUserIdAndGroupName(@Param(value = "userId") String userId, @Param(value = "groupName") String groupName);

    /**
     * 根据题解编号，查询收藏题解的收藏量
     * @param answerKeyId
     * @return
     */
    int getCountByanswerKeyId(@Param(value = "answerKeyId") String answerKeyId);



    /**
     * 评论题解
     * @param comment
     */
    boolean commentAnswer(Comment comment);

    /**
     * 根据评论编号和评论者编号,进行删除评论
     * @param commentId
     */
    boolean deleteComment(@Param(value = "commentId") int commentId, @Param(value = "userId") String userId);

    /**
     * 根据题解编号，查询此题解下的所有评论
     * @param postId
     * @return
     */
    List<Comment> getAllCommentsByPostId(String postId);




     List<Map<String, Object>> getCollectMap();




    /**/
    /**/
    /**/

    /**
     * 通过题解编号获得题解的实体类
     * @return
     */
    AnswerKey getAnswerKeyByAid(String aid);

    /**
     * 通过题解编号，查看此题解的点赞数量
     * @param aid
     * @return
     */
    int getLikeByAid(String aid);

    /**
     * 通过题解编号以及改变成的点赞量,改变点赞数量
     * @param aid
     * @return
     */
    boolean updateLikeByAidAndCount(@Param(value = "aid") String aid, @Param(value = "changeCount") int changeCount);


    /**
     * 通过题解编号，查看此题解的浏览量
     * @param aid
     * @return
     */
    int getBrowseByAid(String aid);

    /**
     * 通过题解编号以及改变成的浏览量,改变浏览量
     * @param aid
     * @return
     */
    boolean updateBrowseByAidAndCount(@Param(value = "aid") String aid, @Param(value = "changeCount") int changeCount);



    List<AnswerKey>  getAllAnswerKeyByTopicId(@Param("codeId") String codeId);
}
