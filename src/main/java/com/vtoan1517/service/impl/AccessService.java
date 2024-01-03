package com.vtoan1517.service.impl;

import com.vtoan1517.dto.AccessDTO;
import com.vtoan1517.entity.Access;
import com.vtoan1517.repository.AccessRepository;
import com.vtoan1517.service.IAccessService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AccessService implements IAccessService {

    private final AccessRepository accessRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public AccessService(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    @Transactional
    public AccessDTO save(AccessDTO accessDTO) {
        Access accessEntity = mapper.map(accessDTO, Access.class);
        return mapper.map(accessRepository.save(accessEntity), AccessDTO.class);
    }

    @Override
    public Map<String, String> findAll() {
        return mapper.map(accessRepository.findAll(), Access::getCode, Access::getName);
    }
}
