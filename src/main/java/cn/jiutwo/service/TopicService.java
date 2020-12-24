package cn.jiutwo.service;

import cn.jiutwo.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiutwo
 */
@Service
public interface TopicService {

    /** Test 测试环境是否搭建成功
     * 查询所有题目类型
     *
     * @return
     */
    public List<Type> queryAllType();

    /**
     * 添加单选、多选题
     * @param multipleTopic
     * @return
     */
    public int addMultipleTopic(MultipleTopic multipleTopic);

    /**
     * 添加判断
     * @param judgeTopic
     * @return
     */
    public int addJudgeTopic(JudgeTopic judgeTopic);

    /**
     * 添加填空题、简答题
     * @param blankTopic
     * @return
     */
    public int addBlankTopic(BlankTopic blankTopic);


    /**
     * 添加代码题
     * @param codeTopic
     * @return
     */
    public int addCodeTopic(CodeTopic codeTopic);








    /**
     * 根据id删除多选题
     * @param mid
     * @return
     */
    int deleteMultipleTopic(@Param("mid") String mid);

    /**
     * 根据id删除
     * @param jid
     * @return
     */
    int deleteJudgeTopic(@Param("jid") String jid);


    /**
     * 删除填空题，简答题
     * @param bid
     * @return
     */
    int deleteBlankTopic(@Param("bid") String bid);


    /**
     * 删除代码题
     * @param cid
     * @return
     */
    int deleteCodeTopic(@Param("cid") String cid);


    /**
     * 更新单选、多选题
     * @param multipleTopic
     * @return
     */
    int updateMultipleTopic(MultipleTopic multipleTopic);

    /**
     * 更新判断题
     * @param judgeTopic
     * @return
     */
    int updateJudgeTopic(JudgeTopic judgeTopic);

    /**
     * 更新填空、简答题
     * @param blankTopic
     * @return
     */
    int updateBlankTopic(BlankTopic blankTopic);


    /**
     * 更新代码题
     * @param codeTopic
     * @return
     */
    int updateCodeTopic(CodeTopic codeTopic);


    /**
     * 根据题目id查询单选、多选题
     * @param mid
     * @return
     */
    MultipleTopic queryMultipleTopic(@Param("mid") String mid);

    /**
     * 查询判断题
     * @param jid
     * @return
     */
    JudgeTopic queryJudgeTopic(@Param("jid") String jid);

    /**
     * 查询填空、简答题
     * @param bid
     * @return
     */
    BlankTopic queryBlankTopic(@Param("bid") String bid);


    /**
     * 查询代码题
     * @param cid
     * @return
     */
    CodeTopic queryCodeTopic(@Param("cid") String cid);






    /**
     * 根据id数组查询多选题
     * @param topicsId
     * @return
     */
    List<MultipleTopic> queryArrayMultipleTopic(String[] topicsId);

    /**
     * 查询判断题
     * @param topicsId
     * @return
     */
    List<JudgeTopic> queryArrayJudgeTopic(String[] topicsId);

    /**
     * 查询填空题
     * @param topicsId
     * @return
     */
    List<BlankTopic> queryArrayBlankTopic(String[] topicsId);

    /**
     * 查询代码提
     * @param topicsId
     * @return
     */
    List<CodeTopic> queryArrayCodeTopic(String[] topicsId);



    //////////////
    //////////////
    //////////////


    /**
     * 根据传入数量随机查询出单选或多选
     * @param count
     * @param type
     * @return
     */
    List<MultipleTopic> queryRandMultipleTopic(@Param("count") int count, @Param("type") int type);

    /**
     * 随机查询处判断题
     * @param count
     * @return
     */
    List<JudgeTopic> queryRandJudgeTopic(@Param("count") int count);

    /**
     * 随机查询出填空题
     * @param count
     * @param type
     * @return
     */
    List<BlankTopic> queryRandBlankTopic(@Param("count") int count, @Param("type") int type);

    /**
     * 随机查询出代码提
     * @param count
     * @return
     */
    List<CodeTopic> queryRandCodeTopic(@Param("count") int count);
}
