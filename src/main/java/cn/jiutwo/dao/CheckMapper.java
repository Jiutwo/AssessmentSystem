package cn.jiutwo.dao;

import cn.jiutwo.pojo.Check;
import cn.jiutwo.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人工审核，自动审核相关的mapper
 * record表
 * @author Jiutwo
 */
public interface CheckMapper {

    /**
     * 添加答题记录
     * @param record
     * @return
     */
    int addRecord(Record record);

    /**
     * 根据rid删除答题记录
     * @param rid
     * @return
     */
    int deleteRecord(@Param("rid") int rid);


    /**
     * 更新一次答题记录
     * @param record
     * @return
     */
    int updateRecord(Record record);


    /**
     * 根据rid查询出答题记录
     * @param rid
     * @return
     */
    Record queryRecordByRecordId(@Param("rid") String rid);


    /**
     * 查询此用户的所有答题记录
     * @param userId
     * @return
     */
    List<Record> queryRecordByUserId(@Param("user_id") String userId);






    /**/
    /*对于check表的增删改查*/
    /**/


    /**
     * 添加判题
     * 每提交一次答题记录，需要审核就要提交到判题表
     * @param check
     * @return
     */
    int addCheck(Check check);



    /**
     * 删除判题记录
     * @param cid
     * @return
     */
    int deleteCheck(@Param("cid") String cid);

    /**
     * 更新判题记录
     * @param check
     * @return
     */
    int updateCheck(Check check);


    /**
     * 根据以下参数查询出判核
     * 如果没有相应参数则为传null
     * @param cid
     * @param gid
     * @param assessmentId
     * @param username
     * @return
     */
    List<Check> queryCheck(@Param("cid") String cid, @Param("gid") String gid,
                           @Param("assessment_id") String assessmentId, @Param("username") String username);

   /* *//**
     * 根据check表主键查询
     * @param cid
     * @return
     *//*
    Check queryCheckByCheckId(@Param("cid") String cid);
    *//**
     * 根据组id查询
     * @param gid
     * @return
     *//*
    List<Check> queryCheckByGroupId(@Param("gid") String gid);

    *//**
     * 根据审核方id查询
     * @param assessmentId
     * @return
     *//*
    List<Check> queryCheckByAssessmentId(@Param("assessment_id") String assessmentId);

    *//**
     * 根据学生姓名查询
     * @param username
     * @return
     *//*
    Check queryCheckByUsername(@Param("username") String username);*/


}
