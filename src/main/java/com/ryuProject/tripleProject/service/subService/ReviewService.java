package com.ryuProject.tripleProject.service.subService;

import com.ryuProject.tripleProject.dto.RequestDTO;

public interface ReviewService {
    void isCompareModifyUser(RequestDTO requestDTO);

    Integer getPlaceByReviewCount(RequestDTO requestDTO);

    void isExistReview(RequestDTO requestDTO);

    void insertReview(RequestDTO requestDTO);

    void updateReview(RequestDTO requestDTO);

    void isCompareDeleteUser(RequestDTO requestDTO);

    void deleteReview(RequestDTO requestDTO);
}
