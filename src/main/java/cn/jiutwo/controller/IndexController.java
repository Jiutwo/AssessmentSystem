package cn.jiutwo.controller;

import cn.jiutwo.pojo.*;
import cn.jiutwo.pojo.other.RankList;
import cn.jiutwo.service.CommentService;
import cn.jiutwo.service.TaskService;
import cn.jiutwo.service.TopicService;
import cn.jiutwo.utils.GenerateId;
import cn.jiutwo.utils.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiInternalFrameUI;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class IndexController {

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



    @RequestMapping("/addSinTopic")
    public Object addSinTopic(
                              @RequestParam("content") String content,
                              @RequestParam("optiona") String optiona,
                              @RequestParam("optionb") String optionb,
                              @RequestParam("optionc") String optionc,
                              @RequestParam("optiond") String optiond,
                              @RequestParam("answer") String answer,
                              @RequestParam("parse") String parse, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GenerateId generateId = new GenerateId();
        String founderId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        int type = 0;

        MultipleTopic multipleTopic = new MultipleTopic(generateId.getTopicId(10), content, optiona, optionb,
                optionc, optiond,type, answer, parse,founderId, sdf.format(new Date()));
        multipleTopic.setType(type);
        topicService.addMultipleTopic(multipleTopic);
        return 1;
    }

    @RequestMapping("/addMulTopic")
    public int addMulTopic(
            @RequestParam("content") String content,@RequestParam("optiona") String optiona,
            @RequestParam("optionb") String optionb,@RequestParam("optionc") String optionc,
            @RequestParam("optiond") String optiond,@RequestParam("answer") String[] answer,
            @RequestParam("parse") String parse , HttpServletRequest request) {
        int type = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GenerateId generateId = new GenerateId();
        String founderId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        ParseUtil parseUtil = new ParseUtil();
        for (String s : answer) {
        }
        MultipleTopic multipleTopic = new MultipleTopic(generateId.getTopicId(20), content, optiona, optionb, optionc,
                optiond, type, parseUtil.parseToString(answer).toString(), parse,founderId, sdf.format(new Date()));
        multipleTopic.setType(type);
        topicService.addMultipleTopic(multipleTopic);
        return 1;
    }
    @RequestMapping("/addJudgeTopic")
    public int addJudgeTopic(String content,String answer,String parse, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GenerateId generateId = new GenerateId();
        int ans = answer.equals("3") ? 1 : 0;
        String founderId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        JudgeTopic judgeTopic = new JudgeTopic(generateId.getTopicId(30), content, ans,
                parse, sdf.format(new Date()), founderId);
        topicService.addJudgeTopic(judgeTopic);
        return 1;
    }

    @RequestMapping("/addBlankTopic")
    public int addBlankTopic(String content,String answer, String parse, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GenerateId generateId = new GenerateId();
        String founderId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        BlankTopic blankTopic = new BlankTopic(generateId.getTopicId(40), content, answer, parse, 0,
                sdf.format(new Date()), founderId);
        topicService.addBlankTopic(blankTopic);
        return 1;
    }

    @RequestMapping("/addEssayTopic")
    public int addEssayTopic(String content,String answer, String parse, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GenerateId generateId = new GenerateId();
        String founderId = ((Assessment)request.getSession().getAttribute("assessmentSession")).getUid();
        BlankTopic blankTopic = new BlankTopic(generateId.getTopicId(50), content, answer, parse, 1,
                sdf.format(new Date()), founderId);
        topicService.addBlankTopic(blankTopic);
        return 1;
    }

    @RequestMapping("/addCodeTopic")
    public int addCodeTopic(@RequestBody List<CodeTopic> codeTopicList) {
        for (CodeTopic topic : codeTopicList) {
            topic.setCid(new GenerateId().getTopicId(60));
            topicService.addCodeTopic(topic);
        }
        return 1;
    }




    @RequestMapping("toIndex")
    public String  GetTask(HttpServletRequest req, HttpServletResponse resp){

         return "redirect:/index.jsp";
    }

    /**
     * 获取排行榜、公开题
     *
     * @return
     */
    @RequestMapping("/getRankList")
    public List<RankList> getRankList() {
        return taskService.queryTopIntegral(10);
    }

    @RequestMapping("/getMyRank")
    public String getMyRankList(HttpServletRequest request){
        String userId = null;
        if(request.getSession().getAttribute("userSession") != null) {
            userId = ((User)request.getSession().getAttribute("userSession")).getUid();
        }
        return taskService.queryIntegralByUserId(userId);
    }

    /**
     * 获得公开考核任务
     * @return
     */
    @RequestMapping("/getPublicTask")
    public List<TaskTrans> getPublicTask() {
        List<TaskTrans> taskTransList = new ArrayList<TaskTrans>();
        for (Task task : taskService.queryAllPulicTask()) {
            taskTransList.add(taskService.getTaskTrans(task));
        }
        return taskTransList;
    }


    /**
     * 人工组题
     * 获取整个考核任务的题目
     * @param request
     * @return
     */
    @RequestMapping("/getOneTask")
    public String Input(HttpServletRequest request) {

        String taskId = (String)request.getAttribute("taskId");
        Task task = taskService.queryTaskByTaskId(taskId);
        taskService.getTaskTrans(task);


        int  mul = (Integer) request.getAttribute("mul");
        int sin = (Integer) request.getAttribute("sin");
        int jud = (Integer) request.getAttribute("jud");
        int bla = (Integer) request.getAttribute("bla");
        int ess = (Integer) request.getAttribute("ess");
        int cod = (Integer) request.getAttribute("cod");

        List<MultipleTopic> mulList = (List<MultipleTopic>) request.getAttribute("mulList");
        List<MultipleTopic> sinList = (List<MultipleTopic>) request.getAttribute("mulList");
        List<JudgeTopic> judList = (List<JudgeTopic>) request.getAttribute("mulList");
        List<BlankTopic> blaList = (List<BlankTopic>) request.getAttribute("mulList");
        List<BlankTopic> essayList = (List<BlankTopic>) request.getAttribute("mulList");
        List<CodeTopic> codeList = (List<CodeTopic>) request.getAttribute("mulList");

        OneTask oneTask = taskService.getOneTask(task);
        oneTask = taskService.setListProblemSet(oneTask, sinList, mulList, judList, blaList, essayList, codeList);
        request.setAttribute("oneTask", oneTask);
        return "task";
    }

    /**
     * 自动组题
     * @param request
     * @return
     */
    @RequestMapping("/autoInput")
    public String autoInput(HttpServletRequest request) {
        int  mul = (Integer) request.getAttribute("mul");
        int sin = (Integer) request.getAttribute("sin");
        int jud = (Integer) request.getAttribute("jud");
        int bla = (Integer) request.getAttribute("bla");
        int ess = (Integer) request.getAttribute("ess");
        int cod = (Integer) request.getAttribute("cod");


        List<MultipleTopic> mulList = topicService.queryRandMultipleTopic(1, 0);
        List<MultipleTopic> sinList = topicService.queryRandMultipleTopic(2, 1);
        List<JudgeTopic> judList = topicService.queryRandJudgeTopic(1);
        List<BlankTopic> blaList = topicService.queryRandBlankTopic(1, 1);
        List<BlankTopic> essayList = topicService.queryRandBlankTopic(0, 0);
        List<CodeTopic> codeList = topicService.queryRandCodeTopic(3);

        OneTask oneTask = taskService.autoProblemSet(mul,sin,jud,bla,ess,cod);
        taskService.setListProblemSet(oneTask, sinList, mulList, judList, blaList, essayList, codeList);

        return "autoInput";
    }


    @RequestMapping("/getCollect")
    public String getCollect(HttpServletRequest request) {

        String userId = (String) request.getAttribute("userId");
        List<Collect> collectList = commentService.getAllCollectionsByUserId(userId);
        request.setAttribute("collectList", collectList);
        return "collect";
    }

    @RequestMapping("/addCollect")
    public String addCollect(HttpServletRequest request) {
        String userId = (String)request.getAttribute("userId");
        String  answerKeyId = (String)request.getAttribute("answerKeyId");
        commentService.addCollect(answerKeyId, userId);

        return "collect";
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

//    @RequestMapping("/deleteCollent")
//    public String deleteCollect(HttpServletRequest) {
//    }

}
