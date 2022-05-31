package cn.flik1337.lotteryHelper.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AwardLevelEnum {
    FIRST_PRIZE(1,"一等奖"),
    SECOND_PRIZE(2,"二等奖"),
    THIRD_PRIZE(3,"三等奖"),
    FOURTH_PRIZE(4,"四等奖"),
    FIFTH_PRIZE(5,"五等奖");

    AwardLevelEnum(int code,String name){
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

    public static AwardLevelEnum getValue(int code){

        for (AwardLevelEnum  AwardLeveL: values()) {
            if(AwardLeveL.getCode() == code){
                return  AwardLeveL;
            }
        }
        return null;

    }
}
