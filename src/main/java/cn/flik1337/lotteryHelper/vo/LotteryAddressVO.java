package cn.flik1337.lotteryHelper.vo;

import cn.flik1337.lotteryHelper.entity.*;

public class LotteryAddressVO {
    private User user;
    private Award award;
    private LotteryJoin lotteryJoin;
    private LotterySend lotterySend;
    private Address address;
    private Boolean hasSetAddress;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public LotteryJoin getLotteryJoin() {
        return lotteryJoin;
    }

    public void setLotteryJoin(LotteryJoin lotteryJoin) {
        this.lotteryJoin = lotteryJoin;
    }

    public LotterySend getLotterySend() {
        return lotterySend;
    }

    public void setLotterySend(LotterySend lotterySend) {
        this.lotterySend = lotterySend;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getHasSetAddress() {
        return hasSetAddress;
    }

    public void setHasSetAddress(Boolean hasSetAddress) {
        this.hasSetAddress = hasSetAddress;
    }


}
