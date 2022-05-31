package cn.flik1337.lotteryHelper.vo;

public class UserSession {
    public UserSession(String openid, String sessionKey, String unionId, Integer errCode, String errMsg) {
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.unionId = unionId;
        this.errcode = errCode;
        this.errmsg = errMsg;
    }

    String openid;
    String sessionKey;
    String unionId;
    Integer errcode;
    String errmsg;

    public String getOpenId() {
        return openid;
    }

    public void setOpenId(String openId) {
        this.openid = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getErrCode() {
        return errcode;
    }

    public void setErrCode(Integer errCode) {
        this.errcode = errCode;
    }

    public String getErrMsg() {
        return errmsg;
    }

    public void setErrMsg(String errMsg) {
        this.errmsg = errMsg;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "openId='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", unionId='" + unionId + '\'' +
                ", errCode=" + errcode +
                ", errMsg='" + errmsg + '\'' +
                '}';
    }
}
