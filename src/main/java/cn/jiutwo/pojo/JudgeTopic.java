package cn.jiutwo.pojo;

/**
 * 判断题实体
 */
public class JudgeTopic {

    private String jid;
    private String content;
    private int answer; // 答案
    private String parse; // 解析
    private String createtime; // 创建时间
    private String founder; // 创建者

    public JudgeTopic(String jid, String content, int answer,
                      String parse, String createtime, String founder) {
        this.jid = jid;
        this.content = content;
        this.answer = answer;
        this.parse = parse;
        this.createtime = createtime;
        this.founder = founder;
    }

    public JudgeTopic(String jid, String content, int answer, String parse) {
        this.jid = jid;
        this.content = content;
        this.answer = answer;
        this.parse = parse;
    }


    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }
}
