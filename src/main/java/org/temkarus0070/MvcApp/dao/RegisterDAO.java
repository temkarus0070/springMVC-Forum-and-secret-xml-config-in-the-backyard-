package org.temkarus0070.MvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.models.MyUserDetails;
import org.temkarus0070.MvcApp.models.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisterDAO implements UserDetailsService {


    @Resource
    private  UserRepository userRepository;
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
    EntityManager  entityManager;

    public RegisterDAO(){

    }

    @Autowired
    public void setLocalContainerEntityManagerFactoryBean(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        this.localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBean;
        this.entityManager=localContainerEntityManagerFactoryBean.createNativeEntityManager(null);
    }



    static PasswordEncoder passwordEncoder;


    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            User user= userRepository.findById(s).get();
            if(user!=null) {
                Collection<GrantedAuthority> authorities = user.getAuthorities().stream().map(authority -> authority).collect(Collectors.toList());
                return new MyUserDetails(user.getUsername(), user.getPassword(), authorities, user.isEnabled(), user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialNonExpired());
            }
            else throw new UsernameNotFoundException("user not found");
    }



}
