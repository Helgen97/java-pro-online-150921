package com.task.siteCheck.service;

import com.task.siteCheck.dto.UrlDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailService {
    private final JavaMailSender mailSender;
    public final ApplicationContext applicationContext;

    public MailService(JavaMailSender mailSender, ApplicationContext applicationContext) {
        this.mailSender = mailSender;
        this.applicationContext = applicationContext;
    }

    public void sendMessage(UrlDTO urlDTO) {
        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);

        String text = String.format(message.getText(), urlDTO.getUrl());
        message.setText(text);
        message.setTo(urlDTO.getEmail());
        mailSender.send(message);
    }
}
