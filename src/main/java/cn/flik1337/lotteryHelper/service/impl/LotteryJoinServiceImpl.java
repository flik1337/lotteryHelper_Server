package cn.flik1337.lotteryHelper.service.impl;

import cn.flik1337.lotteryHelper.common.enums.*;
import cn.flik1337.lotteryHelper.common.exception.Asserts;
import cn.flik1337.lotteryHelper.common.tool.LotteryUtil;
import cn.flik1337.lotteryHelper.common.tool.ShuffleTool;
import cn.flik1337.lotteryHelper.dto.LotteryJoinDto;
import cn.flik1337.lotteryHelper.entity.*;
import cn.flik1337.lotteryHelper.mapper.*;
import cn.flik1337.lotteryHelper.service.*;
import cn.flik1337.lotteryHelper.vo.LotteryAddressAllVO;
import cn.flik1337.lotteryHelper.vo.LotteryAddressVO;
import cn.flik1337.lotteryHelper.vo.LotteryDetailVO;
import cn.flik1337.lotteryHelper.vo.LotteryWinVO;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.flik1337.lotteryHelper.common.api.ResultCode.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-22
 */
@Service
public class LotteryJoinServiceImpl extends ServiceImpl<LotteryJoinMapper, LotteryJoin> implements ILotteryJoinService {
    @Autowired
    private LotteryJoinMapper lotteryJoinMapper;
    @Autowired
    private AwardMapper awardMapper;
    @Autowired
    private IAwardService awardService;
    @Autowired
    private RedisServiceImpl redisService;
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private LotteryMapper lotteryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILotterySendService lotterySendService;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IAddressService addressService;


    @Override
    public Integer saveLotteryJoin(LotteryJoinDto lotteryJoinDto) {
        // 检查是否已参与该活动
        boolean hasParticipated = hasParticipated(lotteryJoinDto.getUserId(),lotteryJoinDto.getLotteryId());

        if (hasParticipated){
            Asserts.fail(CAN_NOT_PARTICIPATE_AGAIN);
        }

        LotteryJoin lotteryJoin = new LotteryJoin();
        lotteryJoin.setlId(lotteryJoinDto.getLotteryId());
        lotteryJoin.setuId(lotteryJoinDto.getUserId());
        lotteryJoin.setJoinFrom(LotteryFromLimitEnum.ALL);
        lotteryJoin.setJoinTime(LocalDateTimeUtil.now());
        lotteryJoin.setJoinResult(LotteryJoinResultEnum.NOT_OPEN);

        // 插入抽奖记录
        Integer saveCount = lotteryJoinMapper.insert(lotteryJoin);
        if (ObjectUtil.isNull(saveCount)){
            Asserts.fail(FAIL_INSERT_LOTTERY_JOIN);
        }

        // 处理Redis对应记录
        String searchKey = LotteryUtil.generateLotterySearchKey(lotteryJoinDto.getLotteryId());
        System.out.println(searchKey);
        // 获取对应的key
        String targetKey = redisService.keys(searchKey).iterator().next();
        System.out.println(targetKey);
        // lottery_1_3
        Integer openType = Integer.valueOf(targetKey.split(LotteryUtil.getBreak())[2]);
        Long lotteryId = Long.valueOf(targetKey.split(LotteryUtil.getBreak())[1]);
        System.out.println(openType);
        // 判断开奖类型
        // 若到达人数开奖
        if (openType == 1){
            // 人数减一
            Long lastNum = redisService.decr(targetKey);
            System.out.println(lastNum);
            if (lastNum == 0){
                // 开奖
                openLottery(targetKey,lotteryJoinDto.getLotteryId());
                // 移除redis记录

            }
        }
        // 即开即中
        if (openType == 2){
            // 减少奖品数量
            Long lastNum = redisService.decr(targetKey);
            // 判断是否中奖
            openLotteryImmediately(lotteryId,lotteryJoin);
            if (lastNum == 0){
                // 设置抽奖活动为已经开奖

                lotteryService.updateLotteryStatus(lotteryId, LotteryStatusEnum.CLOSED);
            }
        }


        return saveCount;
    }

    // 查询该用户是否已经参加过该活动
    @Override
    public boolean hasParticipated(Long userId,Long lotteryId) {
        LambdaQueryWrapper<LotteryJoin> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(LotteryJoin::getuId,userId)
                .eq(LotteryJoin::getlId,lotteryId);
        Long count = lotteryJoinMapper.selectCount(lambdaQueryWrapper);
        if (count != 0){
            return true;
        }
        return false;

    }
    // 获取活动参与用户id列表
    @Override
    public List<Long> getLotteryJoinUserIdList(Long lotteryId){
        LambdaQueryWrapper<LotteryJoin> queryWrapper = new LambdaQueryWrapper();
        queryWrapper
                .eq(LotteryJoin::getlId,lotteryId)
                .orderByDesc(LotteryJoin::getJoinTime)
                .select(LotteryJoin::getuId);

        List<Long> lotteryJoinUserIdList = lotteryJoinMapper
                                            .selectObjs(queryWrapper)
                                            .stream()
                                            .map(item -> (Long)item)
                                            .collect(Collectors.toList());
        return lotteryJoinUserIdList;
    }

    private List<LotteryJoin> getLotteryJoinList(Long lotteryId){
        LambdaQueryWrapper<LotteryJoin> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(LotteryJoin::getlId,lotteryId);
        List<LotteryJoin> lotteryJoinList = lotteryJoinMapper.selectList(queryWrapper);
        return lotteryJoinList;
    }
    // 即抽即开过期

    @Override
    public void openImmediateLottery(Long lotteryId){
        // 设置抽奖活动为已经开奖
        lotteryService.updateLotteryStatus(lotteryId, LotteryStatusEnum.CLOSED);
    }

    // 到时间或者到人数开奖
    @Override
    public void openLottery(String redisKey,Long lotteryId){

        List<Award> awardList = awardService.getAwardListByLId(lotteryId);
        // 获取奖品总数
        Integer lotteryAwardNum = 0;
        for (Award award:awardList) {
            lotteryAwardNum += award.getaNumber();
        }

        // 获取参与人数列表
        List<LotteryJoin> lotteryJoinList = getLotteryJoinList(lotteryId);
        Integer joinNum = lotteryJoinList.size();

        // 若参与人数不大于奖品数量，则每人都中奖
        if (joinNum <= lotteryAwardNum){
            List<Long> shuffleAwardIdList = shuffleAward(awardList);

            for (int i = 0;i<joinNum ;i++){
                // 按序分配
                lotteryJoinList.get(i).setAid(shuffleAwardIdList.get(i));
                lotteryJoinList.get(i).setJoinResult(LotteryJoinResultEnum.WIN);
            }
            System.out.println(lotteryJoinList.get(0).toString());
            // 更新记录
            this.updateBatchById(lotteryJoinList);
        }else{
            // 若参与人数大于奖品数量，则前n个用户获奖
            // 打乱抽奖记录
            List<LotteryJoin> shuffleLotteryJoin = shuffleLotteryJoin(lotteryJoinList);
            // 扩展奖品id
            List<Long> awardIdExpandList = awardService.expandAwardId(awardList);
            for (int i = 0;i<shuffleLotteryJoin.size();i++){
                LotteryJoin lotteryJoin = shuffleLotteryJoin.get(i);
                if (i < awardIdExpandList.size()){
                    // 中
                    lotteryJoin.setAid(awardIdExpandList.get(i));
                    lotteryJoin.setJoinResult(LotteryJoinResultEnum.WIN);
                }else {
                    lotteryJoin.setJoinResult(LotteryJoinResultEnum.LOSE);
                }
            }
              this.updateBatchById(shuffleLotteryJoin);
        }

        // 设置抽奖活动为已经开奖
        lotteryService.updateLotteryStatus(lotteryId, LotteryStatusEnum.CLOSED);
        // 清除redis记录
        redisService.remove(redisKey);
    }
    // 获奖用户列表
    @Override
    public List<Long> getWinUserIdList(Long lotteryId) {
        LambdaQueryWrapper<LotteryJoin> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper
                .eq(LotteryJoin::getlId,lotteryId)
                .eq(LotteryJoin::getJoinResult,LotteryJoinResultEnum.WIN)
                .select(LotteryJoin::getuId);
        List<Long> winUserIdList = lotteryJoinMapper.selectObjs(lambdaQueryWrapper)
                .stream()
                .map(item -> (Long)item)
                .collect(Collectors.toList());

        return winUserIdList;
    }

    @Override
    public LotteryDetailVO getLotteryDetailVOById(Long lotteryId, Long userId){
        LotteryDetailVO lotteryDetailVO = new LotteryDetailVO();

        Lottery lottery = lotteryMapper.selectById(lotteryId);
        if (ObjectUtil.isNull(lottery)){
            Asserts.fail(BODY_NOT_MATCH);
        }
        // 查询对应的奖品列表
        List<Award> awardList = awardService.getAwardListByLId(lotteryId);
        // 创建者信息
        User creator = userMapper.selectById(lottery.getCreatorId());
        // 参与用户
        List<Long> userIdList = this.getLotteryJoinUserIdList(lotteryId);
        List<User> joinUserList = userService.getUserListByIds(userIdList);
        // 中奖用户
        List<Long> winUserIdList = this.getWinUserIdList(lotteryId);
        List<User> winUserList = userService.getUserListByIds(winUserIdList);

        // 加载获奖信息
        LotteryWinVO lotteryWinVO = new LotteryWinVO();

        // 判断用户是否中奖
        // 若传用户id，则表示查询该用户是否中奖
        if (ObjectUtil.isNotNull(userId)){
            lotteryDetailVO.setUserWin(false);
            for (Long id:winUserIdList){
                if (ObjectUtil.equals(id,userId)){
                    lotteryDetailVO.setUserWin(true);
                    break;
                }
            }

            if (lotteryDetailVO.getUserWin()){
                lotteryWinVO.setUserWin(true);
                LotteryJoin lotteryJoin = getLotteryJoinByLotteryAndUserId(lotteryId,userId);
                Award award = awardMapper.selectById(lotteryJoin.getAid());
                LotterySend lotterySend = lotterySendService.getByJoinId(lotteryJoin.getjId());

                lotteryWinVO.setAward(award);
                lotteryWinVO.setLotteryJoin(lotteryJoin);
                lotteryWinVO.setLotterySend(lotterySend);

                Boolean hasSetAddress = lotterySendService.hasSetAddress(lotteryJoin.getjId());
                lotteryWinVO.setHasSetAddress(hasSetAddress);

            }

        }

        lotteryDetailVO.setLottery(lottery);
        lotteryDetailVO.setAwardList(awardList);
        lotteryDetailVO.setCreator(creator);
        lotteryDetailVO.setJoinedUserList(joinUserList);
        lotteryDetailVO.setWinUserList(winUserList);
        lotteryDetailVO.setLotteryWinVO(lotteryWinVO);


        return lotteryDetailVO;
    }
    public void openLotteryImmediately(Long lotteryId,LotteryJoin lotteryJoin){
        List<Award> awardList = awardService.getAwardListByLId(lotteryId);

        // 获取奖品总数
        Integer lotteryAwardNum = 0;
        for (Award award:awardList) {
            lotteryAwardNum += award.getaNumber();
        }
        //  获取活动
        Lottery lottery = lotteryService.getById(lotteryId);
        boolean isWin = LotteryUtil.isOpenImmediateWin(lotteryAwardNum,lottery.getOpenNum());
        if (isWin){
            lotteryJoin.setJoinResult(LotteryJoinResultEnum.WIN);
            // 奖品缓存中取出
            Long awardId = awardService.popAwardTemp(lotteryId);
            lotteryJoin.setAid(awardId);
        }else{
            lotteryJoin.setJoinResult(LotteryJoinResultEnum.LOSE);
        }
        lotteryJoinMapper.updateById(lotteryJoin);

    }



    public List<Long> shuffleAward(List<Award> awardList){
        List<Long> awardIdList = new ArrayList<>();
        // id扩容
        List<Long> awardIdExpandList = awardService.expandAwardId(awardList);
        List<Long> shuffledAwardIdList = ShuffleTool.knuthShuffle(awardIdExpandList);
        return shuffledAwardIdList;
    }
    public List<LotteryJoin> shuffleLotteryJoin(List<LotteryJoin> lotteryJoinList){
        return ShuffleTool.knuthShuffleJoin(lotteryJoinList);
    }
    public LotteryJoin getLotteryJoinByLotteryAndUserId(Long lotteryId,Long userId){
        LambdaQueryWrapper<LotteryJoin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LotteryJoin::getlId,lotteryId)
                          .eq(LotteryJoin::getuId,userId);
        LotteryJoin lotteryJoin = lotteryJoinMapper.selectOne(lambdaQueryWrapper);
        return lotteryJoin;
    }
    @Override
    public LotteryAddressAllVO getLotteryAddressList(Long lotteryId){
        // 获取获奖用户列表
        // 中奖用户
        List<Long> winUserIdList = this.getWinUserIdList(lotteryId);
        List<User> winUserList = userService.getUserListByIds(winUserIdList);
        List<LotteryAddressVO> lotteryAddressVOS = new ArrayList<>();
        Integer setUserNum = 0;

        for (User user: winUserList){
            LotteryAddressVO lotteryAddressVO = new LotteryAddressVO();
            LotteryJoin lotteryJoin = getLotteryJoinByLotteryAndUserId(lotteryId,user.getUid());
            Long awardId = lotteryJoin.getAid();
            Award award = awardMapper.selectById(awardId);
            System.out.println(award.toString());
            LotterySend lotterySend = lotterySendService.getByJoinId(lotteryJoin.getjId());

            lotteryAddressVO.setUser(user);
            lotteryAddressVO.setAward(award);
            lotteryAddressVO.setLotteryJoin(lotteryJoin);
            lotteryAddressVO.setHasSetAddress(false);
            lotteryAddressVO.setLotterySend(lotterySend);

            if (ObjectUtil.isNotNull(lotterySend)){
                setUserNum++;
                Address address = addressService.getAddressBySendId(lotterySend.getSendId());
                lotteryAddressVO.setHasSetAddress(true);
                lotteryAddressVO.setAddress(address);
            }
            lotteryAddressVOS.add(lotteryAddressVO);

        }
        LotteryAddressAllVO lotteryAddressAllVO = new LotteryAddressAllVO();
        lotteryAddressAllVO.setLotteryAddressVO(lotteryAddressVOS);
        lotteryAddressAllVO.setSetUserNum(setUserNum);
        lotteryAddressAllVO.setTotalUserNum(winUserList.size());

        return lotteryAddressAllVO;

    }


}
