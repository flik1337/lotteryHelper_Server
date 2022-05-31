package cn.flik1337.lotteryHelper.common.api;

import cn.hutool.json.JSONObject;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import io.lettuce.core.dynamic.annotation.Param;
import retrofit2.http.*;


@RetrofitClient(baseUrl = "${remote.baseUrl.wechat}")
public interface WechatApi {
    @GET("/sns/jscode2session")
    JSONObject authCode2Session(@Query("appid") String appId, @Query("secret")String secret, @Query("js_code")String jsCode, @Query("grant_type")String grantType);
    @GET("/cgi-bin/token")
    JSONObject getAccessToken(@Query("appid") String appId, @Query("secret")String secret,@Query("grant_type")String grantType);

    @FormUrlEncoded
    @POST("/wxa/getwxacodeunlimit")
    String getUnlimitedCode(@Param("access_token")String accessToken, @Param("scene")String scene , @Param("page")String page, @Param("check_path")Boolean checkPath, @Param("env_version")String envVersion, @Param("width")Integer width);
}
