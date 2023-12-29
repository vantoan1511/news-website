package com.vtoan1517.api.admin;

import com.vtoan1517.dto.AccessDTO;
import com.vtoan1517.service.IAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accesses")
public class AccessAPI {

    private final IAccessService accessService;

    @Autowired
    public AccessAPI(IAccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping
    public ResponseEntity<AccessDTO> createAccess(@RequestBody AccessDTO accessDTO) {
        return new ResponseEntity<>(accessService.save(accessDTO), HttpStatus.CREATED);
    }
}
