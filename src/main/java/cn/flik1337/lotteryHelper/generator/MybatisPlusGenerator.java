package cn.flik1337.lotteryHelper.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerator {

    private static String URL = "jdbc:mysql://localhost:3306/lottery?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2b8&useSSL=false";
    private static String USERNAME = "root";
    private static String PASSWORD = "lovegfm99";
    private static String AUTHOR = "flik1337";
    private static String PARENT = "cn.flik1337";
    private static String MODULE = "lotteryHelper";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String filePath = "/src/main/java/";
        String resourcePath = "/src/main/resources/mapper";
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                        .globalConfig(builder -> {
                            builder.author(AUTHOR) // 设置作者
                                    //.enableSwagger() // 开启    swagger 模式
                                    //.fileOverride() // 覆盖已生成文件
                                    .outputDir(projectPath.concat(filePath)); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent(PARENT) // 设置父包名
                                    .moduleName(MODULE) // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath.concat(resourcePath)));// 设置mapperXml生成路径
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("lottery_send") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_")// 设置过滤表前缀
                                    .controllerBuilder()
                                    .enableRestStyle() // 开启restController
                                    .mapperBuilder()
                                    .enableMapperAnnotation();// 开启 @Mapper 注解

                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .execute();
    }
}
