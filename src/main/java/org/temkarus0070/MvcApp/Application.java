package org.temkarus0070.MvcApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.temkarus0070.MvcApp.config.SpringConfig;
import org.temkarus0070.MvcApp.config.secure.SecureConfig;
import org.temkarus0070.MvcApp.controllers.PeopleController;

import java.util.Collections;

@SpringBootApplication(scanBasePackageClasses = {PeopleController.class})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }






}