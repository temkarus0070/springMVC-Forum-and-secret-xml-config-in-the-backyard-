package org.temkarus0070.MvcApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.temkarus0070.MvcApp.config.SpringConfig;
import org.temkarus0070.MvcApp.controllers.AuthorizationController;



@SpringBootApplication(scanBasePackageClasses = {Application.class})
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }







}