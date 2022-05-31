package cn.flik1337.lotteryHelper.component;



import cn.flik1337.lotteryHelper.common.enums.LotteryOpenTypeEnum;
import cn.flik1337.lotteryHelper.service.ILotteryJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

public class KeyExpiredListener extends KeyExpirationEventMessageListener {
    @Autowired
    private ILotteryJoinService lotteryJoinService;

    private static final String SPLIT = "_";
    private static final String LOTTERY_IDENTITY = "lottery";
    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //String channel = new String(message.getChannel(),StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        //lottery_id
        // 判断是否是lottery类型的key
        if (!key.contains(LOTTERY_IDENTITY)){
            return;
        }
        String[] keySplits = key.split(SPLIT);
        Long lotteryId = Long.parseLong(keySplits[1]);
        Integer openType = Integer.parseInt(keySplits[2]);

        System.out.println("监听到活动"+lotteryId+"过期");
        if (openType == LotteryOpenTypeEnum.OPEN_BY_INSTANT.getCode()){
            lotteryJoinService.openImmediateLottery(lotteryId);
        }else{
            lotteryJoinService.openLottery(key,lotteryId);
        }


    }
}
