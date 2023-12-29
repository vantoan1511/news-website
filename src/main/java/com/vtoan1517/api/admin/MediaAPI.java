package com.vtoan1517.api.admin;

import com.vtoan1517.dto.MediaDTO;
import com.vtoan1517.dto.SuccessResponse;
import com.vtoan1517.exception.ResourceNotFoundException;
import com.vtoan1517.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
public class MediaAPI {

    private final IMediaService mediaService;

    private final ServletContext servletContext;

    @Autowired
    public MediaAPI(IMediaService mediaService, ServletContext servletContext) {
        this.mediaService = mediaService;
        this.servletContext = servletContext;
    }

    @PostMapping
    public ResponseEntity<MediaDTO> upload(@RequestParam("title") String title,
                                           @RequestParam("file") CommonsMultipartFile file) throws IOException {
        System.out.println(title);
        return new ResponseEntity<>(mediaService.save(mediaService.handleUpload(file, title, servletContext.getRealPath("/"))), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) throws ResourceNotFoundException {
        SuccessResponse response = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message("Deleted " + mediaService.delete(ids).toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
