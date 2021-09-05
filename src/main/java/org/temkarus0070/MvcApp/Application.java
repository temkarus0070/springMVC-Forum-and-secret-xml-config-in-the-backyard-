package org.temkarus0070.MvcApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.temkarus0070.MvcApp.controllers.RegistrationController;

@SpringBootApplication(scanBasePackageClasses = {RegistrationController.class})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }







}