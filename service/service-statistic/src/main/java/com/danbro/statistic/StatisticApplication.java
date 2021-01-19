package com.danbro.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Classname StatisticApplication
 * @Description TODO
 * @Date 2021/1/12 11:04
 * @Author Danrbo
 */
@MapperScan(basePackages = "com.danbro.statistic.mapper")
@SpringBootApplication()
@EnableFeignClients
@ComponentScan(basePackages = "com.danbro")
@EnableDiscoveryClient
@EnableScheduling
public class StatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class, args);
    }
}
