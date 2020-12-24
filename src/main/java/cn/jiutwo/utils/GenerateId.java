package cn.jiutwo.utils;

import cn.jiutwo.pojo.Task;
import cn.jiutwo.pojo.TaskTrans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * 为每种实体类生成相应的id
 * @author Jiutwo
 */
public class GenerateId {

    /**
     * 为用户实体生成id
     * @return
     */
    public String getUserId() {
        String id = UUID.randomUUID().toString();
        return id.replace("-", "");
    }

    public String getId() {
        String id = UUID.randomUUID().toString();
        return id.replace("-", "");
    }


    /**
     * 生成题目id，为了可以通过编号判断处题目类型
     * 生成带有题目类型信息的编号
     *
     * type:
     *  10,20,30,40,50分别代表选择（单选和多选），判断，填空与简答题，代码题
     *  10表示单选题， 11多选题
     *  30表示简答题，大于31表示填空题
     *
     * @param type
     * @return
     */
    public String getTopicId(int type) {
        String id = UUID.randomUUID().toString().replace("-", "").substring(18);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return type + sdf.format(new Date()) + id;
    }


    /**
     * 得到考核任务id
     * id：题目总数 + 时间 + 随机字符
     * @return
     */
    public String getTaskId(TaskTrans taskTans) {
        String id = UUID.randomUUID().toString().replace("-", "").substring(18);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        int count = 0;
        for (int i = 0; i < taskTans.getTopicscore().length; i++) {
            count += taskTans.getTopicscore()[i];
        }

        return trans(count) + sdf.format(new Date()) + id;
    }


    public String getRecordId(String userId) {
        String id = UUID.randomUUID().toString().replace("-", "").substring(20);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        return  sdf.format(new Date()) + id;
    }




    /**
     * 将小于10的数字转为0X的字符串
     * 大于10的则不转换
     *
     * @param num
     * @return
     */
    private String trans(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return "" + num;
        }
    }

}
