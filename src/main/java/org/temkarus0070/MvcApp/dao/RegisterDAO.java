package org.temkarus0070.MvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.models.MyUserDetails;
import org.temkarus0070.MvcApp.models.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

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
                return new MyUserDetails(user.getUsername(), user.getPassword(), user.getAuthorities(), user.isEnabled(), user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialNonExpired());
            }
            else throw new UsernameNotFoundException("user not found");
    }



}
