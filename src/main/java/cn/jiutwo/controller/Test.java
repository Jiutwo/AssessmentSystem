package cn.jiutwo.controller;

import cn.jiutwo.pojo.*;
import cn.jiutwo.service.CommentService;
import cn.jiutwo.service.TaskService;
import cn.jiutwo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class Test {

    @Qualifier("TopicServiceImpl")
    @Autowired
    @Resource
    TopicService topicService;

    @Qualifier("TaskServiceImpl")
    @Autowired
    TaskService taskService;

    @Qualifier("CommentServiceImpl")
    @Autowired
    CommentService commentService;

    @RequestMapping("/test5")
    public String test5() {

        //int singleMultipleCount = 1;
        List<MultipleTopic> mulList = topicService.queryRandMultipleTopic(1, 0);
        //int multipleCount = 1;
        List<MultipleTopic> sinList = topicService.queryRandMultipleTopic(2, 1);
        //int judgeCount = 1;
        List<JudgeTopic> judList = topicService.queryRandJudgeTopic(1);
        //int blankCount = 1;
        List<BlankTopic> blaList = topicService.queryRandBlankTopic(1, 1);
        //int essayCount = 1;
        List<BlankTopic> essayList = topicService.queryRandBlankTopic(0, 0);
        List<CodeTopic> codeList = topicService.queryRandCodeTopic(3);

        System.out.println("++++++>" + sinList.size());
        System.out.println("======>" + codeList.size());

        OneTask oneTask = taskService.autoProblemSet(1,2,1,1,0,3);
        taskService.setListProblemSet(oneTask, sinList, mulList, judList, blaList, essayList, codeList);

        System.out.println(oneTask);
        return "/WEB-INF/test.jsp";
    }



    @RequestMapping("/test6")
    public String test6() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Task task = new Task("234", "1", "1#1#1#1#1#1", "1#1#1#1#1#1#", "1#1#1#2#1#1#",
                "A#B#C#D#E#FG#", sdf.format(new Date()), 0, sdf.format(new Date()), "1");

        System.out.println(taskService.addTask(task));
        System.out.println(taskService.queryTaskByTaskId("1"));
        System.out.println(taskService.queryTaskByFounderid("1"));



        return "/WEB-INF/test.jsp";
    }



    /**
     * 获得OneTask对象, 传给前端
     * @param topicService
     * @param taskTrans
     * @return
     */
    public OneTask getOneTask(TopicService topicService, TaskTrans taskTrans) {
        int[] num = taskTrans.getCount();
        String[] topicid = taskTrans.getTopicId();
        //String[] sinTopic = Arrays.copyOfRange(topicid, i, j);


        List<MultipleTopic> sinTopic = null;
        List<MultipleTopic> mulTopic = null;
        List<JudgeTopic> judgeTopic = null;
        List<BlankTopic> blaTopic = null;
        List<BlankTopic> essTopic = null;
        List<CodeTopic> codeTopics = null;

        // i和j分别是num和topicid的指针
        for (int i = 0, j = 0; i < num.length; i++) {
            if (i == 0 && num[i] != 0) {
                sinTopic = topicService.queryArrayMultipleTopic(Arrays.copyOfRange(topicid, j, j + num[i]));
                j += num[i];
            }else if(i == 1 && num[i] != 0) {
                mulTopic = topicService.queryArrayMultipleTopic(Arrays.copyOfRange(topicid, j, j + num[i]));
                j += num[i];
            }else if (i == 2 && num[i] != 0) {
                judgeTopic = topicService.queryArrayJudgeTopic(Arrays.copyOfRange(topicid, j, j + num[i]));
                j += num[i];
            }else if (i == 3 && num[i] != 0) {
                blaTopic = topicService.queryArrayBlankTopic(Arrays.copyOfRange(topicid, j, j + num[i]));
                j += num[i];
            }else if (i == 4 && num[i] != 0) {
                essTopic = topicService.queryArrayBlankTopic(Arrays.copyOfRange(topicid, j, j + num[i]));
                j += num[i];
            }else if (i == 5 && num[i] != 0) {
                codeTopics = topicService.queryArrayCodeTopic(Arrays.copyOfRange(topicid, j, j + num[i]));
                j += num[i];
            }

        }
        String createTime = taskTrans.getCreatetime();
        String endTime = taskTrans.getEndtime();
        float[] score = taskTrans.getTopicscore();
        int[] count = taskTrans.getCount();
        String founderName = taskTrans.getFounder();

        OneTask oneTask = new OneTask(sinTopic, mulTopic, judgeTopic, blaTopic, essTopic, codeTopics, createTime,
                endTime, score, count, founderName);
        return oneTask;
    }



    @RequestMapping("/testonetask")
    public String testonetask() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Task task = new Task("234", "1", "1#1#1#1#1#2#", "1#1#1#1#1#1#2#", "1#1#1#2#1#1#",
                "A#B#C#D#E#FG#", sdf.format(new Date()), 0, sdf.format(new Date()), "1", "jiutwo");
        TaskTrans taskTrans = taskService.getTaskTrans(task);
        Task copy = taskService.getTask(taskTrans);
        System.out.println("*************》》》" + copy);
        System.out.println("============>>>" + getOneTask(topicService, taskTrans));
        return "/WEB-INF/test.jsp";
    }


    @RequestMapping("/test7")
    public String test7() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Record record = new Record("2", "1","1", "A#public#", "1#0#0#0#0#9#", 1,
                "0#1#",sdf.format(new Date()), "1#0#0#0#0#1#", 10, "1#1#");

        //taskService.addRecord(record);
        taskService.updateRecord(record);
        System.out.println("==========>" + taskService.queryRecordByRecordId("2"));

        return "test";
    }


    /**
     * 对象转化
     * @return
     */
    @RequestMapping("/test8")
    public String test8() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Record record = new Record("2", "1","1", "A#public#", "1#0#0#0#0#9#", 1,
                "0#1#",sdf.format(new Date()), "1#0#0#0#0#1#", 10, "1#1#");

        RecordTrans recordTrans = taskService.getRecordTrans(record);
        System.out.println("========>" + recordTrans);
        System.out.println("====+++++>" + taskService.getRecord(recordTrans));

        return "test";
    }

    @RequestMapping("/test9")
    public String test9() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Check check = new Check("2", "1", "1", "1", "1", "1",
                "1", "1", "1", 0, 12, sdf.format(new Date()));

        // taskService.addCheck(check);
        Check check2 = new Check("2", "1", "1", "1", "1", "1",
                "1", "1", "1", 0, 12, sdf.format(new Date()));
        // taskService.updateCheck(check2);

        System.out.println(taskService.queryCheck(null, null, "1", null));
        System.out.println("======&&&&&999=======");
        return "test";
    }

    @RequestMapping("/test10")
    public String test10() {
        Group group = new Group("2","groupName","1");
        // taskService.addGroup(group);
        // System.out.println(taskService.queryGroupByFounderId("1"));
        System.out.println("=====>" + taskService.queryAllGroup());

        taskService.releaseTask("1", "1"); // 这两个是一起的
        taskService.updateTaskStatus("1", 0);
        return "test";
    }


    @RequestMapping("/test11")
    public String test11() {

        // commentService.addCollect("1", "1");
        // commentService.addCollectToGroup("1", "1", "1");
       // commentService.addNewGroupByUserIdAndGroupName("1", "喜欢");
        // System.out.println(commentService.queryCollectExistByanswerKeyIdAndUserId("1", "1"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        commentService.commentAnswer(new Comment(1, "1fef", "1",
                "1",sdf.format(new Date()), "1", "1"));
        return "test";
    }

    @RequestMapping("/test12")
    public String test12() {
        System.out.println("======>" + taskService.queryTaskByTaskId("1"));
        System.out.println("=======》" + taskService.queryTaskByFounderid("1"));
        System.out.println("=======>" + taskService.queryTaskByUserId("1"));

        return "test";
    }



}
