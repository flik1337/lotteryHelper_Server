package cn.flik1337.lotteryHelper.dto;

import cn.flik1337.lotteryHelper.entity.Address;

public class AddressDto {
    private Address address;
    private Long lotteryJoinId;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getLotteryJoinId() {
        return lotteryJoinId;
    }

    public void setLotteryJoinId(Long lotteryJoinId) {
        this.lotteryJoinId = lotteryJoinId;
    }
}
