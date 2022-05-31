package cn.flik1337.lotteryHelper.controller;


import cn.flik1337.lotteryHelper.common.api.CommonResult;
import cn.flik1337.lotteryHelper.dto.AddressDto;
import cn.flik1337.lotteryHelper.service.IAddressService;
import cn.flik1337.lotteryHelper.service.ILotterySendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author flik1337
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/lotteryHelper/lottery-send")
public class LotterySendController {
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ILotterySendService lotterySendService;

    @PostMapping
    public CommonResult setAddress(@RequestBody AddressDto addressDto){
        return CommonResult.success(lotterySendService.setLotterySend(addressDto));
    }
}
