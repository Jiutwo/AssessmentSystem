package cn.jiutwo.service;

import cn.jiutwo.dao.CheckMapper;
import cn.jiutwo.dao.TaskMapper;
import cn.jiutwo.dao.TopicMapper;
import cn.jiutwo.pojo.*;
import cn.jiutwo.pojo.other.RankList;
import cn.jiutwo.utils.ParseUtil;
import org.apache.ibatis.annotations.One;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 考核任务业务逻辑实现类
 * @author Jiutwo
 */
public class TaskServiceImpl implements TaskService{

    TopicMapper topicMapper;
    TaskMapper taskMapper;
    CheckMapper checkMapper;
    // 通过set方法注入IOC容器中，在spring-service.xml中配置

    public void setTopicMapper(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public void setCheckMapper(CheckMapper checkMapper) {
        this.checkMapper = checkMapper;
    }

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
    @Override
    public OneTask autoProblemSet(int singleMultipleCount, int multipleCount, int judgeCount,
                                  int blankCount, int essayCount, int codeCount) {

        SimpleDateFormat sdf = new SimpleDateFormat();
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        // 默认截止时间是的后3天
        c.add(Calendar.DAY_OF_MONTH, 3);
        Date tomorrow = c.getTime();

        // 每种类型题目的数量,注意顺序的问题
        int[] count = {singleMultipleCount, multipleCount, judgeCount, blankCount, essayCount, codeCount};

        // 此处将每题的分数设置平均分（默认100）TODO 后期根据不同题目的类型和难易度进行智能赋分
        int num = 0;
        for (int i = 0; i < count.length; i++) {
            num += count[i];
        }
        float[] score = new float[num];
        float avg = 100 / num;
        for (int i = 0; i < num; i++) {
            score[i] = avg;
        }




        return new OneTask(sdf.format(today), sdf.format(tomorrow), score, count, "auto");
    }

    @Override
    public OneTask setListProblemSet(OneTask oneTask, List<MultipleTopic> sinTopicList, List<MultipleTopic> mulTopicList,
                                     List<JudgeTopic> judTopicList, List<BlankTopic> blaTopicList, List<BlankTopic> essList,
                                     List<CodeTopic> codeTopicList) {
        oneTask.setSingleTopic(sinTopicList);
        oneTask.setMultipleTopic(mulTopicList);
        oneTask.setJudgeTopic(judTopicList);
        oneTask.setBlankTopic(blaTopicList);
        oneTask.setCodeTopic(codeTopicList);

        return oneTask;
    }



    @Override
    public int addTask(Task task) {

        return taskMapper.addTask(task);
    }

    @Override
    public int deleteTask(int tid) {
        return taskMapper.deleteTask(tid);
    }

    @Override
    public int updateTask(int tid) {
        return taskMapper.updateTask(tid);
    }

    @Override
    public Task queryTaskByTaskId(String tid) {
        return taskMapper.queryTaskByTaskId(tid);
    }

    @Override
    public List<Task> queryTaskByFounderid(String founderid) {
        return taskMapper.queryTaskByFounderid(founderid);
    }

    @Override
    public List<Task> queryAllPulicTask() {
        return taskMapper.queryAllPulicTask();
    }


    /**
     * TaskTrans对象转为Task对象
     * @param taskTrans
     * @return
     */
    @Override
    public Task getTask(TaskTrans taskTrans) {
        ParseUtil parseUtil = new ParseUtil();
        String topicAnswer = parseUtil.parseToString(taskTrans.getTopicanswer()).toString();
        String topicScore = parseUtil.parseToString(taskTrans.getTopicscore()).toString();
        String count = parseUtil.parseToString(taskTrans.getCount()).toString();
        String topicid = parseUtil.parseToString(taskTrans.getTopicId()).toString();

        Task task = new Task(taskTrans.getTid(), taskTrans.getTitle(), count, topicid, topicScore,topicAnswer,
                taskTrans.getCreatetime(), taskTrans.getStatus(),taskTrans.getEndtime(), taskTrans.getFounderid(),
                taskTrans.getFounder());

        return task;
    }

    @Override
    public List<Task> queryTaskByUserId(String userId) {
        return taskMapper.queryTaskByUserId(userId);
    }


    /**/
    /**/
    /**/


    @Override
    public int addRecord(Record record) {
        return checkMapper.addRecord(record);
    }

    @Override
    public int deleteRecord(int rid) {
        return checkMapper.deleteRecord(rid);
    }

    @Override
    public int updateRecord(Record record) {
        return checkMapper.updateRecord(record);
    }

    @Override
    public Record queryRecordByRecordId(String rid) {
        return checkMapper.queryRecordByRecordId(rid);
    }


    @Override
    public List<Record> queryRecordByUserId(String userId) {
        return checkMapper.queryRecordByUserId(userId);
    }



    /**/
    /**/
    /**/




    @Override
    public Record getRecord(RecordTrans recordTrans) {
        ParseUtil parseUtil = new ParseUtil();
        String allScore = parseUtil.parseToString(recordTrans.getAllScore()).toString();
        String record = recordTrans.getRecord();
        String count = parseUtil.parseToString(recordTrans.getCount()).toString();
        String isRight = parseUtil.parseToString(recordTrans.getIsRight()).toString();
        String rid = recordTrans.getRid();
        int score = recordTrans.getScore();
        int status = recordTrans.getStatus();
        String time = recordTrans.getTime();
        String userId = recordTrans.getUserId();
        String taskId = recordTrans.getTaskId();
        String topicid = parseUtil.parseToString(recordTrans.getTopicid()).toString();
        Record record_1 = new Record(rid, userId, taskId, record, allScore, status, isRight, time, count, score, topicid);

        return record_1;
    }

    @Override
    public RecordTrans getRecordTrans(Record record) {
        ParseUtil parseUtil = new ParseUtil();
        String record_1 = record.getRecord();
        float[] allScore = parseUtil.parseToFloatArray(record.getAllScore());
        int[] isRight = parseUtil.parseToIntArray(record.getCount());
        int[] count = parseUtil.parseToIntArray(record.getCount());
        String rid = record.getRid();
        String userId = record.getUserId();
        String taskId = record.getTaskId();
        int status = record.getStatus();
        String time = record.getTime();
        int score = record.getScore();
        String[] topicid = parseUtil.parseToStrArray(record.getTopicid());
        RecordTrans recordTrans = new RecordTrans(rid, userId, taskId, record_1, allScore, status, isRight, time,
                count, score, topicid);
        return recordTrans;
    }



    /**/
    /**/
    /**/



    @Override
    public int addCheck(Check check) {
        return checkMapper.addCheck(check);
    }

    @Override
    public int deleteCheck(String cid) {
        return checkMapper.deleteCheck(cid);
    }

    @Override
    public int updateCheck(Check check) {
        return checkMapper.updateCheck(check);
    }

    @Override
    public List<Check> queryCheck(String cid, String gid, String assessmentId, String username) {
        return checkMapper.queryCheck(cid, gid, assessmentId, username);
    }



    @Override
    public int[] RightOrWrong(String[] record, String[] answer) {
        if (record.length != answer.length) {
            return null;
        }
        int[] tmp = new int[answer.length];
        for (int i = 0; i < answer.length; ) {
            if (record[i] == answer[i]) {
                tmp[i] = 1;
            } else {
                tmp[i] = 0;
            }
        }
        return tmp;
    }



    /**/
    /*组操作*/
    /**/



    @Override
    public int addGroup(Group group) {
        return taskMapper.addGroup(group);
    }

    @Override
    public int deleteGroup(String gid) {
        return taskMapper.deleteGroup(gid);
    }

    @Override
    public int updateGroup(Group group) {
        return taskMapper.updateGroup(group);
    }

    @Override
    public List<Group> queryGroupByFounderId(String founderId) {
        return taskMapper.queryGroupByFounderId(founderId);
    }

    @Override
    public List<Group> queryAllGroup() {
        return taskMapper.queryAllGroup();
    }

    @Override
    public int joinGroup(String userId, String groupId) {
        return taskMapper.joinGroup(userId, groupId);
    }

    @Override
    public int quitGroup(String userId, String groupId) {
        return taskMapper.quitGroup(userId, groupId);
    }

    @Override
    public int deleteUserFromGroup(String userId, String groupId) {
        return taskMapper.deleteUserFromGroup(userId, groupId);
    }

    @Override
    public int releaseTask(String taskId, String groupId) {
        return taskMapper.releaseTask(taskId, groupId);
    }

    @Override
    public int deleteRelease(String taskId, String groupId) {
        return taskMapper.deleteRelease(taskId, groupId);
    }

    @Override
    public int updateTaskStatus(String tid, int status) {
        return taskMapper.updateTaskStatus(tid, status);
    }


    /**/
    /**/
    /**/

    @Override
    public int addIntegral(String userId, int integral) {
        return taskMapper.addIntegral(userId, integral);
    }

    @Override
    public int updateIntegral(String userId, int integral) {
        return taskMapper.updateIntegral(userId, integral);
    }

    @Override
    public int deleteIntegral(String userId) {
        return taskMapper.deleteIntegral(userId);
    }

    @Override
    public String  queryIntegralByUserId(String userId) {
        return taskMapper.queryIntegralByUserId(userId);
    }

    @Override
    public List<RankList> queryTopIntegral(int top) {
        return taskMapper.queryTopIntegral(top);
    }


    @Override
    public OneTask getOneTask(Task task) {
        ParseUtil parseUtil = new ParseUtil();
        OneTask oneTask = new OneTask(task.getCreatetime(), task.getEndtime(),
                parseUtil.parseToFloatArray(task.getTopicscore()), parseUtil.parseToIntArray(task.getCount()),
                task.getFounder());
        return oneTask;
    }

    @Override
    public List<Task> queryMyCompletedTask(String userId) {
        return taskMapper.queryMyCompletedTask(userId);
    }

    @Override
    public List<Task> queryMyToDoTask(String userId) {
        return taskMapper.queryMyToDoTask(userId);
    }


    /**
     * 将task对象转为taskTrans对象
     * @param task
     * @return
     */
    @Override
    public TaskTrans getTaskTrans(Task task) {
        ParseUtil parseUtil = new ParseUtil();
        float[] topicScore = parseUtil.parseToFloatArray(task.getTopicscore());
        String[] topicAnswer = parseUtil.parseToStrArray(task.getTopicanswer());
        int[] topicCount = parseUtil.parseToIntArray(task.getCount());
        String[] topicid = parseUtil.parseToStrArray(task.getTopicid());
        int allCount = 0;
        for (int i = 0; i < topicCount.length; i++) {
            allCount += topicCount[i];
        }
        TaskTrans taskTrans = new TaskTrans(task.getTid(), task.getTitle(), topicCount, topicid, topicScore,topicAnswer,
                task.getCreatetime(), task.getStatus(),task.getEndtime(), task.getFounderid(),
                task.getFounder(), allCount);
        return taskTrans;
    }





}
