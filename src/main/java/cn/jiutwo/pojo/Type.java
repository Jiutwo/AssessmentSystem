package cn.jiutwo.pojo;

/**
 * 题目类型实体
 */
public class Type {

    private String tid;
    private String type_name;

    public Type() {
    }

    public Type(String tid, String type_name) {
        this.tid = tid;
        this.type_name = type_name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
