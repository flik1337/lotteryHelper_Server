package cn.flik1337.lotteryHelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.flik1337.lotteryHelper.mapper")
public class LotteryHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryHelperApplication.class, args);
    }

}
