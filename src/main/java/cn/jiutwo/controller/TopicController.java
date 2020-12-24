package cn.jiutwo.controller;

import cn.jiutwo.pojo.*;
import cn.jiutwo.service.CommentService;
import cn.jiutwo.service.TaskService;
import cn.jiutwo.service.TopicService;
import cn.jiutwo.utils.GenerateId;
import cn.jiutwo.utils.ParseUtil;
import cn.jiutwo.utils.SensitiveUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController {

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

    /**
     * 拿到收藏夹名字
     * @param request
     * @return
     */
    @RequestMapping("/getGroupCollectName")
    public String getGroupCollectName(HttpServletRequest request, Model model) {
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
             userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else if (request.getSession().getAttribute("assessmentSession") != null){
             userId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        }
        List<String> GroupCollect = commentService.getAllGroupNamesByUserId(userId);
        request.setAttribute("GroupCollectName",GroupCollect);
        model.addAttribute("GroupCollectName", GroupCollect);
        return "forward:/statics/page/collection.jsp";
    }


    @RequestMapping("/getCollectByGroupName")
    @ResponseBody
    public List<AnswerKey> getCollectByGroupName(@RequestParam("groupName") String groupName, HttpServletRequest request) {
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else if (request.getSession().getAttribute("assessmentSession") != null){
            userId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        }

        List<AnswerKey> answerKeys = commentService.getCollectsByGroupNameAndUserId(userId, groupName);
        for (AnswerKey answerKey : answerKeys) {
        }
        return commentService.getCollectsByGroupNameAndUserId(userId, groupName);
    }

    @RequestMapping("/addCollectGroup")
    public void addNewGroup(String groupName, HttpServletRequest request) {
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else if (request.getSession().getAttribute("assessmentSession") != null){
            userId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        }
        commentService.addNewGroupByUserIdAndGroupName(userId, groupName);
    }

    @RequestMapping("/deleteCollectGroup")
    public void deleteGroup(String groupName, HttpServletRequest request) {
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else {
            userId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        }
        commentService.deleteGroupByUserIdAndGroupName(userId,groupName);
    }


    /**
     * 根据taskId获得此次答题需要的题目内容
     * @param taskId
     * @param request
     * @return
     */
    @RequestMapping("/queryTopicByTakeId")
    public String queryTopicByTakeId(@RequestParam("taskId") String taskId, HttpServletRequest request) {

        TaskTrans taskTrans = taskService.getTaskTrans(taskService.queryTaskByTaskId(taskId));
        request.setAttribute("TaskTrans", taskTrans);
        OneTask oneTask = getOneTask(topicService, taskTrans);

        request.setAttribute("MultipleTopic0", oneTask.getSingleTopic());
        request.setAttribute("MultipleTopic1", oneTask.getMultipleTopic());
        request.setAttribute("JudgeTopic", oneTask.getJudgeTopic());
        request.setAttribute("BlankTopic0", oneTask.getBlankTopic());
        request.setAttribute("BlankTopic1", oneTask.getEssayTopic());
        request.setAttribute("CodeTopic", oneTask.getCodeTopic());

        return "forward:/statics/page/doTests.jsp"; // 跳转页面
    }


    /**
     * 查询我的考核任务
     * 完成状态(0表示不使用此属性)， -1代表未完成. 1已完成(直接存储字符串)
     * @param userId
     * @param type 1 查询已完成， 0 查询未完成
     * @return
     */
    @RequestMapping("/getMyTask")
    public List<TaskTrans>  queryMyTask(@RequestParam("userId") String userId, @RequestParam("type") int type) {
        List<TaskTrans> taskTransList = new ArrayList<TaskTrans>();
        if (type == 1) {
            for (Task task : taskService.queryMyCompletedTask(userId)) {
                taskTransList.add(taskService.getTaskTrans(task));
            }
            for (TaskTrans taskTrans : taskTransList) {
                taskTrans.setCompleted("查看题解");
            }
        } else {
            for (Task task : taskService.queryMyToDoTask(userId)) {
                taskTransList.add(taskService.getTaskTrans(task));
            }
            for (TaskTrans taskTrans : taskTransList) {
                taskTrans.setCompleted("立即答题");
            }
        }


        return taskTransList;
    }

    /**
     * 得到我的所有任务，通过userId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMyAllTaskByUserId")
    public List<TaskTrans> getMyAllTaskByUserId(@RequestParam("userId") String userId) {

        List<TaskTrans> taskTransList1 = queryMyTask(userId,0);
        List<TaskTrans> taskTransList2 = queryMyTask(userId,1);
        List<TaskTrans> taskTransList = new ArrayList<TaskTrans>();

        taskTransList.addAll(taskTransList1);
        taskTransList.addAll(taskTransList2);
        System.out.println("考核任务" + taskTransList);
        return taskTransList;
    }






    /**
     * 添加组
     * @param groupName
     * @param founderId
     * @return
     */
    @RequestMapping("/addGroup")
    public int addGroup(@RequestParam("groupName") String groupName,@RequestParam("founderId") String founderId) {
        Group group = new Group(new GenerateId().getId(), groupName, founderId);
        taskService.addGroup(group);
        return 1; // 添加成功
    }

    /**
     * 删除
     * @param groupId
     * @return
     */
    @RequestMapping("/deleteGroup")
    public int deleteGroup(@RequestParam("groupId") String groupId) {
        taskService.deleteGroup(groupId);
        return 1;
    }

    /**
     * 更新组名
     * @param groupId
     * @param groupName
     * @return
     */
    @RequestMapping("/updateGroup")
    public int updateGroup(@RequestParam("groupId") String groupId, @RequestParam("groupName") String groupName) {
        Group group = new Group(groupId, groupName);
        taskService.updateGroup(group);
        return 1;
    }


    /**
     * 根据考核任务id和组id发布相应的考核任务
     * @param taskId
     * @param groupId
     */
    @RequestMapping("/releaseTask")
    public void releasetaskToGroup(@RequestParam("taskId") String taskId, @RequestParam("groupId") String groupId) {
        taskService.releaseTask(taskId, groupId);

    }

    /**
     * 删除发布的考核任务
     * @param taskId
     * @param groupId
     */
    @RequestMapping("/deleteRelease")
    public void deleteRelease(@RequestParam("taskId") String taskId, @RequestParam("groupId") String groupId) {
        taskService.deleteRelease(taskId, groupId);
    }


    /**
     * 添加评论
     * @param
     */
    @RequestMapping("/addComment")
    public void addComment(String commentContent,String answerKeyId , HttpServletRequest request) {

        String newCommentContent = SensitiveUtil.filterInfo(commentContent);
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else if (request.getSession().getAttribute("assessmentSession") != null){
            userId = ((Assessment)request.getSession().getAttribute("userSession")).getUid();
        }
          String postId ;                     //评论对象编号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
          String commentDate = sdf.format(new Date());               //评论时间
          String replyId = null;                    //评论对象(评论)编号
        Comment comment = new Comment(newCommentContent,userId,userId,commentDate,replyId,answerKeyId );
        System.out.println(comment);
        commentService.commentAnswer(comment);
    }

    /**
     * 删除评论
     * @param commentId
     * @param request
     */
    @RequestMapping("/deleteComment")
    public void deleteComment(int commentId, HttpServletRequest request) {
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else if (request.getSession().getAttribute("assessmentSession") != null){
            userId = ((Assessment)request.getSession().getAttribute("userSession")).getUid();
        }
        commentService.deleteComment(commentId, userId);

    }

    /**
     * 根据题解id查询出所有评论，返回json
     * @param answerKeyId
     * @return
     */
    @RequestMapping("/queryAllCommentByAnswerKeyId")
    @ResponseBody
    public List<Comment> queryAllCommentByAnswerKeyId(String answerKeyId) {
        return commentService.getAllCommentsByPostId(answerKeyId);
    }




    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryGroupByFounderId")
    public List<Group> queryGroupByFounderId(HttpServletRequest request) {
        String founderId = (String) request.getSession().getAttribute("assessmentSession");
        return taskService.queryGroupByFounderId(founderId);
    }

    /**
     * 查询所有组
     * @return
     */
    @RequestMapping("/queryAllGroup")
    public List<Group> queryAllGroup() {
        return taskService.queryAllGroup();
    }

    /**
     * 根据需要的题目个数，从题库中抽取
     * 自动从题库中抽取一个一个考核任务
     * @param sinCount
     * @param mulCount
     * @param judCount
     * @param blaCount
     * @param essCount
     * @param codCount
     * @return
     */
    @RequestMapping("/autoProblemSet")
    @ResponseBody
    public OneTask setStatus(@RequestParam("sinCount") int sinCount, @RequestParam("mulCount") int mulCount,
                             @RequestParam("judCount") int judCount, @RequestParam("blaCount") int blaCount,
                             @RequestParam("essCount") int essCount, @RequestParam("codCount") int codCount) {


        List<MultipleTopic> mulList = topicService.queryRandMultipleTopic(sinCount, 0);
        List<MultipleTopic> sinList = topicService.queryRandMultipleTopic(mulCount, 1);
        List<JudgeTopic> judList = topicService.queryRandJudgeTopic(judCount);
        List<BlankTopic> blaList = topicService.queryRandBlankTopic(blaCount, 1);
        List<BlankTopic> essayList = topicService.queryRandBlankTopic(essCount, 0);
        List<CodeTopic> codeList = topicService.queryRandCodeTopic(codCount);


        OneTask oneTask = taskService.autoProblemSet(sinCount, mulCount, judCount, blaCount, essCount, codCount);

        return taskService.setListProblemSet(oneTask, sinList, mulList, judList, blaList, essayList, codeList);
    }






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

    @RequestMapping("/addRecord")
    public String addRecord(@RequestParam("taskId") String taskId, @RequestParam("userAnswer") String[] record, HttpServletRequest request) {
        System.out.println("-========>>>>>>>");
        String userId = null;
        if (request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        } else {
            userId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        }


        TaskTrans taskTrans = taskService.getTaskTrans(taskService.queryTaskByTaskId(taskId));
        request.setAttribute("TaskTrans", taskTrans);
        OneTask oneTask = getOneTask(topicService, taskTrans);

        request.setAttribute("MultipleTopic0", oneTask.getSingleTopic());
        request.setAttribute("MultipleTopic1", oneTask.getMultipleTopic());
        request.setAttribute("JudgeTopic", oneTask.getJudgeTopic());
        request.setAttribute("BlankTopic0", oneTask.getBlankTopic());
        request.setAttribute("BlankTopic1", oneTask.getEssayTopic());
        request.setAttribute("CodeTopic", oneTask.getCodeTopic());


        System.out.println("userId:    " + userId);
        ParseUtil parseUtil = new ParseUtil();
        GenerateId generateId = new GenerateId();
        parseUtil.parseToString(record);

        String[] topicanswer = taskTrans.getTopicanswer();
        // 每道题目的分数
        float[] topicscore = taskTrans.getTopicscore();
        String[] topicId = taskTrans.getTopicId();
        int[] count = taskTrans.getCount();
        float[] allScore = new float[record.length]; // 得分
        int[] isRight = new int[record.length];
        for (int i = 0; i < record.length - 1 ; i++) {
            if (topicanswer[i].equals(record[i])) {
                // 答案正确
                isRight[i] = 1;
                allScore[i] = topicscore[i];
            } else {
                isRight[i] = 0;
                allScore[i] = 0;
            }
        }
        String record2 = parseUtil.parseToString(record).toString();
        topicanswer.toString();
        float count1 = 0;
        for (float v : allScore) {
            count1 += v;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RecordTrans record1 = new RecordTrans(generateId.getRecordId(userId), userId, taskId, record2,
                allScore, 0, isRight, sdf.format(new Date()), count, (int) count1, topicId);
        System.out.println("=+++++++++>" + record1);
        request.setAttribute("myRecord",record);

        request.setAttribute("recordTrans",record1);
        taskService.addRecord(taskService.getRecord(record1));
        // 提交一次记录改变积分 TODO 根据一个公式计算出每次做题的得分，此处简单处理
        taskService.updateIntegral(userId,10);

        return "forward:/statics/page/answer.jsp";
    }


    /**
     * 获取所有题解
     */
    @RequestMapping("/getAllAnswerKey")
    public String  getAllAnswerKey(String topicId, HttpServletRequest request) {
        List<AnswerKey> allAnswerKey = commentService.getAllAnswerKeyByTopicId(topicId);
        request.setAttribute("allAnswerKey", allAnswerKey);
        return "forward:/statics/page/otherAnswer.jsp";
    }








}
