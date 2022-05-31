package cn.flik1337.lotteryHelper.common.api;


/**
 * 枚举了一些常用API操作码
 */

public enum ResultCode implements IErrorCode{
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    SESSION_WRONG(50005,"获取Session失败"),
    FILE_EMPTY(50029,"文件为空"),
    FILM_NOT_EXIST(50030,"电影不存在"),
    CODE_TOO_SOON(50006,"验证码发送过于频繁"),
    CODE_TOO_MANY(50007,"验证码发送达到上限"),
    DECODE_ERROR(50010,"文件解码失败，请重试"),
    SEAT_LOCKED(50020,"该座位已被锁定"),
    SEAT_NOT_LOCKED(50021,"该座位未被锁定"),
    ORDER_STATUS_ERROR(50022,"订单状态错误，请查看数据库核对状态"),
    ORDER_WITHDRAWAL_STATUS_ERROR(50023,"提现记录状态错误，请联系客服"),
    ORDER_REFUND_STATUS_ERROR(50024,"退款记录状态错误，请联系客服"),
    ORDER_STATUS_CANNOT_CANCEL(50026,"订单状态为不可被取消状态"),
    ORDER_PAY_STATUS_NOT_UNPAID(50027,"支付表中状态不是未付款"),
    ORDER_REFUND_FEE_ERROR(50028,"退款金额错误"),
    ORDER_ERROR(50068,"订单信息异常，请重试"),
    SET_ONLINE_STATUS_ERROR(50050,"设置在线状态失败"),
    FAIL_GENERATOR_MESSAGE(50001,"极光消息构造失败"),
    SEAT_NOT_SEL(50052,"未选择座位"),
    //数据库操作错误
    FAIL_UPDATE_TABLE(50031,"数据库更新失败"),
    FAIL_INSERT_LOTTERY(50032,"抽奖活动插入失败"),
    FAIL_SELECT_ORDER_REQUEST(50033,"查询订单请求表失败"),
    FAIL_UPDATE_USER(50034,"用户表更新失败"),
    FAIL_SELECT_WX_ORDER(50035,"数据库查询微信订单失败"),
    FAIL_UPDATE_ORDER_PAY(50036,"订单支付表更新失败"),
    FAIL_SELECT_ORDER_PAY(50037,"查询订单支付表失败"),
    FAIL_INSERT_ORDER_REFUND(50038,"生成退款记录失败"),
    FAIL_SELECT_ORDER_REFUND(50039,"查询退款记录失败"),
    FAIL_UPDATE_ORDER_REFUND(50040,"更新退款记录失败"),
    FAIL_INSERT_ORDER_WITHDRAW(50041,"提现记录生成失败"),
    FAIL_SELECT_OPEN_ID(50042,"获取openId失败"),
    FAIL_SELECT_ORDER_WITHDRAWAL(50043,"获取提现记录失败"),
    FAIL_INSERT_AWARD(50044,"插入奖品信息失败"),
    FAIL_INSERT_LOTTERY_JOIN(50045,"插入抽奖记录失败"),
    CAN_NOT_PARTICIPATE_AGAIN(50046,"不能重复参与该活动"),
    CAN_NOT_BAN_AGAIN(50047,"不能重复封禁"),
    CAN_NOT_WITHDRAWAL_AGAIN(50048,"不能重复提现"),
    FAIL_UPDATE_USER_CLIENT(50049,"clientId更新失败"),
    USER_IS_NOT_TARGET_TAKER(50060,"该用户不是该订单中接单师"),
    NO_UPDATE(50061,"数据更新失败"),
    TOTAL_FEE_ERROR(50062,"总金额错误"),
    PAY_EXCEPTION(50063,"支付异常"),
    ORDER_NONE(50064,"此订单不存在"),
    TICKET_FETCHED(50066,"该订单已取票"),
    VALIDATE_FAILED(404, "未找到该资源"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    BODY_NOT_MATCH(400,"参数不正确"),
    ILLEGAL_PHONE(40001,"手机号不合法"),
    NICKNAME_EMPTY(40002,"昵称不能为空"),
    GENDER_ERROR(40003,"性别只能是0:女 1:男"),
    ILLEGAL_PASSWORD(40004,"密码不合法"),
    NICKNAME_EXIST(40006,"昵称已存在"),
    NICKNAME_NOT_EXIST(40007,"昵称不存在"),

    WAIT_PAY_MONEY_EMPTY(40011,"请设置金额"),

    FILM_NAME_EMPTY(40012,"电影名称不能为空"),



    USER_BANNED(40016,"用户被封禁"),
    METHOD_WRONG(40016,"请求方式不匹配"),
    WRONG_CODE(40011,"验证码校验失败"),
    FIRST_THIRD_LOGIN(40012,"第一次第三方登录，请注册账号并绑定"),
    USER_BAN_EXIST(40014,"该用户已被封禁，或正在封禁审核中，请耐心等待"),
    FORBIDDEN(403, "没有相关权限"),
    NULL_TOKEN(40301, "token不能为空"),
    WRONG_INFO(40310, "用户名或者密码错误"),
    USER_NOT_EXIST(40311,"用户不存在"),
    USER_EXIST(40312,"用户已存在，请重新输入手机号");


    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
