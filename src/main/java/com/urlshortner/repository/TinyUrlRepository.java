package com.urlshortner.repository;

import com.urlshortner.model.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TinyUrlRepository extends JpaRepository<String , TinyUrl> {
}
