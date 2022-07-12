package com.dushop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/*
 * @BelongsProject: DuShopProject
 * @BelongsPackage: com.dushop.common.entity
 * @Author: Jiang Chufeng
 * @CreateTime: 2022-07-8  10:16
 * @Description: TODO
 * @Version: 1.0
 */


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })//加上它就解决了
@EntityScan({"com.dushop.common.entity"})
public class DuShopBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuShopBackEndApplication.class, args);
    }

}
