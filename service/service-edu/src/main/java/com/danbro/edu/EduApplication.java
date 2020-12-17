package com.danbro.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname EduApplication
 * @Description TODO EduTeacher 主启动类
 * @Date 2020/12/14 14:45
 * @Author Danrbo
 */
@EnableSwagger2
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
