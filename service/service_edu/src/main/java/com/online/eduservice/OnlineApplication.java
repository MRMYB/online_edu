package com.online.eduservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.online"})
public class OnlineApplication {
    public static void main(String[] args) {

        SpringApplication.run(OnlineApplication.class, args);


        System.out.println("--------------------------------程序完全启动--------------------------------------");


    }
}
