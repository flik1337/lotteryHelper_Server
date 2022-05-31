package cn.flik1337.lotteryHelper.controller;


import cn.flik1337.lotteryHelper.common.api.CommonResult;
import cn.flik1337.lotteryHelper.common.enums.LotteryJoinResultEnum;
import cn.flik1337.lotteryHelper.dto.LotteryDto;
import cn.flik1337.lotteryHelper.service.ILotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
@RestController
@RequestMapping("/lotteryHelper/lottery")
public class LotteryController {
    @Autowired
    private ILotteryService lotteryService;

    @PostMapping("/save")
    public CommonResult saveLottery(@RequestBody LotteryDto lotteryDto){
        return CommonResult.success(lotteryService.saveLottery(lotteryDto));
    }

    @GetMapping("/index/list")
    public CommonResult getIndexLotteryList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        return CommonResult.success(lotteryService.getIndexLotteryVOList(pageNum,pageSize));
    }
    @GetMapping("/user/list")
    public CommonResult getUserLotteryList(@RequestParam("userId") Long userId,
                                           @RequestParam(value = "lotteryJoinResult",required = false)Integer lotteryJointResult){

        return CommonResult.success(lotteryService.getUserJoinedLotteryVOList(userId, lotteryJointResult));
    }
    @GetMapping("/user/createdList")
    public CommonResult getUserCreatedLotteryList(@RequestParam("userId") Long userId){

        return CommonResult.success(lotteryService.getUserCreatedLotteryVOList(userId));
    }
    @GetMapping("/qrcode")
    public CommonResult getLotteryQrCode(@RequestParam("lotteryId") Long lotteryId){

        return CommonResult.success(lotteryService.getLotteryQrCode(lotteryId));
    }

}
