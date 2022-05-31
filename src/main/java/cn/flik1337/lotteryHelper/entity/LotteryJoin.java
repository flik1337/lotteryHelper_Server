package cn.flik1337.lotteryHelper.entity;

import cn.flik1337.lotteryHelper.common.enums.LotteryFromLimitEnum;
import cn.flik1337.lotteryHelper.common.enums.LotteryJoinResultEnum;
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
 * @since 2022-04-22
 */
@TableName("lottery_join")
public class LotteryJoin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "j_id",type = IdType.AUTO)
    private Long jId;

    /**
     * 活动id
     */
    private Long lId;

    /**
     * 用户id
     */
    private Long uId;

    /**
     * 参与时间
     */
    private LocalDateTime joinTime;

    /**
     * 中奖结果
     */
    private LotteryJoinResultEnum joinResult;

    /**
     * 参与活动的来源
     */
    private LotteryFromLimitEnum joinFrom;
    /**
     * 中奖奖品id
     */
    private Long aId;


    public Long getjId() {
        return jId;
    }

    public void setjId(Long jId) {
        this.jId = jId;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    public LotteryJoinResultEnum getJoinResult() {
        return joinResult;
    }

    public void setJoinResult(LotteryJoinResultEnum joinResult) {
        this.joinResult = joinResult;
    }

    public LotteryFromLimitEnum getJoinFrom() {
        return joinFrom;
    }

    public void setJoinFrom(LotteryFromLimitEnum joinFrom) {
        this.joinFrom = joinFrom;
    }

    public Long getAid() {
        return aId;
    }

    public void setAid(Long aid) {
        this.aId = aid;
    }

    @Override
    public String toString() {
        return "LotteryJoin{" +
                "jId=" + jId +
                ", lId=" + lId +
                ", uId=" + uId +
                ", joinTime=" + joinTime +
                ", joinResult=" + joinResult +
                ", joinFrom=" + joinFrom +
                ", aid=" + aId +
                '}';
    }
}
