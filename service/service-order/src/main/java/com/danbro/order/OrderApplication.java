package com.danbro.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname OrderApplication
 * @Description TODO
 * @Date 2021/1/8 13:56
 * @Author Danrbo
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "com.danbro")
@MapperScan(basePackages = "com.danbro.order.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
