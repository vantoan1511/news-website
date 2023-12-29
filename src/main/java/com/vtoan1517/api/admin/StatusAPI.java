package com.vtoan1517.api.admin;

import com.vtoan1517.dto.StatusDTO;
import com.vtoan1517.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusAPI {

    private final IStatusService statusService;

    @Autowired
    public StatusAPI(IStatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<StatusDTO> createStatus(@RequestBody StatusDTO statusDTO) {
        return new ResponseEntity<>(statusService.save(statusDTO), HttpStatus.CREATED);
    }
}
