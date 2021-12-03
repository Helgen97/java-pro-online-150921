package com.task.siteCheck.controller;

import com.task.siteCheck.dto.UrlDTO;
import com.task.siteCheck.service.UrlService;
import com.task.siteCheck.urlStatus.SiteStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final UrlService urlService;

    public MainController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/post")
    public ResponseEntity<SiteStatus> addUrl(@RequestBody UrlDTO urlDTO) {
        urlService.addUrl(urlDTO);
        int code = UrlDTO.check(urlDTO.getUrl());
        return new ResponseEntity<>(new SiteStatus(code), HttpStatus.OK);
    }
}
