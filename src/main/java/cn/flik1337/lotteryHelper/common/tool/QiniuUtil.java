package cn.flik1337.lotteryHelper.common.tool;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiniuUtil {

    private static String accessKey;

    @Value("${qiniu.AccessKey}")
    public  void setAccessKey(String accessKeyId) {
        QiniuUtil.accessKey = accessKeyId;
    }

    private static String secretKey;

    @Value("${qiniu.SecretKey}")
    public  void setSecretKey(String secretKey) {
        QiniuUtil.secretKey = secretKey;
    }

    private static String bucketName;

    @Value("${qiniu.bucketName}")
    public  void setBucketName(String bucketName) {
        QiniuUtil.bucketName = bucketName;
    }


    public static String getUpToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);
        return upToken;
    }

}
