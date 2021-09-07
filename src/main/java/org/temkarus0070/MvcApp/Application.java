package org.temkarus0070.MvcApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.temkarus0070.MvcApp.controllers.AuthorizationController;

@SpringBootApplication(scanBasePackageClasses = {AuthorizationController.class})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }







}