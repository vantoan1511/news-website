package com.vtoan1517.repository;

import com.vtoan1517.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Media findByTitle(String title);

    Media findByUrl(String url);
}
