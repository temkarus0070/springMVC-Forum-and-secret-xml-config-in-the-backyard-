package org.temkarus0070.MvcApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.RegisterDAO;
import org.temkarus0070.MvcApp.models.MyUserDetails;

@RestController
public class RegistrationController {
    private RegisterDAO registerDAO;


    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
    }



    @Autowired
    public void setRegisterDAO(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }



    @PostMapping(path = "/register")
    public void register(@RequestBody MyUserDetails myUserDetails){
        registerDAO.addNew(myUserDetails);
    }



}
