package cn.flik1337.lotteryHelper.common.tool;

import cn.flik1337.lotteryHelper.entity.Lottery;
import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

@Component
public class LotteryUtil {
    private static final String LOTTERY_PREFIX = "lottery";
    private static final String AWARD_LOTTERY_PREFIX = "award_lottery";
    private static final String KEY_BREAK = "_";
    private static final String PLACE = "*";


    public static String generateLotteryKey(Long lotteryId,Integer openType){
        String lotteryKey = LOTTERY_PREFIX
                .concat(KEY_BREAK)
                .concat(String.valueOf(lotteryId))
                .concat(KEY_BREAK)
                .concat(String.valueOf(openType));
        return lotteryKey;
    }
    public static String generateLotterySearchKey(Long lotteryId){
        String lotterySearchKey = LOTTERY_PREFIX
                .concat(KEY_BREAK)
                .concat(String.valueOf(lotteryId))
                .concat(KEY_BREAK)
                .concat(PLACE);
        return lotterySearchKey;
    }
    public static String generateLotteryAwardKey(Long lotteryId){
        String lotterySearchKey = AWARD_LOTTERY_PREFIX
                .concat(KEY_BREAK)
                .concat(String.valueOf(lotteryId));
        return lotterySearchKey;
    }

    public static String getPlace(){
        return PLACE;
    }
    public static String getBreak(){
        return KEY_BREAK;
    }

    public static boolean isOpenImmediateWin(Integer awardCount,Integer peopleNum){
        if (peopleNum < awardCount){
            return true;
        }else{
            // [0,k)
            Integer k = RandomUtil.randomInt(0, peopleNum);
            if ( k  < awardCount){
                return true;
            }
            return false;
        }

    }

}
