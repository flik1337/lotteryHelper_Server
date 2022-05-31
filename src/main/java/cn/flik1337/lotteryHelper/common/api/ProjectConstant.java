package cn.flik1337.lotteryHelper.common.api;

/**
 * 项目常量
 */
public final class ProjectConstant {

    public static final String REDIS_IS_ONLINE_KEY = "ONLINE";
    public static final String REDIS_ONLINE_USER = "ONLINE_USER";

    public static final Float REFUND_RATE = 0.8F; //实际退款金额未总金额的  x%
    public static final Float WITHDRAWAL_RATE = 0.8F; //实际提现金额未总金额的  x%

    //企业付款有关
    public static final String WITHDRAWAL_DESC = "ziekoo订单提现"; //企业付款备注
    public static final String IS_CHECK_NAME = "NO_CHECK"; //是否校验用户姓名 NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名

    public static final String MESSAGE_TYPE = "messageType";
    public static final int MESSAGE_TYPE_ORDER_REQUEST = 0;//用户发送订单请求返回的消息
    public static final int MESSAGE_TYPE_ORDER_AGREE = 1; //接单师同意接单返回的消息
    public static final int MESSAGE_TYPE_ORDER_REJECT = 2; //接单师拒绝接单返回的消息
    public static final int MESSAGE_TYPE_ORDER_PAY = 3; // 查询订单时用户已支付返回的消息
    public static final int MESSAGE_TYPE_ORDER_CANCEL = 4; //用户取消已被接订单，给接单师发消息
    public static final int MESSAGE_TYPE_ORDER_COMPLETE = 5; //用户点击已完成，给接单师发消息
    //订单接单状态
//    public static final int ORDER_TAKE_STATUS_NO_TAKEN = 0; //没有接单师接单
//    public static final int ORDER_TAKE_STATUS_TAKEN = 1; //接单师已同意

    //订单状态
    public static final int ORDER_STATUS_PLACED = 0;//已下单
    public static final int ORDER_STATUS_BEEN_TAKEN = 1;//已被接（未支付）
    public static final int ORDER_STATUS_ONGOING = 2;//进行中（已支付）
    public static final int ORDER_STATUS_COMPLETED = 3;//已完成
    public static final int ORDER_STATUS_CANCELLING = 4;//已下单
    public static final int ORDER_STATUS_CANCELLED = 5;//已取消

    //订单表中接单师id默认的值
    public static final Long ORDER_TAKER_ID_INITIAL_VALUE = -1L;

    //订单请求表状态
    public static final int ORDER_REQUEST_STATUS_USER_CANCEL = 0; //未对接单师发送下单请求
    public static final int ORDER_REQUEST_STATUS_INVITE = 1; //已经对接单师发送下单请求
    public static final int ORDER_REQUEST_STATUS_TAKER_REJECT = 2; //接单师拒绝
    public static final int ORDER_REQUEST_STATUS_TAKER_AGREE = 3; //接单师同意

    //订单支付状态
    public static final int PAY_STATUS_UNPAID = 0;//未付款
    public static final int PAY_STATUS_PAID = 1;//完成付款
    public static final int PAY_STATUS_REFUNDING = 2;//退款中
    public static final int PAY_STATUS_REFUND_SUCCESS = 3;//退款成功
    public static final int PAY_STATUS_REFUND_FAILED = 4;//退款失败
    public static final int PAY_STATUS_CANCELLED = 5; //订单已取消

    //订单退款状态
    public static final int REFUND_STATUS_VERIFYING = 0; //退款审核中 （要经过后台审批）
//    public static final int REFUND_STATUS_VERIFY_NOT_PASS = 1; //退款审核不通过
//    public static final int REFUND_STATUS_REFUNDING = 2; //退款中
    public static final int REFUND_STATUS_SUCCESS = 1; //退款成功
    public static final int REFUND_STATUS_FAIL = 2; //退款失败
    public static final int REFUND_STATUS_EXCEPTION = 3; //退款异常

    //订单提现状态
    public static final int WITHDRAWAL_STATUS_VERIFYING = 0; //提现审核中
    public static final int WITHDRAWAL_STATUS_SUCCESS = 1; //提现成功
    public static final int WITHDRAWAL_STATUS_FAIL = 2; //提现失败
    public static final int WITHDRAWAL_STATUS_EXCEPTION = 3; //提现异常

    //接单师申请审核状态
    public static final int TAKER_STATUS_VERIFYING = 0; //审核中
    public static final int TAKER_STATUS_PASS = 1; // 已通过
    public static final int TAKER_STATUS_NO_PASS = 2;// 未通过

    //用户是否是接单师
    public static final int USER_IS_NOT_TAKER = 0; //用户不是接单师
    public static final int USER_IS_TAKER = 1; //用户是接单师

    //创建微信支付订单所需参数
    public static final String WX_PAY_ORDER_BODY = "ziekoo-订单支付";
    public static final String WX_PAY_ORDER_TRADE_TYPE = "APP";
    public static final String WX_PAY_ORDER_NOTIFY_URL = "http://www.flik1337.cn/ziekoo-3/order/wx/pay/notify/order";
    public static final String WX_PAY_REFUND_NOTIFY_URL = "http://www.flik1337.cn/ziekoo-3/admin/wx/pay/notify/refund";

}
