package com.mzy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.mzy.blog.mapper")
@EnableTransactionManagement
public class MyblogApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }

}
