package cn.flik1337.lotteryHelper.controller;


import cn.flik1337.lotteryHelper.common.api.CommonResult;
import cn.flik1337.lotteryHelper.entity.User;
import cn.flik1337.lotteryHelper.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author flik1337
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/lotteryHelper/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("login")
    public CommonResult login(@Param("code") String code){
        User user = userService.authCode2UserInfo(code);
        return CommonResult.success(user);
    }
    @PostMapping("update")
    public CommonResult login(@RequestBody User user){
        userService.updateUserInfo(user);
        return CommonResult.success();
    }
}
