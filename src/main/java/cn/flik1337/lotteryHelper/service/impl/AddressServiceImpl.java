package cn.flik1337.lotteryHelper.service.impl;

import cn.flik1337.lotteryHelper.common.enums.LotterySendStatusEnum;
import cn.flik1337.lotteryHelper.dto.AddressDto;
import cn.flik1337.lotteryHelper.entity.Address;
import cn.flik1337.lotteryHelper.entity.LotterySend;
import cn.flik1337.lotteryHelper.mapper.AddressMapper;
import cn.flik1337.lotteryHelper.mapper.LotteryMapper;
import cn.flik1337.lotteryHelper.mapper.LotterySendMapper;
import cn.flik1337.lotteryHelper.service.IAddressService;
import cn.flik1337.lotteryHelper.service.ILotterySendService;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Address getAddressBySendId(Long sendId) {
        LambdaQueryWrapper<Address> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Address::getSendId,sendId);

        return addressMapper.selectOne(lambdaQueryWrapper);
    }
}
