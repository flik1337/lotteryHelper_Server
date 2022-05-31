package cn.flik1337.lotteryHelper.dto;

public class LotteryJoinDto {
    private Long userId;
    private Long lotteryId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

    @Override
    public String
    toString() {
        return "LotteryJoinDto{" +
                "userId=" + userId +
                ", lotteryId=" + lotteryId +
                '}';
    }

}
