package cn.flik1337.lotteryHelper.vo;

import cn.flik1337.lotteryHelper.entity.Award;
import cn.flik1337.lotteryHelper.entity.LotteryJoin;
import cn.flik1337.lotteryHelper.entity.LotterySend;

//用户获奖情况
public class LotteryWinVO {

    private Boolean isUserWin;
    private Boolean hasSetAddress;
    private Award award;

    private LotterySend lotterySend;

    private LotteryJoin lotteryJoin;


    public LotteryWinVO( ) {
        this.isUserWin = false;
        this.hasSetAddress = false;
    }

    public Boolean getUserWin() {
        return isUserWin;
    }

    public void setUserWin(Boolean userWin) {
        isUserWin = userWin;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }


    public LotterySend getLotterySend() {
        return lotterySend;
    }

    public void setLotterySend(LotterySend lotterySend) {
        this.lotterySend = lotterySend;
    }

    public LotteryJoin getLotteryJoin() {
        return lotteryJoin;
    }

    public void setLotteryJoin(LotteryJoin lotteryJoin) {
        this.lotteryJoin = lotteryJoin;
    }

    public Boolean getHasSetAddress() {
        return hasSetAddress;
    }

    public void setHasSetAddress(Boolean hasSetAddress) {
        this.hasSetAddress = hasSetAddress;
    }
}
