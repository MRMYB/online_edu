package com.online.oss;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //不启用数据库自动配置
@ComponentScan(basePackages = "com.online")
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
        System.out.println("------------------------------------------------------程序正常启动-----------------------------------------------------------");
    }

}
