package org.temkarus0070.MvcApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.temkarus0070.MvcApp.dao.RegisterDAO;
import org.temkarus0070.MvcApp.models.User;

@Controller
public class RegistrationController {
    private RegisterDAO registerDAO;


    @Autowired
    public void setRegisterDAO(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }




    @GetMapping("/register")
    public String register(@ModelAttribute User user){
        return "register/register";
    }

    @PostMapping("/register")
    public String registerUser(User user){
        registerDAO.addNew(user);
        return "redirect:login";
    }

}
