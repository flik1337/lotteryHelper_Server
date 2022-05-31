package cn.flik1337.lotteryHelper.service.impl;

import cn.flik1337.lotteryHelper.common.api.WechatApi;
import cn.flik1337.lotteryHelper.common.enums.*;
import cn.flik1337.lotteryHelper.common.exception.Asserts;
import cn.flik1337.lotteryHelper.common.tool.LotteryUtil;
import cn.flik1337.lotteryHelper.dto.AwardDto;
import cn.flik1337.lotteryHelper.dto.LotteryDto;
import cn.flik1337.lotteryHelper.entity.Award;
import cn.flik1337.lotteryHelper.entity.LotteryJoin;
import cn.flik1337.lotteryHelper.entity.User;
import cn.flik1337.lotteryHelper.mapper.LotteryJoinMapper;
import cn.flik1337.lotteryHelper.mapper.UserMapper;
import cn.flik1337.lotteryHelper.service.*;
import cn.flik1337.lotteryHelper.vo.LotteryDetailVO;
import cn.flik1337.lotteryHelper.vo.LotteryVO;
import cn.flik1337.lotteryHelper.entity.Lottery;
import cn.flik1337.lotteryHelper.mapper.LotteryMapper;
import cn.flik1337.lotteryHelper.vo.TokenObject;
import cn.flik1337.lotteryHelper.vo.UserSession;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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
 * @since 2022-04-18
 */
@Service
public class LotteryServiceImpl extends ServiceImpl<LotteryMapper, Lottery> implements ILotteryService {
    @Autowired
    private LotteryMapper lotteryMapper;
    @Autowired
    private LotteryJoinMapper lotteryJoinMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IAwardService awardService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private WechatApi wechatApi;
    @Value("${wechat.appid}")
    private  String APPID;
    @Value("${wechat.secret}")
    private  String SECRET;

    private  String[] levelName = {"零","一","二","三","四","五","六"};
    @Override
    public LotteryVO getLotteryVOById(Long lid) {
        System.out.println(lid);
        Lottery lottery = lotteryMapper.selectById(lid);
        if (ObjectUtil.isNull(lottery)){
            Asserts.fail(BODY_NOT_MATCH);
        }
        // 查询对应的奖品列表
        List<Award> awardList = awardService.getAwardListByLId(lid);
        User user = userMapper.selectById(lottery.getCreatorId());
        LotteryVO lotteryVO = new LotteryVO(lottery,awardList,user);
        // 加载参与用户列表

        return lotteryVO;
    }


    @Override
    public void updateLotteryStatus(Long lotteryId,LotteryStatusEnum lotteryStatusEnum){
        LambdaUpdateWrapper<Lottery> updateWrapper = new LambdaUpdateWrapper();
        updateWrapper
                .eq(Lottery::getlId,lotteryId)
                .set(Lottery::getlStatus,lotteryStatusEnum);
        lotteryMapper.update(null,updateWrapper);
    };
    // 根据活动列表，生成活动Vo列表
    public List<LotteryVO> getLotteryVoListByLotteryList(List<Lottery> lotteryList){
        List<LotteryVO> lotteryVOList = new ArrayList<>();
        // 遍历抽奖活动，同时加载对应奖品
        lotteryList.forEach(lottery -> {
            List<Award> awardList = awardService.getAwardListByLId(lottery.getlId());
            User user = userMapper.selectById(lottery.getCreatorId());
            LotteryVO lotteryVO = new LotteryVO(lottery,awardList,user);
            lotteryVOList.add(lotteryVO);
        });

        return lotteryVOList;

    }
    // 用户参加过的活动列表
    @Override
    public List<LotteryVO> getUserJoinedLotteryVOList(Long userId, Integer lotteryJoinResult){
        LambdaQueryWrapper<LotteryJoin> lotteryJoinLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 参与记录

        System.out.println(ObjectUtil.isNull(lotteryJoinResult));
        lotteryJoinLambdaQueryWrapper
                .eq(LotteryJoin::getuId,userId)
                .orderByDesc(LotteryJoin::getJoinTime)
                .select(LotteryJoin::getlId);
        if (ObjectUtil.isNotNull(lotteryJoinResult)){
            lotteryJoinLambdaQueryWrapper.eq(ObjectUtil.isNotNull(lotteryJoinResult),LotteryJoin::getJoinResult,LotteryJoinResultEnum.getValue(lotteryJoinResult));
        }

        List<Long> lotteryIdList = lotteryJoinMapper
                .selectObjs(lotteryJoinLambdaQueryWrapper)
                .stream()
                .map(item -> (Long)item)
                .collect(Collectors.toList());

        if (lotteryIdList.size() == 0){
            return new ArrayList<>();
        }
        LambdaQueryWrapper<Lottery> lotteryLambdaQueryWrapper = new LambdaQueryWrapper<>();

        StringBuilder builder = new StringBuilder();
        builder.append("order by field(l_id,");
        int length = lotteryIdList.size();
        for(int i= 0; i<length; i++){
            if(i==0){
                builder.append(lotteryIdList.get(i));
            }else{
                builder.append(",")
                        .append(lotteryIdList.get(i));
            }
            if (i==length-1){
                builder.append(")");
            }
        }

        lotteryLambdaQueryWrapper
                .in(Lottery::getlId,lotteryIdList)
                .last(builder.toString());
        List<Lottery> lotteryList = lotteryMapper.selectList(lotteryLambdaQueryWrapper);
        List<LotteryVO> lotteryVOList = getLotteryVoListByLotteryList(lotteryList);

        return lotteryVOList;
    }

    @Override
    public List<LotteryVO> getUserCreatedLotteryVOList(Long userId) {
        LambdaQueryWrapper<Lottery> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Lottery::getCreatorId,userId)
                .orderByDesc(Lottery::getCreateTime);
        List<Lottery> lotteryList = lotteryMapper.selectList(queryWrapper);
        List<LotteryVO> lotteryVOList = getLotteryVoListByLotteryList(lotteryList);
        return lotteryVOList;
    }

    @Override
    public List<LotteryVO> getIndexLotteryVOList(int pageNum,int pageSize) {
        LambdaQueryWrapper<Lottery> queryWrapper = new LambdaQueryWrapper();
        // 以降序查找状态为待开始的抽奖活动
        queryWrapper
                .eq(Lottery::getlStatus, LotteryStatusEnum.READY.getCode())
                .orderByDesc(Lottery::getCreateTime);
        IPage<Lottery> lotteryPage = new Page<>(pageNum,pageSize);
        lotteryMapper.selectPage(lotteryPage,queryWrapper);

        // 处理currents
        List<Lottery> lotteryList = lotteryPage.getRecords();


        List<LotteryVO> lotteryVOList = getLotteryVoListByLotteryList(lotteryList);
        return lotteryVOList;
    }
    //private String generateRuleName(Integer level,String awardName,)
    // 转换dto类型
    public Award transAwardDto(AwardDto awardDto,Long lid){
        Award award = new Award();
        award.setaCover(awardDto.getCover());
        award.setaLevel(AwardLevelEnum.getValue(awardDto.getLevel()));
        award.setaMoney(awardDto.getCountMoney());
        award.setaNumber(awardDto.getNumber());
        award.setaName(awardDto.getName());
        award.setaType(AwardTypeEnum.getValue(awardDto.getType()));
        award.setlId(lid);
        award.setExchangeCode(awardDto.getExchangeCode());
        award.setReceiveType(awardDto.getReceiveTypeCode());
        award.setRuleName(String.valueOf(levelName[awardDto.getLevel()]).concat("等奖：").concat(awardDto.getName()).concat("x").concat(String.valueOf(awardDto.getNumber())).concat("份"));
        return award;
    }
    //创建抽奖活动，返回活动id
    @Override
    public Long saveLottery(LotteryDto lotteryDto) {
        // 批量插入奖品信息
        List<AwardDto> awardDtoList = lotteryDto.getAwardList();
        Lottery lottery = new Lottery();
        // todo
        lottery.setCreatorId(3L);
        lottery.setCreateTime(LocalDateTimeUtil.now());
        lottery.setlType(LotteryTypeEnum.GENERAL);
        lottery.setlStatus(LotteryStatusEnum.READY);
        lottery.setOpenNum(lotteryDto.getOpenNumber());
        lottery.setOpenTime(LocalDateTimeUtil.parse(lotteryDto.getOpenTime(), DatePattern.NORM_DATETIME_PATTERN));
        lottery.setOpenType(LotteryOpenTypeEnum.getValue(lotteryDto.getOpenType()));

        Integer saveLotteryCount = lotteryMapper.insert(lottery);
        if (saveLotteryCount == 0){
            Asserts.fail(FAIL_INSERT_LOTTERY);

        }
        List<Award> awardList = new ArrayList<>();

        awardDtoList.forEach(awardDto -> {
            awardList.add(transAwardDto(awardDto,lottery.getlId()));
        });

        // 批量保存奖品
        awardService.saveBatch(awardList);
        if (ObjectUtil.isNull(saveLotteryCount)){
            Asserts.fail(FAIL_INSERT_AWARD);

        }
        // 存储活动redis记录

        // 按人数开奖
        if (lottery.getOpenType() == LotteryOpenTypeEnum.OPEN_BY_NUM){
            setLotteryMaxUserNum(lottery.getlId(),lottery.getOpenNum(),lottery.getOpenTime(),lottery.getOpenType().getCode());
        }else{
            setLotteryOnTimeAndNum(lottery.getlId(),lottery.getOpenTime(),lottery.getOpenNum(),lottery.getOpenType().getCode());
            if (lottery.getOpenType() == LotteryOpenTypeEnum.OPEN_BY_INSTANT){
                // 即开即中类型活动，须设置奖品缓存
                awardService.pushAwardTempList(lottery.getlId() ,awardService.expandAwardId(awardList));
            }
        }
        System.out.println(lottery.getlId());
        return lottery.getlId();
    }

    // 创建活动后，设置活动开奖时间倒计时（到达时间开奖、即抽即开）
    @Override
    public void setLotteryOnTimeAndNum(Long lotteryId,LocalDateTime openTime,Integer openNum,Integer openType){
        // 创建记录 key lottery_1_3
        String lotteryKey = LotteryUtil.generateLotteryKey(lotteryId,openType);
        // 存储对应的openType
        redisService.set(lotteryKey,String.valueOf(openNum));
        // 倒计时 millSeconds
        LocalDateTime nowTimeLocal = LocalDateTimeUtil.now();
        Duration between = LocalDateTimeUtil.between(nowTimeLocal, openTime);
        redisService.expire(lotteryKey,between.toMillis());

    }


    // 创建活动后，设置活动开奖人数(到达人数开奖)
    @Override
    public void setLotteryMaxUserNum(Long lotteryId,Integer openNum,LocalDateTime openTime,Integer openType){
        // 创建记录 key lottery_1_3
        String lotteryKey = LotteryUtil.generateLotteryKey(lotteryId,openType);
        LocalDateTime nowTimeLocal = LocalDateTimeUtil.now();
        Duration between = LocalDateTimeUtil.between(nowTimeLocal, openTime);
        // 设置最多人数
        redisService.set(lotteryKey,String.valueOf(openNum));
        redisService.expire(lotteryKey,between.toMillis());
    }



    // 获取活动奖品总数
    @Override
    public Integer getLotteryAwardNum(Long lotteryId){
        List<Award> awardList = awardService.getAwardListByLId(lotteryId);
        Integer lotteryAwardNum = 0;
        for (Award award:awardList){
            lotteryAwardNum += award.getaNumber();
        }
        return lotteryAwardNum;
    }

    // 生成活动二维码
    public String getLotteryQrCode(Long lotteryId){
        // 获取接口凭证
        JSONObject authObject = wechatApi.getAccessToken(APPID,SECRET,"client_credential");
        TokenObject tokenObject = JSONUtil.toBean(authObject, TokenObject.class);

        if (ObjectUtil.isNotNull(tokenObject.getErrcode())){
            Asserts.fail(SESSION_WRONG);
        }
        String token = tokenObject.getAccessToken();
        System.out.println(token);

        String scene = "lotteryId="+lotteryId;
        String page = "pages/join/join";
        Boolean checkPath = false;
        String envVersion = "develop";
        Integer width = 280;

        JSONObject qrCodeParam = JSONUtil.createObj();
        qrCodeParam.set("access_token",token);
        qrCodeParam.set("scene",scene);
        qrCodeParam.set("page",page);
        qrCodeParam.set("check_path",checkPath);
        qrCodeParam.set("env_version",envVersion);
        qrCodeParam.set("width",width);
        System.out.println(qrCodeParam.toStringPretty());

        String codeObjectStr = wechatApi.getUnlimitedCode(token,scene,page,checkPath,envVersion,width);
        System.out.println(codeObjectStr);
        return codeObjectStr;
    }
}
