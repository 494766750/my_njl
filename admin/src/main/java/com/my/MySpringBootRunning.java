package com.my;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.my.mapper")
public class MySpringBootRunning {
    public static void main(String[] args)
    {
        //修改Banner的模式为OFF
        //SpringApplicationBuilder builder = new SpringApplicationBuilder(MySpringBootRunning.class);
        //builder.bannerMode(Banner.Mode.OFF).run(args);
        SpringApplication.run(MySpringBootRunning.class, args);
    }
}
