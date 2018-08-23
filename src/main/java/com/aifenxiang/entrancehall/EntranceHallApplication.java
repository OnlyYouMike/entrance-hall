package com.aifenxiang.entrancehall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: zj
 * @create: 2018-08-21 21:45
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.aifenxiang.*"})
public class EntranceHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntranceHallApplication.class,args);
    }

}
