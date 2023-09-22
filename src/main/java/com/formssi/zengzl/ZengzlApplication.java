package com.formssi.zengzl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.formssi.zengzl.mapper")
@EnableScheduling
public class ZengzlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZengzlApplication.class, args);
    }

}
