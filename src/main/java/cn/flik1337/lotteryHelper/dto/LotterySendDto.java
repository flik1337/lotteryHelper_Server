package cn.flik1337.lotteryHelper.dto;

import cn.flik1337.lotteryHelper.entity.Address;
import cn.flik1337.lotteryHelper.entity.LotterySend;

public class LotterySendDto {
    private Address address;
    private LotterySend lotterySend;
    private Long lotteryJoinId;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LotterySend getLotterySend() {
        return lotterySend;
    }

    public void setLotterySend(LotterySend lotterySend) {
        this.lotterySend = lotterySend;
    }

    public Long getLotteryJoinId() {
        return lotteryJoinId;
    }

    public void setLotteryJoinId(Long lotteryJoinId) {
        this.lotteryJoinId = lotteryJoinId;
    }
}
