package cn.jiutwo.service;

import cn.jiutwo.dao.TopicMapper;
import cn.jiutwo.pojo.*;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.List;

/**
 * @author Jiutwo
 *
 */
public class TopicServiceImpl implements TopicService {

    /*
    service层 调 dao层
     */

    TopicMapper topicMapper;
    /**
     * 通过set方法注入（在spring-service中配置）
     * @param topicMapper
     */
    public void setAddTopicMapper(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Type> queryAllType() {
        return topicMapper.queryAllType();
    }


    @Override
    public int addMultipleTopic(MultipleTopic multipleTopic) {
        return topicMapper.addMultipleTopic(multipleTopic);
    }

    @Override
    public int addJudgeTopic(JudgeTopic judgeTopic) {
        return topicMapper.addJudgeTopic(judgeTopic);
    }

    @Override
    public int addBlankTopic(BlankTopic blankTopic) {
        return topicMapper.addBlankTopic(blankTopic);
    }

    @Override
    public int addCodeTopic(CodeTopic codeTopic) {
        return topicMapper.addCodeTopic(codeTopic);
    }

    @Override
    public int deleteMultipleTopic(String mid) {
        return topicMapper.deleteMultipleTopic(mid);
    }

    /**
     * @param jid
     * @return
     */
    @Override
    public int deleteJudgeTopic(String jid) {
        return topicMapper.deleteJudgeTopic(jid);
    }

    @Override
    public int deleteBlankTopic(String bid) {
        return topicMapper.deleteBlankTopic(bid);
    }

    @Override
    public int deleteCodeTopic(String cid) {
        return topicMapper.deleteCodeTopic(cid);
    }

    @Override
    public int updateMultipleTopic(MultipleTopic multipleTopic) {
        return topicMapper.updateMultipleTopic(multipleTopic);
    }

    @Override
    public int updateJudgeTopic(JudgeTopic judgeTopic) {
        return topicMapper.updateJudgeTopic(judgeTopic);
    }

    @Override
    public int updateBlankTopic(BlankTopic blankTopic) {
        return topicMapper.updateBlankTopic(blankTopic);
    }

    @Override
    public int updateCodeTopic(CodeTopic codeTopic) {
        return topicMapper.updateCodeTopic(codeTopic);
    }

    @Override
    public MultipleTopic queryMultipleTopic(String mid) {
        return topicMapper.queryMultipleTopic(mid);
    }

    @Override
    public JudgeTopic queryJudgeTopic(String jid) {
        return topicMapper.queryJudgeTopic(jid);
    }

    @Override
    public BlankTopic queryBlankTopic(String bid) {
        return topicMapper.queryBlankTopic(bid);
    }

    @Override
    public CodeTopic queryCodeTopic(String cid) {
        return topicMapper.queryCodeTopic(cid);
    }

    @Override
    public List<MultipleTopic> queryArrayMultipleTopic(String[] topicsId) {
        return topicMapper.queryArrayMultipleTopic(topicsId);
    }

    @Override
    public List<JudgeTopic> queryArrayJudgeTopic(String[] topicsId) {
        return topicMapper.queryArrayJudgeTopic(topicsId);
    }

    @Override
    public List<BlankTopic> queryArrayBlankTopic(String[] topicsId) {
        return topicMapper.queryArrayBlankTopic(topicsId);
    }

    @Override
    public List<CodeTopic> queryArrayCodeTopic(String[] topicsId) {
        return topicMapper.queryArrayCodeTopic(topicsId);
    }



    /////////////
    /////随机查询出任意数量的题目////////
    /////////////

    @Override
    public List<MultipleTopic> queryRandMultipleTopic(int count, int type) {
        return topicMapper.queryRandMultipleTopic(count, type);
    }

    @Override
    public List<JudgeTopic> queryRandJudgeTopic(int count) {
        return topicMapper.queryRandJudgeTopic(count);
    }

    @Override
    public List<BlankTopic> queryRandBlankTopic(int count, int type) {
        return topicMapper.queryRandBlankTopic(count, type);
    }

    @Override
    public List<CodeTopic> queryRandCodeTopic(int count) {
        return topicMapper.queryRandCodeTopic(count);
    }


}
