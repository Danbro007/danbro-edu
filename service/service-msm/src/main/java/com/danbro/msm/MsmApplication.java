package com.danbro.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname MsmApplication
 * @Description TODO
 * @Date 2021/1/5 14:55
 * @Author Danrbo
 */
@ComponentScan(basePackages = "com.danbro")
@SpringBootApplication
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
