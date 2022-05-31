package cn.flik1337.lotteryHelper.vo;

import cn.flik1337.lotteryHelper.entity.Award;
import cn.flik1337.lotteryHelper.entity.Lottery;
import cn.flik1337.lotteryHelper.entity.User;

import java.util.List;

public class LotteryDetailVO {
    // 活动详情
    private Lottery lottery;
    // 奖品列表
    private List<Award> awardList;
    // 活动发起者
    private User creator;
    // 参与用户列表
    private List<User> joinedUserList;
    // 获奖用户列表
    private List<User> winUserList;
    // 该用户是否中奖
    private Boolean isUserWin;

    private LotteryWinVO lotteryWinVO;

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getJoinedUserList() {
        return joinedUserList;
    }

    public void setJoinedUserList(List<User> joinedUserList) {
        this.joinedUserList = joinedUserList;
    }

    public List<User> getWinUserList() {
        return winUserList;
    }

    public void setWinUserList(List<User> winUserList) {
        this.winUserList = winUserList;
    }

    public Boolean getUserWin() {
        return isUserWin;
    }

    public void setUserWin(Boolean userWin) {
        isUserWin = userWin;
    }

    public LotteryWinVO getLotteryWinVO() {
        return lotteryWinVO;
    }

    public void setLotteryWinVO(LotteryWinVO lotteryWinVO) {
        this.lotteryWinVO = lotteryWinVO;
    }
}
