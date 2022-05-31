package cn.flik1337.lotteryHelper.service.impl;

import cn.flik1337.lotteryHelper.common.enums.LotterySendStatusEnum;
import cn.flik1337.lotteryHelper.dto.AddressDto;
import cn.flik1337.lotteryHelper.dto.LotterySendDto;
import cn.flik1337.lotteryHelper.entity.Address;
import cn.flik1337.lotteryHelper.entity.LotterySend;
import cn.flik1337.lotteryHelper.mapper.AddressMapper;
import cn.flik1337.lotteryHelper.mapper.LotterySendMapper;
import cn.flik1337.lotteryHelper.service.IAddressService;
import cn.flik1337.lotteryHelper.service.ILotterySendService;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author flik1337
 * @since 2022-05-09
 */
@Service
public class LotterySendServiceImpl extends ServiceImpl<LotterySendMapper, LotterySend> implements ILotterySendService {
    @Autowired
    LotterySendMapper lotterySendMapper;
    @Autowired
    private IAddressService addressService;

    @Override
    public LotterySend getByJoinId(Long lotteryJoinId) {
        LambdaQueryWrapper<LotterySend> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LotterySend::getLotteryJoinId,lotteryJoinId);
        LotterySend lotterySend = lotterySendMapper.selectOne(lambdaQueryWrapper);
        return lotterySend;
    }

    private LotterySend getLotterySendByLotteryJoinId(Long lotteryJoinId){
        LambdaQueryWrapper<LotterySend> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LotterySend::getLotteryJoinId,lotteryJoinId);
        return lotterySendMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public LotterySendDto setLotterySend(AddressDto addressDto) {

        // 更新发货记录
        LotterySend lotterySend = new LotterySend();

        lotterySend.setLotteryJoinId(addressDto.getLotteryJoinId());
        lotterySend.setSendStatus(LotterySendStatusEnum.NOT_SEND);
        lotterySend.setCreateTime(LocalDateTimeUtil.now());

        LambdaUpdateWrapper<LotterySend> lambdaUpdateWrapper = new LambdaUpdateWrapper();
        lambdaUpdateWrapper.eq(LotterySend::getLotteryJoinId,addressDto.getLotteryJoinId());

        this.saveOrUpdate(lotterySend,lambdaUpdateWrapper);
        //重新获取LotterySend（解决update不返回id的问题）
        LotterySend updatedLotterySend = this.getLotterySendByLotteryJoinId(addressDto.getLotteryJoinId());

        // 更新地址
        Address address = addressDto.getAddress();
        address.setSendId(updatedLotterySend.getSendId());
        LambdaUpdateWrapper<Address> addressUpdateWrapper = new LambdaUpdateWrapper<>();
        addressUpdateWrapper.eq(Address::getSendId,updatedLotterySend.getSendId());
        addressService.saveOrUpdate(address,addressUpdateWrapper);

        LotterySendDto lotterySendDto = new LotterySendDto();
        lotterySendDto.setAddress(address);
        lotterySendDto.setLotterySend(updatedLotterySend);
        lotterySendDto.setLotteryJoinId(addressDto.getLotteryJoinId());

        return lotterySendDto;
    }
    @Override
    public Boolean hasSetAddress(Long lotteryJoinId){
        LambdaQueryWrapper<LotterySend> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(LotterySend::getLotteryJoinId,lotteryJoinId);

        LotterySend lotterySend = lotterySendMapper.selectOne(queryWrapper);
        return ObjectUtil.isNotNull(lotterySend);
    }
}
