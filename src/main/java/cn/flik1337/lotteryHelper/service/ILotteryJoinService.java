package cn.flik1337.lotteryHelper.service;

import cn.flik1337.lotteryHelper.dto.LotteryJoinDto;
import cn.flik1337.lotteryHelper.entity.LotteryJoin;
import cn.flik1337.lotteryHelper.vo.LotteryAddressAllVO;
import cn.flik1337.lotteryHelper.vo.LotteryAddressVO;
import cn.flik1337.lotteryHelper.vo.LotteryDetailVO;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-22
 */

public interface ILotteryJoinService extends IService<LotteryJoin> {

    Integer saveLotteryJoin(LotteryJoinDto lotteryJoinDto);
    boolean hasParticipated(Long userId,Long lotteryId);
    void openImmediateLottery(Long lotteryId);
    List<Long> getLotteryJoinUserIdList(Long lotteryId);
    public void openLottery(String redisKey,Long lotteryId);
    List<Long> getWinUserIdList(Long lotteryId);
    LotteryDetailVO getLotteryDetailVOById(Long lotteryId, Long userId);
    LotteryAddressAllVO getLotteryAddressList(Long lotteryId);

}
