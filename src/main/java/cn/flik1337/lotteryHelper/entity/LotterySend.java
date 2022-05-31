package cn.flik1337.lotteryHelper.entity;

import cn.flik1337.lotteryHelper.common.enums.LotterySendStatusEnum;
import cn.flik1337.lotteryHelper.common.enums.LotteryStatusEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author flik1337
 * @since 2022-05-09
 */
@TableName("lottery_send")
public class LotterySend implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "send_id", type = IdType.AUTO)
    private Long sendId;



    private Long lotteryJoinId;

    private LocalDateTime createTime;

    private LotterySendStatusEnum sendStatus;

    private String deliverNo;

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getLotteryJoinId() {
        return lotteryJoinId;
    }

    public void setLotteryJoinId(Long lotteryJoinId) {
        this.lotteryJoinId = lotteryJoinId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LotterySendStatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(LotterySendStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }
    public String getDeliverNo() {
        return deliverNo;
    }

    public void setDeliverNo(String deliverNo) {
        this.deliverNo = deliverNo;
    }

    @Override
    public String toString() {
        return "LotterySend{" +
            "sendId=" + sendId +
            ", lotteryJoinId=" + lotteryJoinId +
            ", createTime=" + createTime +
            ", sendStatus=" + sendStatus +
            ", deliverNo=" + deliverNo +
        "}";
    }
}
