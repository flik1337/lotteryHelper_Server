package cn.flik1337.lotteryHelper.common.exception;


import cn.flik1337.lotteryHelper.common.api.IErrorCode;

/**
* @Description: 断言处理类，用于抛出各种API异常
* @Param:
* @Date: 2021/5/18 11:26 上午
*/
public class Asserts {
    public static void fail(String message){
        throw new ApiException(message);
    }
    public static void fail(IErrorCode iErrorCode){
        throw new ApiException(iErrorCode);
    }
}
