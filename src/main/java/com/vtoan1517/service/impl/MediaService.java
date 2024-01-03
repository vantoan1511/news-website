package com.vtoan1517.service.impl;

import com.vtoan1517.dto.MediaDTO;
import com.vtoan1517.entity.Media;
import com.vtoan1517.exception.ResourceNotFoundException;
import com.vtoan1517.repository.MediaRepository;
import com.vtoan1517.service.IMediaService;
import com.vtoan1517.utils.CollectionMapper;
import com.vtoan1517.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MediaService implements IMediaService {

    private final MediaRepository mediaRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<MediaDTO> findAll() {
        return mapper.map(mediaRepository.findAll(), MediaDTO.class);
    }

    @Override
    public MediaDTO handleUpload(CommonsMultipartFile file, String title, String location) throws IOException {
        final String prefix = "http://localhost:8080/";

        if (file != null) {

            String fileName = title.isBlank() ? file.getOriginalFilename() : title;
            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            if (!fileName.contains(fileExtension)) {
                fileName += fileExtension;
            }

            if (mediaRepository.findByTitle(fileName) != null) {
                fileName = "media-" + SlugGenerator.slugify.slugify(new Date().toString()) + fileExtension;
            }

            String url = "\\resources\\images\\" + fileName;

            String link = prefix + "resources/images/" + fileName;

            String dest = location + url;

            File desDir = new File(dest);
            if (!desDir.exists()) {
                desDir.mkdirs();
            }
            file.transferTo(desDir);

            return MediaDTO.builder()
                    .title(fileName)
                    .url(link)
                    .directory(dest)
                    .build();
        }

        return null;
    }

    @Override
    public MediaDTO save(MediaDTO mediaDTO) {
        Media mediaEntity = mapper.map(mediaDTO, Media.class);
        mediaEntity = mediaRepository.save(mediaEntity);
        return mapper.map(mediaEntity, MediaDTO.class);
    }

    @Override
    public List<Long> delete(List<Long> ids) throws ResourceNotFoundException {
        List<Long> result = new ArrayList<>();
        for (long id : ids) {
            Media mediaEntity = mediaRepository.findOne(id);

            if (mediaEntity != null) {
                File file = new File(mediaEntity.getDirectory());
                if (file.exists()) {
                    if (file.delete()) {
                        mediaRepository.delete(id);
                        result.add(id);
                    }
                }
            } else {
                throw new ResourceNotFoundException("Không tìm thấy tài nguyên với id " + id);
            }
        }
        return result;
    }
}
