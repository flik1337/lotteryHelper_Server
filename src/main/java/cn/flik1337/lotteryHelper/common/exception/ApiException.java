package cn.flik1337.lotteryHelper.common.exception;


import cn.flik1337.lotteryHelper.common.api.IErrorCode;

/**
* @Description: 自定义异常处理
* @Param:
* @Date: 2021/5/18 11:27 上午
*/
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    public ApiException() {
        super();
    }

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public void setErrorCode(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
