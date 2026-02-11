package com.gkz.urlshortner.repository;

import com.gkz.urlshortner.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{

    boolean existsByshortCode(String shortCode);

}
