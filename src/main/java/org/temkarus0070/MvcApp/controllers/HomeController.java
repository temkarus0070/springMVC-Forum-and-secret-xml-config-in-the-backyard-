package org.temkarus0070.MvcApp.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String index(){
        return "home/home";
    }


    @GetMapping("/about")
    public String about(){
        return "home/about";
    }
}
