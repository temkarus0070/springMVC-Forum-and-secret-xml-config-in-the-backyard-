package org.temkarus0070.MvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.temkarus0070.MvcApp.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

@Component
public class RegisterDAO implements UserDetailsService {


    static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static PasswordEncoder passwordEncoder;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("username", s);
            return namedParameterJdbcTemplate.query("SELECT * FROM USERS WHERE username=:username", map, (row, index) -> {
                User userDetails = new User();
                userDetails.setUsername(s);
                userDetails.setPassword(row.getString("password"));
                return userDetails;
            }).stream().findAny().orElse(null);
        }
        catch (Exception ex){
         ex.printStackTrace();
        }
        return null;
    }


    public void addNew(User entity) {
        String encodedPass = passwordEncoder.encode(entity.getPassword());
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("username", entity.getUsername());
        map.put("password", encodedPass);

        namedParameterJdbcTemplate.update("INSERT INTO Users(username,password,enabled) VALUES(:username,:password,true) ", map);
    }
}
