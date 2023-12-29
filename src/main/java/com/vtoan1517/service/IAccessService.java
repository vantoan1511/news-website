package com.vtoan1517.service;

import com.vtoan1517.dto.AccessDTO;

import java.util.Map;

public interface IAccessService {
    AccessDTO save(AccessDTO accessDTO);

    Map<String, String> findAll();
}
