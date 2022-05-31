package cn.flik1337.lotteryHelper.vo;

import java.util.List;

public class LotteryAddressAllVO {
    private List<LotteryAddressVO> lotteryAddressVO;
    private Integer totalUserNum;
    private Integer setUserNum;

    public List<LotteryAddressVO> getLotteryAddressVO() {
        return lotteryAddressVO;
    }

    public void setLotteryAddressVO(List<LotteryAddressVO> lotteryAddressVO) {
        this.lotteryAddressVO = lotteryAddressVO;
    }

    public Integer getTotalUserNum() {
        return totalUserNum;
    }

    public void setTotalUserNum(Integer totalUserNum) {
        this.totalUserNum = totalUserNum;
    }

    public Integer getSetUserNum() {
        return setUserNum;
    }

    public void setSetUserNum(Integer setUserNum) {
        this.setUserNum = setUserNum;
    }
}
