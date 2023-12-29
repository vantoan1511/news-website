package com.vtoan1517.service;

import com.vtoan1517.dto.MediaDTO;
import com.vtoan1517.exception.ResourceNotFoundException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMediaService {

    List<MediaDTO> findAll();

    MediaDTO handleUpload(CommonsMultipartFile file, String title, String location) throws IOException;

    MediaDTO save(MediaDTO mediaDTO);

    List<Long> delete(List<Long> ids) throws ResourceNotFoundException;

}
