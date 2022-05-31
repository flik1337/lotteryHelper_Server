package cn.flik1337.lotteryHelper.service;

import cn.flik1337.lotteryHelper.entity.User;
import cn.flik1337.lotteryHelper.vo.UserSession;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-09
 */
public interface IUserService extends IService<User> {
    UserSession authCode2Session(String code);
    User authCode2UserInfo(String code);
    User getUserByOpenId(String openId);
    void updateUserInfo(User user);
    List<User> getUserListByIds(List<Long> idList);

}
