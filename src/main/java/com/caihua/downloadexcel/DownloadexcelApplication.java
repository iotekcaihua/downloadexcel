package com.caihua.downloadexcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.caihua.*"})
@MapperScan("com.caihua.mapper")
public class DownloadexcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownloadexcelApplication.class, args);
    }

}
