package cn.flik1337.lotteryHelper.service;

import cn.flik1337.lotteryHelper.common.enums.LotteryStatusEnum;
import cn.flik1337.lotteryHelper.dto.LotteryDto;
import cn.flik1337.lotteryHelper.vo.LotteryDetailVO;
import cn.flik1337.lotteryHelper.vo.LotteryVO;
import cn.flik1337.lotteryHelper.entity.Lottery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
public interface ILotteryService extends IService<Lottery> {
    LotteryVO getLotteryVOById(Long lid);
    void updateLotteryStatus(Long lotteryId, LotteryStatusEnum lotteryStatusEnum);
    List<LotteryVO> getIndexLotteryVOList(int pageNum,int pageSize);
    List<LotteryVO> getUserJoinedLotteryVOList(Long userId, Integer lotteryJoinResult);
    List<LotteryVO> getUserCreatedLotteryVOList(Long userId);
    Long saveLottery(LotteryDto lotteryDto);
    void setLotteryOnTimeAndNum(Long lotteryId,LocalDateTime openTime,Integer openNum,Integer openType);
    void setLotteryMaxUserNum(Long lotteryId,Integer openNum,LocalDateTime openTime,Integer openType);
    Integer getLotteryAwardNum(Long lotteryId);
    String getLotteryQrCode(Long lotteryId);

}
