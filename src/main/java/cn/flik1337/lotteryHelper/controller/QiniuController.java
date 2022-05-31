package cn.flik1337.lotteryHelper.controller;

import cn.flik1337.lotteryHelper.common.api.CommonResult;
import cn.flik1337.lotteryHelper.common.tool.QiniuUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotteryHelper/qiniu")
public class QiniuController {

    @GetMapping("auth")
    public CommonResult getToken(){
        return CommonResult.success(QiniuUtil.getUpToken());
    }

}
