package cn.flik1337.lotteryHelper;

import cn.flik1337.lotteryHelper.common.tool.ShuffleTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class ShuffleTest {


    @Test
    void fisherYatesShuffleTest(){
        List<Long> arrays = new ArrayList<>();
        arrays.add(1L);
        arrays.add(2L);
        arrays.add(3L);
        arrays.add(5L);
        ShuffleTool.fisherYatesShuffle(arrays);
    }
    @Test
    void knuthShuffleTest(){
        List<Long> arrays = new ArrayList<>();
        arrays.add(1L);
        arrays.add(2L);
        arrays.add(3L);
        arrays.add(4L);
        arrays.add(5L);

        ShuffleTool.knuthShuffle(arrays);
    }
}
