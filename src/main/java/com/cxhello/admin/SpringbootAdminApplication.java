package com.cxhello.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cxhello.admin.dao")
public class SpringbootAdminApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringbootAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminApplication.class, args);
        logger.debug("启动成功");
    }

}
