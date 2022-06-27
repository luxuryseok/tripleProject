package com.ryuProject.tripleProject.service.subService;

import com.ryuProject.tripleProject.dto.RequestDTO;

public interface ImageService {
    void inputImage(RequestDTO requestDTO);

    boolean isImageByReview(RequestDTO requestDTO);

    void deleteImage(RequestDTO requestDTO);
}
