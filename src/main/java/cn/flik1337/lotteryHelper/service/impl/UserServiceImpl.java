package cn.flik1337.lotteryHelper.service.impl;

import cn.flik1337.lotteryHelper.common.api.WechatApi;
import cn.flik1337.lotteryHelper.common.exception.Asserts;

import cn.flik1337.lotteryHelper.entity.Lottery;
import cn.flik1337.lotteryHelper.entity.User;
import cn.flik1337.lotteryHelper.mapper.UserMapper;
import cn.flik1337.lotteryHelper.service.IUserService;
import cn.flik1337.lotteryHelper.vo.UserSession;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.flik1337.lotteryHelper.common.api.ResultCode.FAIL_UPDATE_USER;
import static cn.flik1337.lotteryHelper.common.api.ResultCode.SESSION_WRONG;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author flik1337
 * @since 2022-04-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Value("${wechat.appid}")
    private  String APPID;
    @Value("${wechat.secret}")
    private  String SECRET;
    private static String grantType = "authorization_code";

    @Autowired
    private WechatApi wechatApi;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserSession authCode2Session(String code) {
        JSONObject userSessionObject = wechatApi.authCode2Session(APPID,SECRET,code,grantType);
        System.out.println(userSessionObject.toStringPretty());
        // json转换为bean
        UserSession userSession = JSONUtil.toBean(userSessionObject,UserSession.class);
        return userSession;

    }

    @Override
    public User authCode2UserInfo(String code){
        UserSession userSession = this.authCode2Session(code);
        // 若获取失败
        if (ObjectUtil.isNotNull(userSession.getErrCode())){
            Asserts.fail(SESSION_WRONG);
        }
        User user = new User();
        user.setOpenId(userSession.getOpenId());
        user.setSessionKey(userSession.getSessionKey());
        user.setuType(0);
        System.out.println(user.toString());
        User userQuery = getUserByOpenId(userSession.getOpenId());
        if (ObjectUtil.isNull(userQuery)){
            // 该用户第一次登录，添加信息
            userMapper.insert(user);
        }else{
            user = userQuery;
        }
        return user;
    }

    @Override
    public User getUserByOpenId(String openId){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getOpenId,openId);
        User userQuery = userMapper.selectOne(queryWrapper);
        return userQuery;
    }
    @Override
    public void updateUserInfo(User user){
        int updateCount = userMapper.updateById(user);
        if (updateCount == 0){
            Asserts.fail(FAIL_UPDATE_USER);
        }
    }

    @Override
    public List<User> getUserListByIds(List<Long> idList) {
        if (idList.size() == 0){
            return new ArrayList<>();
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(User::getUid,idList)
                    .select(User::getUid,User::getNickName,User::getAvatarUrl);
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList;
    }
}
