package com.ryuProject.tripleProject.service.subService.impl;

import com.ryuProject.tripleProject.dto.ImageDTO;
import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.mapper.ImageMapper;
import com.ryuProject.tripleProject.service.subService.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageMapper imageMapper;

    public ImageServiceImpl(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public void inputImage(RequestDTO requestDTO) {
        imageMapper.inputImage(requestDTO);
    }

    @Override
    public boolean isImageByReview(RequestDTO requestDTO) {

        ImageDTO imageDTO = getImageByReviewCount(requestDTO);

        return imageDTO.isImageByReview();
    }

    @Override
    public void deleteImage(RequestDTO requestDTO) {
        imageMapper.deleteImage(requestDTO);
    }

    private ImageDTO getImageByReviewCount(RequestDTO requestDTO) {
        return imageMapper.getImageByReviewCount(requestDTO);
    }
}
