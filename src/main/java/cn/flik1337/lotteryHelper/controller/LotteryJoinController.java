package cn.flik1337.lotteryHelper.controller;


import cn.flik1337.lotteryHelper.common.api.CommonResult;
import cn.flik1337.lotteryHelper.dto.LotteryJoinDto;
import cn.flik1337.lotteryHelper.service.ILotteryJoinService;
import cn.flik1337.lotteryHelper.service.impl.LotteryJoinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import retrofit2.http.POST;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author flik1337
 * @since 2022-04-22
 */
@RestController
@RequestMapping("/lotteryHelper/lottery-join")
public class LotteryJoinController {
     @Autowired
     private ILotteryJoinService lotteryJoinService;

     @PostMapping
     public CommonResult saveLotteryJoin(@RequestBody LotteryJoinDto lotteryJoinDto){
         lotteryJoinService.saveLotteryJoin(lotteryJoinDto);
         return CommonResult.success();
     }
    @GetMapping("/participated")
    public CommonResult hasParticipated( LotteryJoinDto lotteryJoinDto){

        return CommonResult.success(lotteryJoinService.hasParticipated(lotteryJoinDto.getUserId(),lotteryJoinDto.getLotteryId()));
    }
    @GetMapping()
    public CommonResult getLotteryById(@RequestParam("lotteryId") Long lotteryId,@RequestParam(value = "userId",required = false)Long userId){
        return CommonResult.success(lotteryJoinService.getLotteryDetailVOById(lotteryId,userId));
    }
    @GetMapping("/address")
    public CommonResult getLotteryAddress(@RequestParam("lotteryId") Long lotteryId){
        return CommonResult.success(lotteryJoinService.getLotteryAddressList(lotteryId));
    }


}
