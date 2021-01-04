package com.danbro.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname CmsApplication
 * @Description TODO
 * @Date 2021/1/4 15:18
 * @Author Danrbo
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan(basePackages = "com.danbro")
@SpringBootApplication
@MapperScan(basePackages = "com.danbro.cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
