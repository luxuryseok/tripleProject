package com.ryuProject.tripleProject.service;

import com.ryuProject.tripleProject.dto.RequestDTO;

public interface MainService {
    void create(RequestDTO requestDTO);

    void modify(RequestDTO requestDTO);

    void delete(RequestDTO requestDTO);

    Integer getUserPoint(String userId);
}
