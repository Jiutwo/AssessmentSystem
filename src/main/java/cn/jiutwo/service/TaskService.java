package cn.jiutwo.service;

import cn.jiutwo.dao.TopicMapper;
import cn.jiutwo.pojo.*;
import cn.jiutwo.pojo.other.RankList;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 与考核任务相关的业务逻辑层
 * 以及答题记录record表
 * @author Jiutwo
 */
@Service
public interface TaskService {


    /**
     * 自动组题
     * 随机生成每种类型题目的id
     * 然后从题库中获取
     * @param singleMultipleCount 单选题   type： 0表示单选题， 1表示多选题
     * @param multipleCount 多选题
     * @param judgeCount 判断题
     * @param blankCount 填空题   type： 0表示简答题， 1表示填空题
     * @param essayCount 简答题
     * @param codeCount 代码题
     * @return
     */
    OneTask autoProblemSet(int singleMultipleCount, int multipleCount, int judgeCount,
                                  int blankCount, int essayCount, int codeCount);


    /**
     * 为OneTask设置参数
     * @param oneTask
     * @param sinTopicList
     * @param mulTopicList
     * @param judTopicList
     * @param blaList
     * @param essList
     * @param codeList
     * @return
     */
    OneTask setListProblemSet(OneTask oneTask, List<MultipleTopic> sinTopicList, List<MultipleTopic> mulTopicList,
                              List<JudgeTopic> judTopicList, List<BlankTopic> blaList, List<BlankTopic> essList,
                              List<CodeTopic> codeList);






    /**
     * 添加考核任务
     *
     * @param task
     * @return
     */
    int addTask(Task task);

    /**
     * 删除
     *
     * @param tid
     * @return
     */
    int deleteTask(@Param("tid") int tid);

    /**
     * 更新
     *
     * @param tid
     * @return
     */
    int updateTask(@Param("tid") int tid);

    /**
     * 根据考核任务查询
     *
     * @param tid
     * @return
     */
    Task queryTaskByTaskId(@Param("tid") String tid);

    /**
     * 根据创建者id查询
     *
     * @param founderid
     * @return
     */
    List<Task> queryTaskByFounderid(@Param("founderid") String founderid);



    /**
     * 查询所有公开考题
     * @return
     */
    List<Task> queryAllPulicTask();


    /**/
    /**/
    /**/


    /**
     * 由Task对象转为TaskTrans对象
     * @param task
     * @return
     */
    TaskTrans getTaskTrans(Task task);


    /**
     * 由task对象转为TaskTans对象
     * @param taskTrans
     * @return
     */
    Task getTask(TaskTrans taskTrans);


    /**
     * 根据userId查询出已经发布给自己的任务
     *
     * @param userId
     * @return
     */
    List<Task> queryTaskByUserId(@Param("userId") String userId);




    /**/
    /**/
    /**/



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


    /**
     * 将Record转为RecordTrans对象
     * @param recordTrans
     * @return
     */
    Record getRecord(RecordTrans recordTrans);

    /**
     * RecordTrans对象转为Record
     * @param record
     * @return
     */
    RecordTrans getRecordTrans(Record record);




    /**/
    /**/
    /**/



    /**
     * 添加审题记录
     * @param check
     * @return
     */
    int addCheck(Check check);

    /**
     * 删除审题记录
     * @param cid
     * @return
     */
    int deleteCheck(@Param("cid") String cid);

    /**
     * 更新审题记录
     * @param check
     * @return
     */
    int updateCheck(Check check);


    /**
     * 根据以下参数查询出审核
     * 如果没有此参数则为传null
     * @param cid
     * @param gid
     * @param assessmentId
     * @param username
     * @return
     */
    List<Check> queryCheck(@Param("cid") String cid,@Param("gid") String gid,
                          @Param("assessment_id") String assessmentId, @Param("username") String username);






    /**
     * 根据做题记录和答案,判断对错
     * 代码存的是运行结果
     * @param record
     * @param answer
     * @return
     */
    int[] RightOrWrong(String[] record, String[] answer);












    /**
     * 增加一个组（考核方）
     * @param group
     * @return
     */
    int addGroup(Group group);

    /**
     * 根据id删除一个组
     * @param gid
     * @return
     */
    int deleteGroup(@Param("gid") String gid);

    /**
     * 修改组名
     * @param group
     * @return
     */
    int updateGroup(Group group);

    /**
     * 获取考核方的所有创建的组
     * @param founderId
     * @return
     */
    List<Group> queryGroupByFounderId(@Param("founderId") String founderId);

    /**
     * 获取所有组，学生方选择加入组
     * 加入组，需要经过考核方审核，才能正式加入组
     * @return
     */
    List<Group> queryAllGroup();




    /**
     * 学生方加入组
     *
     * 默认值为0，学生方申请加入组，status值为0，待考核方审核后，
     * status值变为1，学生方申请退出组，status值为-1，
     * 考核方可以选择将此学生从组内删除
     * @param userId
     * @param groupId
     * @return
     */
    int joinGroup(@Param("userId") String userId, @Param("groupId") String groupId);

    /**
     * 学生方退出组
     * 需要考核方审核才可以真正退出，否者只是无法接受到发布的作业
     * @param userId
     * @param groupId
     * @return
     */
    int quitGroup(@Param("userId") String userId, @Param("groupId") String groupId);



    /**
     * 考核方将学生方从组内删除
     * @param userId
     * @param groupId
     * @return
     */
    int deleteUserFromGroup(@Param("userid") String userId, @Param("groupId") String groupId);


    /**/
    /*发布操作*/
    /**/


    /**
     * 发布考核任务给小组
     * @param taskId
     * @param groupId
     * @return
     */
    int releaseTask(@Param("taskId") String taskId, @Param("groupId") String groupId);

    /**
     * 取消发布考核任务给当前小组
     * @param taskId
     * @param groupId
     * @return
     */
    int deleteRelease(@Param("taskId") String taskId, @Param("groupId") String groupId);


    /**
     * 更新task表的任务状态
     *
     * 任务考核状态，0为未发布，1表示已发布，2表示公开发布
     * @param status 考核任务状态
     * @param tid 考核任务id
     * @return
     */
    int updateTaskStatus(@Param("tid") String tid, @Param("status") int status);


    /**/
    /**/
    /**/



    /**
     * 添加到排行榜
     * @param userId
     * @param integral
     * @return
     */
    int addIntegral(@Param("userId") String userId, @Param("integral") int integral );

    int updateIntegral(@Param("userId") String userId, @Param("integral") int integral);

    int deleteIntegral(@Param("userId") String userId);

    String queryIntegralByUserId(@Param("userId") String userId);

    /**
     * 查询前top分数的人的姓名和学号
     * @param top
     * @return
     */
    List<RankList> queryTopIntegral(@Param("top") int top);


    /**
     * 返回一个OneTask
     * @param task
     * @return
     */
    OneTask getOneTask(Task task);



    /**
     * 查询我已经完成的题目
     * @param userId
     * @return
     */
    List<Task> queryMyCompletedTask(@Param("userId") String userId);

    /**
     * 查询未完成任务
     * @param userId
     * @return
     */
    List<Task> queryMyToDoTask(@Param("userId") String userId);



}
