package com.ryuProject.tripleProject.mapper;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ReviewMapper {

    int insertReview(RequestDTO requestDTO);

    int getPlaceByReviewCount(RequestDTO requestDTO);

    void updateReview(RequestDTO requestDTO);

    void deleteReview(RequestDTO requestDTO);

    Optional<ReviewDTO> getReviewByPlaceAndUser(RequestDTO requestDTO);
}
