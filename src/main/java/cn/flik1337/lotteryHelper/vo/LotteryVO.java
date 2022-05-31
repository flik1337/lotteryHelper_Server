package cn.flik1337.lotteryHelper.vo;

import cn.flik1337.lotteryHelper.entity.Award;
import cn.flik1337.lotteryHelper.entity.Lottery;
import cn.flik1337.lotteryHelper.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class LotteryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Lottery lottery;

    private List<Award> awardList;

    private User user;



    public LotteryVO(Lottery lottery, List<Award> awardList, User user) {
        this.lottery = lottery;
        this.awardList = awardList;
        this.user = user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
