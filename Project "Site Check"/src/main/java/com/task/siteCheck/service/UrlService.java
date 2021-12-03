package com.task.siteCheck.service;

import com.task.siteCheck.dto.UrlDTO;
import com.task.siteCheck.entity.Url;
import com.task.siteCheck.repository.UrlRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {

    private final UrlRepo urlRepo;

    public UrlService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    @Transactional
    public void addUrl(UrlDTO urlDto){
        if(!urlRepo.existsUrlByUrlAndEmail(urlDto.getUrl(), urlDto.getEmail())){
            Url url = Url.fromDTO(urlDto);
            urlRepo.save(url);
        }
    }

    @Transactional
    public List<UrlDTO> getUrlThatNotAvailable(){
        List<Url> urls = urlRepo.findAll();
        List<UrlDTO> urlDTOList = new ArrayList<>();
        for (Url url : urls){
            if(UrlDTO.check(url.getUrl()) != 200){
                urlDTOList.add(UrlDTO.of(url));
            }
        }
        return urlDTOList;
    }


}
