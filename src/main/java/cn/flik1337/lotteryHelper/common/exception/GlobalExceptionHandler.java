package cn.flik1337.lotteryHelper.common.exception;





import cn.flik1337.lotteryHelper.common.api.CommonResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.flik1337.lotteryHelper.common.api.ResultCode.*;


/**
 * @Description 全局异常处理
 * @Author flik
 * @Date 2020/7/31 16:38
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult apiExceptionHandle(ApiException e){
        System.out.println(e.toString());
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public CommonResult methodExceptionHandle(HttpRequestMethodNotSupportedException e){
        e.printStackTrace();
        return CommonResult.failed(METHOD_WRONG);
    }
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public CommonResult npExceptionHandle(NullPointerException e){
        e.printStackTrace();
        return CommonResult.failed(BODY_NOT_MATCH);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e){

        e.printStackTrace();
        return CommonResult.failed(FAILED);
    }
}
