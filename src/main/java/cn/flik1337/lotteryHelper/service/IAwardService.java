package cn.flik1337.lotteryHelper.service;

import cn.flik1337.lotteryHelper.entity.Award;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
public interface IAwardService extends IService<Award> {
    List<Award> getAwardListByLId(Long lotteryId);
    Integer saveAwardList(List<Award> awardList);
    List<Long> expandAwardId(List<Award> awardList);
    void pushAwardTempList(Long lotteryId ,List<Long> awardIdList);
    Long popAwardTemp(Long lotteryId);

}
