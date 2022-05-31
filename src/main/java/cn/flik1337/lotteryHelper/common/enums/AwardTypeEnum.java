package cn.flik1337.lotteryHelper.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AwardTypeEnum {
    AWARD(0,"奖品"),
    COUPON(1,"优惠券"),
    EXCHANGE_CODE(2,"兑换码"),
    RED_ENVELOPE_GENERAL(3,"普通红包"),
    RED_ENVELOPE_LUCK(4,"拼手气红包");

    AwardTypeEnum(int code,String name){
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

    public static AwardTypeEnum getValue(int code){

        for (AwardTypeEnum  awardType: values()) {
            if(awardType.getCode() == code){
                return  awardType;
            }
        }
        return null;

    }


}
