package com.danbro.edu.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @Classname Config
 * @Description TODO
 * @Date 2020/12/14 15:15
 * @Author Danrbo
 */
@ComponentScan(basePackages = "com.danbro")
@Configuration
@MapperScan(basePackages = "com.danbro.edu.mapper")
public class Config {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }
}
