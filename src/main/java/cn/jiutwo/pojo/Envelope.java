package cn.jiutwo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envelope {
    private String eid;
    private String addresseeSno;
    private String addresserSno;
    private String date;
    private String content;
    private int status;


}
