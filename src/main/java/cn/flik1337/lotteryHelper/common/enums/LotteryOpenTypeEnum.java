package cn.flik1337.lotteryHelper.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LotteryOpenTypeEnum {
    OPEN_BY_TIME(0,"按时间开奖"),
    OPEN_BY_NUM(1,"按人数开奖"),
    OPEN_BY_INSTANT(2,"即开即中");


    LotteryOpenTypeEnum(int code,String name){
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

    public static LotteryOpenTypeEnum getValue(int code){

        for (LotteryOpenTypeEnum  lotteryOpenType: values()) {
            if(lotteryOpenType.getCode() == code){
                return  lotteryOpenType;
            }
        }
        return null;

    }
}
