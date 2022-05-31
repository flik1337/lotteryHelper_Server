package cn.flik1337.lotteryHelper.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LotteryDto {
    private List<AwardDto> awardList;
    private Integer openNumber;
    private String openTime;
    private Integer openType;
    private Long userId;

    public List<AwardDto> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<AwardDto> awardList) {
        this.awardList = awardList;
    }

    public Integer getOpenNumber() {
        return openNumber;
    }

    public void setOpenNumber(Integer openNumber) {
        this.openNumber = openNumber;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LotteryDto{" +
                "awardList=" + awardList +
                ", openNumber=" + openNumber +
                ", openTime='" + openTime + '\'' +
                ", openType=" + openType +
                ", userId=" + userId +
                '}';
    }
}
