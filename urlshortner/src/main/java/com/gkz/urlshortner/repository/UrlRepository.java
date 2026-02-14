package com.gkz.urlshortner.repository;

import com.gkz.urlshortner.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{

    Optional<Url> findByShortCode(String shortCode);
    Url findByExpiryAt(String shortCode);
    boolean existsByShortCode(String shortCode);
}
