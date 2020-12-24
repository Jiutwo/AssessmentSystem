package cn.jiutwo.controller;

import cn.jiutwo.pojo.*;
import cn.jiutwo.service.TaskService;
import cn.jiutwo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Jiutwo
 */

@Controller
public class TestController {


    @Qualifier("TopicServiceImpl")
    @Autowired
    TopicService topicService;

    @Qualifier("TaskServiceImpl")
    @Autowired
    TaskService taskService;





    @RequestMapping("/test1")
    public String test(Model model) {
        List<Type> typeList = topicService.queryAllType();
        model.addAttribute("type", typeList);
        System.out.println("=========>");
        return "hello";
    }

    @RequestMapping("/test2")
    public String toIndex(Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        MultipleTopic multipleTopic = new MultipleTopic("1", "234", "jfei", "jiefj", "jfeij", "jiej",
                1, "ABCD", "jijij", "kfej", sdf.format(new Date()) );
        JudgeTopic judgeTopic = new JudgeTopic("1", "1234", 1, "123",
                sdf.format(new Date()),"jiutwo");

        BlankTopic blankTopic = new BlankTopic("1", "tent", "answer", "paser", 0,
                sdf.format(new Date()), "founder");

        CodeTopic codeTopic = new CodeTopic("1", "heloo", "jiefi","jie",
                "helo", sdf.format(new Date()), "founder");


        //        int flag = topicService.addJudgeTopic(judgeTopic);
        //        topicService.addBlankTopic(blankTopic);


        topicService.updateMultipleTopic(multipleTopic);
        topicService.updateJudgeTopic(judgeTopic);
        topicService.updateBlankTopic(blankTopic);
        topicService.updateCodeTopic(codeTopic);
        return "/WEB-INF/test.jsp";
    }





    @RequestMapping("/test3")
    public String test3(Model model) {
        String[] topicsId = {"1", "2", "3"};
        List<CodeTopic> codeList = topicService.queryArrayCodeTopic(topicsId);
        if (codeList == null) {
            System.out.println("xxxxxxxxxxx");
            return "/WEB-INF/test.jsp";
        }
        System.out.println("============>");
        System.out.println(codeList.toString());
        for (CodeTopic codeTopic : codeList) {
            System.out.println(codeTopic);
        }
        model.addAttribute("test", codeList);
        return "/WEB-INF/test.jsp";
    }

    @RequestMapping("/test4")
    public String test4(Model model){
        System.out.println(topicService.queryMultipleTopic("1"));
        System.out.println(topicService.queryJudgeTopic("1"));
        System.out.println(topicService.queryBlankTopic("1"));
        System.out.println(topicService.queryCodeTopic("1"));

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





}
