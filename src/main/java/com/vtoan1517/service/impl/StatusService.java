package com.vtoan1517.service.impl;

import com.vtoan1517.dto.StatusDTO;
import com.vtoan1517.entity.Status;
import com.vtoan1517.repository.StatusRepository;
import com.vtoan1517.service.IStatusService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatusService implements IStatusService {

    private final StatusRepository statusRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Map<String, String> findAll() {
        return mapper.map(statusRepository.findAll(), Status::getCode, Status::getName);
    }

    @Override
    public StatusDTO save(StatusDTO statusDTO) {
        Status statusEntity = mapper.map(statusDTO, Status.class);
        return mapper.map(statusRepository.save(statusEntity), StatusDTO.class);
    }
}
