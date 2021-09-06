package org.temkarus0070.MvcApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.temkarus0070.MvcApp.models.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.RegisterDAO;
import org.temkarus0070.MvcApp.dao.UserRepository;
import org.temkarus0070.MvcApp.models.MyUserDetails;
import org.temkarus0070.MvcApp.models.User;

import java.util.LinkedList;
import java.util.List;

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
        List<GrantedAuthority> grantedAuthorityCollection=new LinkedList<>();
        grantedAuthorityCollection.add( new GrantedAuthority("user"));
        String encodedPassword =passwordEncoder.encode(myUserDetails.getPassword());
        myUserDetails.setPassword(encodedPassword);
        User user=new User(myUserDetails);
        user.setAuthorities(grantedAuthorityCollection);
        userRepository.save(user);

    }



}
