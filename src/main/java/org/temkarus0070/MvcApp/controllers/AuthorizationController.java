package org.temkarus0070.MvcApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.RegisterDAO;
import org.temkarus0070.MvcApp.dao.Repositories.UserRepository;
import org.temkarus0070.MvcApp.models.GrantedAuthority;
import org.temkarus0070.MvcApp.models.MyUserDetails;
import org.temkarus0070.MvcApp.models.User;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationController {
    private RegisterDAO registerDAO;
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;




    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
    public void register(@RequestBody MyUserDetails myUserDetails) {
        List<GrantedAuthority> grantedAuthorityCollection = new LinkedList<>();
        grantedAuthorityCollection.add(new GrantedAuthority("admin"));
        String encodedPassword = passwordEncoder.encode(myUserDetails.getPassword());
        myUserDetails.setPassword(encodedPassword);
        User user = new User(myUserDetails);
        user.setAuthorities(grantedAuthorityCollection);
        userRepository.save(user);

    }

    @GetMapping(path = "/role")
    public String getRole(Principal principal) {
        User u = userRepository.getById(principal.getName());
        if (u != null) {
            return u.getAuthorities().stream().findFirst().get().getAuthority();
        }
        return "";
    }


}
