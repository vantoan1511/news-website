package com.vtoan1517.repository;

import com.vtoan1517.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

    MediaEntity findByTitle(String title);

    MediaEntity findByUrl(String url);
}
