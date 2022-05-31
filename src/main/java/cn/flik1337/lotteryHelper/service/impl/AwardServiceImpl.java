package cn.flik1337.lotteryHelper.service.impl;

import cn.flik1337.lotteryHelper.common.tool.LotteryUtil;
import cn.flik1337.lotteryHelper.entity.Award;
import cn.flik1337.lotteryHelper.mapper.AwardMapper;
import cn.flik1337.lotteryHelper.service.IAwardService;
import cn.flik1337.lotteryHelper.service.RedisService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
@Service
public class AwardServiceImpl extends ServiceImpl<AwardMapper, Award> implements IAwardService {
    @Autowired
    private AwardMapper awardMapper;
    @Autowired
    private RedisService redisService;


    @Override
    public List<Award> getAwardListByLId(Long lotteryId) {
        LambdaQueryWrapper<Award> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(ObjectUtil.isNotNull(lotteryId),Award::getlId,lotteryId);
        List<Award> awardList = awardMapper.selectList(lambdaQueryWrapper);
        return awardList;
    }

    @Override
    public Integer saveAwardList(List<Award> awardList) {
        return null;
    }

    @Override
    public List<Long> expandAwardId(List<Award> awardList){
        List<Long> awardIdExpandList = new ArrayList<>();
        for (Award award:awardList){
            for (int i = 0;i<award.getaNumber();i++){
                awardIdExpandList.add(award.getaId());
            }
        }
        return awardIdExpandList;
    }
    // 存储奖品redis缓存
    @Override
    public void pushAwardTempList(Long lotteryId ,List<Long> awardIdList){
        List<String> idList = new ArrayList<>();
        for (Long awardId : awardIdList){
            idList.add(String.valueOf(awardId));
        }
        redisService.setList(LotteryUtil.generateLotteryAwardKey(lotteryId),idList);
    }
    @Override
    public Long popAwardTemp(Long lotteryId){
        String popKey = LotteryUtil.generateLotteryAwardKey(lotteryId);
        String popId = redisService.popListItem(popKey);
        System.out.println(popId);
        return Long.parseLong(popId);
    }

}
