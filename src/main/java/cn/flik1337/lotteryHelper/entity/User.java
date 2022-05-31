package cn.flik1337.lotteryHelper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author flik1337
 * @since 2022-04-09
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 用户类型
     */
    private Integer uType;

    private String openId;

    private String unionId;

    private String sessionKey;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public Integer getuType() {
        return uType;
    }

    public void setuType(Integer uType) {
        this.uType = uType;
    }
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "User{" +
            "uid=" + uid +
            ", nickName=" + nickName +
            ", avatarUrl=" + avatarUrl +
            ", uType=" + uType +
            ", openId=" + openId +
            ", unionId=" + unionId +
            ", sessionKey=" + sessionKey +
        "}";
    }
}
