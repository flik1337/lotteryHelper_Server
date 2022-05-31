package cn.flik1337.lotteryHelper.service;

import cn.flik1337.lotteryHelper.dto.AddressDto;
import cn.flik1337.lotteryHelper.entity.Address;
import cn.flik1337.lotteryHelper.entity.Award;
import cn.flik1337.lotteryHelper.entity.LotterySend;
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
public interface IAddressService extends IService<Address> {
    Address getAddressBySendId(Long sendId);
}
