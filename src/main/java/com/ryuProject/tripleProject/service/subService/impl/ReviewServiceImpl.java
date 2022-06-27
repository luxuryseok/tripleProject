package com.ryuProject.tripleProject.service.subService.impl;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.dto.ReviewDTO;
import com.ryuProject.tripleProject.enums.ErrorMessage;
import com.ryuProject.tripleProject.exception.CustomException;
import com.ryuProject.tripleProject.mapper.ReviewMapper;
import com.ryuProject.tripleProject.service.subService.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    private Optional<ReviewDTO> getReviewByPlaceAndUser(RequestDTO requestDTO){
        return reviewMapper.getReviewByPlaceAndUser(requestDTO);
    }

    @Override
    public void isExistReview(RequestDTO requestDTO) {
        Optional<ReviewDTO> reviewDTO = getReviewByPlaceAndUser(requestDTO);
        if(reviewDTO.isPresent()){
            throw new CustomException(ErrorMessage.DUPLICATE_REVIEW);
        }
    }

    @Override
    public void insertReview(RequestDTO requestDTO) {
        reviewMapper.insertReview(requestDTO);
    }

    @Override
    public void updateReview(RequestDTO requestDTO) {
        reviewMapper.updateReview(requestDTO);
    }

    @Override
    public void isCompareModifyUser(RequestDTO requestDTO){
        Optional<ReviewDTO> reviewDTO = getReviewByPlaceAndUser(requestDTO);

        if(ObjectUtils.isEmpty(reviewDTO.orElse(null))){
            throw new CustomException(ErrorMessage.IS_WRITTEN_USER_MODIFY);
        }
    }

    @Override
    public void isCompareDeleteUser(RequestDTO requestDTO) {
        Optional<ReviewDTO> reviewDTO = getReviewByPlaceAndUser(requestDTO);

        if(ObjectUtils.isEmpty(reviewDTO.orElse(null))){
            throw new CustomException(ErrorMessage.IS_WRITTEN_USER_DELETE);
        }
    }

    @Override
    public void deleteReview(RequestDTO requestDTO) {
        reviewMapper.deleteReview(requestDTO);
    }

    @Override
    public Integer getPlaceByReviewCount(RequestDTO requestDTO) {
        return reviewMapper.getPlaceByReviewCount(requestDTO);
    }
}
