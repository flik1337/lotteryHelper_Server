package cn.flik1337.lotteryHelper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author flik1337
 * @since 2022-04-22
 */
@TableName("lottery_join_limit")
public class LotteryJoinLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "limit_id", type = IdType.AUTO)
    private Long limitId;

    private Long lotteryId;

    private String pwd;

    private Integer fromLimit;

    public Long getLimitId() {
        return limitId;
    }

    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }
    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public Integer getFromLimit() {
        return fromLimit;
    }

    public void setFromLimit(Integer fromLimit) {
        this.fromLimit = fromLimit;
    }

    @Override
    public String toString() {
        return "LotteryJoinLimit{" +
            "limitId=" + limitId +
            ", lotteryId=" + lotteryId +
            ", pwd=" + pwd +
            ", fromLimit=" + fromLimit +
        "}";
    }
}
