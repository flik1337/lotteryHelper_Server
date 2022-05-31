package cn.flik1337.lotteryHelper.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LotteryStatusEnum {
    READY(0,"待开奖"),
    CLOSED(1,"已开奖");


    LotteryStatusEnum(int code,String name){
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
}
