package com.task.siteCheck.mail;

import com.task.siteCheck.dto.UrlDTO;
import com.task.siteCheck.service.MailService;
import com.task.siteCheck.service.UrlService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailSender {
    private final MailService mailService;
    private final UrlService urlService;

    public EmailSender(MailService mailService, UrlService urlService) {
        this.mailService = mailService;
        this.urlService = urlService;
    }

    @Scheduled(fixedDelay = 60000)
    public void sendMail(){
        List<UrlDTO> dtoList = urlService.getUrlThatNotAvailable();
        dtoList.forEach(mailService::sendMessage);
    }
}
