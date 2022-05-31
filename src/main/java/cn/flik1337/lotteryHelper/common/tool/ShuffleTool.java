package cn.flik1337.lotteryHelper.common.tool;

import cn.flik1337.lotteryHelper.entity.LotteryJoin;
import cn.hutool.core.util.RandomUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
public class ShuffleTool {

    public static List<Long> fisherYatesShuffle(List<Long> arrays){
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < arrays.size(); i++){
            int k = RandomUtil.randomInt(0, arrays.size());
            result.add(arrays.get(k));
            arrays.remove(k);
            i--;
        }
        return result;
    }
    public static List<Long> knuthShuffle(List<Long> arrays){
        int arrayLength = arrays.size();
        for(int i = arrayLength-1; i >= 0; i--){
            int k = RandomUtil.randomInt(0, i+1);
            Long temp = arrays.get(i);
            arrays.set(i,arrays.get(k));
            arrays.set(k,temp);
        }
        return arrays;
    }
    public static List<LotteryJoin> knuthShuffleJoin(List<LotteryJoin> arrays){
        int arrayLength = arrays.size();
        for(int i = arrayLength-1; i >= 0; i--){
            int k = RandomUtil.randomInt(0, i+1);
            LotteryJoin temp = arrays.get(i);
            arrays.set(i,arrays.get(k));
            arrays.set(k,temp);
        }
        return arrays;
    }



}
