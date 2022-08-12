package com.link;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ManagerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerSystemApplication.class, args);
    }


}
