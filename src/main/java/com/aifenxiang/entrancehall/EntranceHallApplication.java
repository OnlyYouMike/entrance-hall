package com.aifenxiang.entrancehall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: zj
 * @create: 2018-08-21 21:45
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.aifenxiang.entrancehall","com.aifenxiang.pigeon"})
public class EntranceHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntranceHallApplication.class,args);
    }

}
