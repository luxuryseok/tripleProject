package com.ryuProject.tripleProject.service.subService;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.dto.UserPointDTO;

public interface UserPointService {
    void inputContentPoint(UserPointDTO userPointDTO);
    void inputImagePoint(UserPointDTO userPointDTO);
    void inputFirstPlacePoint(UserPointDTO userPointDTO);

    void retrieveImagePoint(UserPointDTO userPointDTO);

    void retrieveAllPoint(UserPointDTO userPointDTO);

    Integer getUserPoint(String userId);

    void createUserPoint(RequestDTO requestDTO, int getPlaceByReviewCount);

    void modifyUserPoint(RequestDTO requestDTO, boolean isImageByReview);
}
