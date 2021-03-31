package com.springboot.lombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springboot.lombok.property.FileUploadProperties;


import lombok.extern.slf4j.Slf4j;

@SpringBootApplication

// lombok이 로거 필드 생성.
@Slf4j

@EnableConfigurationProperties({
    FileUploadProperties.class
})


public class SpringbootAndLombok {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAndLombok.class, args);
        log.info("Springboot and lombok application started successfully.");
    }
}
