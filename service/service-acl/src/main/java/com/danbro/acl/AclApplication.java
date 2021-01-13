package com.danbro.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname AclApplication
 * @Description TODO
 * @Date 2021/1/13 15:12
 * @Author Danrbo
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.danbro")
@MapperScan(basePackages = "com.danbro.acl.mapper")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }
}
