package com.cxhello.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cxhello.admin.dao")
public class SpringbootAdminApplication {

    private static final Logger logger = LogManager.getLogger(SpringbootAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminApplication.class, args);
        logger.debug("启动成功");
    }

}
