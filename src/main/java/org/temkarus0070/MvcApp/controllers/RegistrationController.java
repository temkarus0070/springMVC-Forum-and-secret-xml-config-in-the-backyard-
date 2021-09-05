package org.temkarus0070.MvcApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.RegisterDAO;
import org.temkarus0070.MvcApp.dao.UserRepository;
import org.temkarus0070.MvcApp.models.MyUserDetails;
import org.temkarus0070.MvcApp.models.User;

import java.util.Collection;
import java.util.LinkedList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
    private RegisterDAO registerDAO;
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRegisterDAO(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }



    @PostMapping(path = "/register")
    public void register(@RequestBody MyUserDetails myUserDetails){
        Collection<GrantedAuthority> grantedAuthorityCollection=new LinkedList<>();
        grantedAuthorityCollection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });
        String encodedPassword =passwordEncoder.encode(myUserDetails.getPassword());
        myUserDetails.setPassword(encodedPassword);
        myUserDetails.getAuthorities().addAll(grantedAuthorityCollection);
        userRepository.save(new User(myUserDetails));
    }



}
