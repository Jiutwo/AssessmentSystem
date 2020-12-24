package cn.jiutwo.service;

import cn.jiutwo.dao.CommentMapper;
import cn.jiutwo.pojo.AnswerKey;
import cn.jiutwo.pojo.Collect;
import cn.jiutwo.pojo.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhou Xuan
 */
public class CommentServiceImpl implements CommentService{


    CommentMapper commentMapper;
    // 通过set()方法注入IOC容器中
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }



    @Override
    public boolean addCollect(String answerKeyId, String userId) {
        return commentMapper.addCollect(answerKeyId, userId);
    }

    @Override
    public boolean addCollectToGroup(String answerKeyId, String userId, String groupName) {
        return commentMapper.addCollectToGroup(answerKeyId, userId, groupName);
    }

    @Override
    public boolean addNewGroupByUserIdAndGroupName(String userId, String groupName) {
        return commentMapper.addNewGroupByUserIdAndGroupName(userId, groupName);
    }

    @Override
    public boolean deleteCollect(String answerKeyId, String userId) {
        return commentMapper.deleteCollect(answerKeyId, userId);
    }

    @Override
    public boolean deleteGroupByUserIdAndGroupName(String userId, String groupName) {
        return commentMapper.deleteGroupByUserIdAndGroupName(userId, groupName);
    }

    @Override
    public boolean updateGroupNameByanswerKeyIdAndUserId(String answerKeyId, String userId, String groupName) {
        return commentMapper.updateGroupNameByanswerKeyIdAndUserId(answerKeyId, userId, groupName);
    }

    @Override
    public int queryCollectExistByanswerKeyIdAndUserId(String answerKeyId, String userId) {
        return commentMapper.queryCollectExistByanswerKeyIdAndUserId(answerKeyId,userId);
    }

    @Override
    public List<Collect> getAllCollectionsByUserId(String userId) {
        return commentMapper.getAllCollectionsByUserId(userId);
    }

    @Override
    public List<String> getAllGroupNamesByUserId(String userId) {
        return commentMapper.getAllGroupNamesByUserId(userId);
    }


    @Override
    public List<String> getAllanswerKeyIdsByGroupNameAndUserId(String userId, String groupName) {
        return commentMapper.getAllanswerKeyIdsByGroupNameAndUserId(userId,groupName);
    }

    @Override
    public int getCountByUserIdAndGroupName(String userId, String groupName) {
        return commentMapper.getCountByUserIdAndGroupName(userId, groupName);
    }

    @Override
    /**
     * 查收藏量
     */
    public int getCountByanswerKeyId(String answerKeyId) {
        return commentMapper.getCountByanswerKeyId(answerKeyId);
    }

    @Override
    public boolean commentAnswer(Comment comment) {
        return commentMapper.commentAnswer(comment);
    }

    @Override
    public boolean deleteComment(int commentId, String userId) {
        return commentMapper.deleteComment(commentId, userId);
    }

    @Override
    public List<Comment> getAllCommentsByPostId(String postId) {
        return commentMapper.getAllCommentsByPostId(postId);
    }

    @Override
    public boolean addLikeByAid(String aid) {
        int likeCount = commentMapper.getLikeByAid(aid);
        return  commentMapper.updateLikeByAidAndCount(aid, likeCount+1);
    }

    @Override
    public boolean deleteLikeByAid(String aid) {
        int likeCount = commentMapper.getLikeByAid(aid);
        return commentMapper.updateLikeByAidAndCount(aid, likeCount-1);
    }

    @Override
    public boolean addBrowseByAid(String aid) {
        int browseCount = commentMapper.getBrowseByAid(aid);
        return commentMapper.updateBrowseByAidAndCount(aid, browseCount+1);
    }

    @Override
    public boolean deleteBrowseByAid(String aid) {
        int browseCount = commentMapper.getBrowseByAid(aid);
        return commentMapper.updateBrowseByAidAndCount(aid, browseCount-1);
    }

    @Override
    public List<AnswerKey> getAllAnswerKeyByTopicId(String topicId) {
        return commentMapper.getAllAnswerKeyByTopicId(topicId);
    }

    @Override
    public List<AnswerKey> getCollectsByGroupNameAndUserId(String userId, String groupName) {
        List<AnswerKey> answerKeys = new ArrayList<AnswerKey>();
        List<String> answerKeyIds = commentMapper.getAllanswerKeyIdsByGroupNameAndUserId(userId,groupName);
        for (String answerKeyId : answerKeyIds) {
            AnswerKey answerKey = commentMapper.getAnswerKeyByAid(answerKeyId);
            answerKeys.add(answerKey);
        }
        return answerKeys;
    }







}
