package cn.flik1337.lotteryHelper.controller;

import cn.flik1337.lotteryHelper.service.ILotteryService;
import cn.flik1337.lotteryHelper.vo.LotteryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ILotteryService lotteryService;



}
