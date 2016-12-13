package com.ballogomezharo.configuration.security;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class BaseSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/**");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
             .formLogin() //a login form is showed when no authenticated request
                .loginPage("/login")
                .and()
            .authorizeRequests()

                .antMatchers("/static/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/createuser").permitAll()
                .mvcMatchers("/usuaris").permitAll()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/partidaNova").authenticated()
                .mvcMatchers("/menuInicial").authenticated()
                .mvcMatchers("/puntuacio").authenticated()
                .antMatchers("/entrarPreguntesFlow").authenticated()
                .anyRequest().permitAll()
                .and()

            .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("preguntes")

                .and()
            .logout()
                    .logoutSuccessUrl("/byebye"); //where to go when logout is successful


        http
            .csrf().disable()
            .headers()
            .frameOptions().disable();
    }
}