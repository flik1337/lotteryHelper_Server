package cn.flik1337.lotteryHelper.entity;

import cn.flik1337.lotteryHelper.common.enums.LotteryOpenTypeEnum;
import cn.flik1337.lotteryHelper.common.enums.LotteryStatusEnum;
import cn.flik1337.lotteryHelper.common.enums.LotteryTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
public class Lottery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 抽奖活动id
     */
    @TableId(value = "l_id",type = IdType.AUTO)
    private Long lId;

    /**
     * 创建者id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    /**
     * 开奖时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime openTime;

    /**
     * 描述
     */
    private String lDescription;

    /**
     * 开奖人数
     */
    private int openNum;

    /**
     * 开奖类型
     */
    private LotteryOpenTypeEnum openType;

    /**
     * 抽奖类别（常规、九宫格等）
     */

    private LotteryTypeEnum lType;

    private LotteryStatusEnum lStatus;
    /**
     * 限制规则id
     */
    private Long limitId;



    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }
    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }
    public String getlDescription() {
        return lDescription;
    }

    public void setlDescription(String lDescription) {
        this.lDescription = lDescription;
    }

    public LotteryTypeEnum getlType() {
        return lType;
    }

    public void setlType(LotteryTypeEnum lType) {
        this.lType = lType;
    }

    public int getOpenNum() {
        return openNum;
    }

    public void setOpenNum(int openNum) {
        this.openNum = openNum;
    }

    public LotteryOpenTypeEnum getOpenType() {
        return openType;
    }

    public void setOpenType(LotteryOpenTypeEnum openType) {
        this.openType = openType;
    }

    public LotteryStatusEnum getlStatus() {
        return lStatus;
    }

    public void setlStatus(LotteryStatusEnum lStatus) {
        this.lStatus = lStatus;
    }

    public Long getLimitId() {
        return limitId;
    }

    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }



    @Override
    public String toString() {
        return "Lottery{" +
                "lId=" + lId +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", openTime=" + openTime +
                ", lDescription='" + lDescription + '\'' +
                ", openNum=" + openNum +
                ", openType=" + openType.getName() +
                ", lStatus=" + lStatus.getName() +
                ", lType=" + lType +
                ", limitId=" + limitId +
                '}';
    }
}
