package org.temkarus0070.MvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.models.MyUserDetails;
import org.temkarus0070.MvcApp.models.User;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

@Component
public class RegisterDAO implements UserDetailsService {

    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
    EntityManager  entityManager;

    @Autowired
    public void setLocalContainerEntityManagerFactoryBean(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        this.localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBean;
        this.entityManager=localContainerEntityManagerFactoryBean.createNativeEntityManager(null);
    }

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
                MyUserDetails myUserDetailsDetails = new MyUserDetails();
                myUserDetailsDetails.setUsername(s);
                myUserDetailsDetails.setPassword(row.getString("password"));
                return myUserDetailsDetails;
            }).stream().findAny().orElse(null);
        }
        catch (Exception ex){
         ex.printStackTrace();
        }
        return null;
    }


    public void addNew(MyUserDetails entity) {
        List<User> myUserDetails =entityManager.createQuery("select u from User", User.class).getResultList();
        String encodedPass = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPass);
        User user=new User(entity);
        entityManager.persist(user);
    }
}
