package com.ncu.quiz_master_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class QuizMasterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizMasterBackendApplication.class, args);
    }

}
