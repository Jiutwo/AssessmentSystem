package cn.jiutwo.dao;

import cn.jiutwo.pojo.Group;
import cn.jiutwo.pojo.Record;
import cn.jiutwo.pojo.Task;
import cn.jiutwo.pojo.other.RankList;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 考核任务相关
 * 考核任务发布
 * 组 group
 * @author Jiutwo
 */
public interface TaskMapper {

    /**
     * 添加考核任务
     * @param task
     * @return
     */
    int addTask(Task task);

    /**
     * 删除
     * @param tid
     * @return
     */
    int deleteTask(@Param("tid") int tid );

    /**
     * 更新
     * @param tid
     * @return
     */
    int updateTask(@Param("tid") int tid);

    /**
     * 根据考核任务查询
     * @param tid
     * @return
     */
    Task queryTaskByTaskId(@Param("tid") String tid);

    /**
     * 根据创建者id查询
     * @param founderId
     * @return
     */
    List<Task> queryTaskByFounderid(@Param("founderId") String founderId);


    /**
     * 根据userId查询出已经发布给自己的任务
     *
     * @param userId
     * @return
     */
    List<Task> queryTaskByUserId(@Param("userId") String userId);


    /**
     * 查询所有公开考题
     * @return
     */
    List<Task> queryAllPulicTask();



    /**/
    /*组操作*/
    /**/



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
     * @param status
     * @param tid
     * @return
     */
    int updateTaskStatus(@Param("tid") String tid, @Param("status") int status);


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


    /**
     * 获取已经发布的题目
     * @return
     */
    List<Task> queryReleasedTask();


    /**
     * 查询出待发布的考核任务
     * @return
     */
    List<Task> queryToReleaseTask();



   
}
