package com.ryuProject.tripleProject.mapper;

import com.ryuProject.tripleProject.dto.ImageDTO;
import com.ryuProject.tripleProject.dto.RequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    void inputImage(RequestDTO requestDTO);

    ImageDTO getImageByReviewCount(RequestDTO requestDTO);

    void deleteImage(RequestDTO requestDTO);
}
