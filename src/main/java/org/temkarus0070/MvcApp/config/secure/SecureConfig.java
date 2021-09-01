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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.temkarus0070.MvcApp.dao.RegisterDAO;

import javax.sql.DataSource;
import javax.xml.crypto.Data;



@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {






    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(auth.getDefaultUserDetailsService()).passwordEncoder(passwordEncoder());
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
     /* http.cors();
        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/posts")
                        .authenticated()
                .antMatchers(HttpMethod.POST,"/sections").authenticated()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().logout().and()
                .rememberMe();
*/
    }



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new RegisterDAO()).and().jdbcAuthentication()
                .dataSource(dataSource).passwordEncoder(passwordEncoder());
    }


}
