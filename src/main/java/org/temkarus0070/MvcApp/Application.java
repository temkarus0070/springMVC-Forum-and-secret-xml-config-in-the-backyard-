package org.temkarus0070.MvcApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.temkarus0070.MvcApp.controllers.PeopleController;

import java.util.Collections;

@SpringBootApplication(scanBasePackageClasses = {PeopleController.class})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }






}