package cn.flik1337.lotteryHelper;

import cn.flik1337.lotteryHelper.entity.User;
import cn.flik1337.lotteryHelper.mapper.UserMapper;
import cn.flik1337.lotteryHelper.service.ILotteryService;
import cn.flik1337.lotteryHelper.service.impl.LotteryServiceImpl;
import cn.flik1337.lotteryHelper.vo.LotteryVO;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ILotteryService lotteryService;

    @Test
    public void testUserMapper( ) {
//        Page<User> page = new Page<>(0,3);
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("uid",2);
//        userMapper.selectPage(page,queryWrapper);
//        System.out.println(page.getRecords());

        User user =  userMapper.selectById(1L);
        if (ObjectUtil.isNotNull(user)){
            System.out.println(user.toString());
        }else{
            System.out.println("null object");
        }
    }
    @Test
    public void testLotteryService(){
        LotteryVO lotteryVO = lotteryService.getLotteryVOById(1L);
        System.out.println(lotteryVO.getLottery());
        System.out.println(lotteryVO.getAwardList().size());
        System.out.println(lotteryVO.getAwardList().get(1).toString());
    }
}
