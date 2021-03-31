package com.springboot.lombok.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class BeanConfiguration {

    @Bean
    public Faker faker() {
        return new Faker(new Locale("en-US"));
    }
}
