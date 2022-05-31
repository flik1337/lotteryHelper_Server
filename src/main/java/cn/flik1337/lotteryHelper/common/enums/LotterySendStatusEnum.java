package cn.flik1337.lotteryHelper.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LotterySendStatusEnum {
    NOT_SEND(0,"未发货"),
    SENT(1,"已发货");


    LotterySendStatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    @EnumValue//标记数据库存的值是code
    private  int code;

    @JsonValue // 标记前端展示为name
    private  String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LotterySendStatusEnum getValue(int code){

        for (LotterySendStatusEnum  lotterySendStatus: values()) {
            if(lotterySendStatus.getCode() == code){
                return  lotterySendStatus;
            }
        }
        return null;

    }
}
