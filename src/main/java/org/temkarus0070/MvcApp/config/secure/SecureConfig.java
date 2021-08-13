package org.temkarus0070.MvcApp.config.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.dao.RegisterDAO;

import javax.sql.DataSource;
import javax.xml.crypto.Data;



@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {


    private PasswordEncoder passwordEncoder;

    @Deprecated
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    private DataSource dataSource;
    {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        this.dataSource=dataSource;
    }


    SecureConfig(){

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/Posts")
                        .authenticated()
                .antMatchers(HttpMethod.POST,"/Sections").authenticated()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().logout().and()
                .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new RegisterDAO()).and().jdbcAuthentication()
                .dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder(9));
    }


}
