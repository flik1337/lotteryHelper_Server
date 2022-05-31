package cn.flik1337.lotteryHelper.service;

import cn.flik1337.lotteryHelper.dto.AddressDto;
import cn.flik1337.lotteryHelper.dto.LotterySendDto;
import cn.flik1337.lotteryHelper.entity.LotterySend;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author flik1337
 * @since 2022-05-09
 */
public interface ILotterySendService extends IService<LotterySend> {
    LotterySend getByJoinId(Long lotteryJoinId);
    LotterySendDto setLotterySend(AddressDto addressDto);
    Boolean hasSetAddress(Long lotteryJoinId);


}
