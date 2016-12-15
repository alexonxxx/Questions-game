package com.ballogomezharo;

import com.ballogomezharo.databaseRepositories.*;
import com.ballogomezharo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class TrivialBalloGomezHaroApplication {
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    public static void main(String[] args) {
        SpringApplication.run(TrivialBalloGomezHaroApplication.class, args);
    }



    @Bean
    CommandLineRunner runner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

            }
        };
    }
}
