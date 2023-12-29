package com.vtoan1517.service;

import com.vtoan1517.dto.StatusDTO;

import java.util.Map;

public interface IStatusService {

    Map<String, String> findAll();

    StatusDTO save(StatusDTO statusDTO);
}
