package com.task.siteCheck.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${spring.mail.username}")
    private String sender;

    @Bean
    @Scope("prototype")
    public SimpleMailMessage messageTemplate(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setSubject("Site become not available");
        message.setText(" '%s' : this site is not available");
        return message;
    }
}
