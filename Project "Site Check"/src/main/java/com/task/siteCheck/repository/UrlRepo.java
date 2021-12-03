package com.task.siteCheck.repository;

import com.task.siteCheck.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UrlRepo extends JpaRepository<Url, Integer> {

    boolean existsUrlByUrlAndEmail(String url, String email);
}
