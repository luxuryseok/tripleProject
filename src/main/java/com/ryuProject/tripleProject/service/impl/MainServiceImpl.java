package com.ryuProject.tripleProject.service.impl;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.dto.UserPointDTO;
import com.ryuProject.tripleProject.service.MainService;
import com.ryuProject.tripleProject.service.subService.ImageService;
import com.ryuProject.tripleProject.service.subService.ReviewService;
import com.ryuProject.tripleProject.service.subService.UserPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainServiceImpl implements MainService {

    private final ReviewService reviewService;
    private final ImageService imageService;
    private final UserPointService userPointService;

    public MainServiceImpl(ReviewService reviewService, ImageService imageService, UserPointService userPointService) {
        this.reviewService = reviewService;
        this.imageService = imageService;
        this.userPointService = userPointService;
    }

    /**
     * 리뷰 저장
     * @param requestDTO
     */
    @Override
    @Transactional
    public void create(RequestDTO requestDTO) {
        //리뷰 내용이 없는경우
        requestDTO.isExistContents();

        //리뷰 존재 확인
        reviewService.isExistReview(requestDTO);

        //리뷰 저장
        reviewService.insertReview(requestDTO);
        //이미지 저장
        if(requestDTO.isExistAttachedPhotoIds()){
            imageService.inputImage(requestDTO);
        }
        int getPlaceByReviewCount = reviewService.getPlaceByReviewCount(requestDTO);
        //점수 처리
        userPointService.createUserPoint(requestDTO, getPlaceByReviewCount);
    }

    /**
     * 리뷰 수정
     * @param requestDTO
     */
    @Override
    @Transactional
    public void modify(RequestDTO requestDTO) {
        //리뷰 내용이 없는경우
        requestDTO.isExistContents();
        //동일 ID 체크
        reviewService.isCompareModifyUser(requestDTO);

        //리뷰업데이트
        requestDTO.updateContent();
        reviewService.updateReview(requestDTO);

        //점수처리
        boolean isImageByReview = imageService.isImageByReview(requestDTO);
        userPointService.modifyUserPoint(requestDTO, isImageByReview);

        //이미지 처리
        imageService.deleteImage(requestDTO);

        if(requestDTO.isExistAttachedPhotoIds()){
            imageService.inputImage(requestDTO);
        }
    }

    /**
     * 리뷰 삭제
     * @param requestDTO
     */
    @Override
    @Transactional
    public void delete(RequestDTO requestDTO) {
        //동일 ID 체크
        reviewService.isCompareDeleteUser(requestDTO);

        UserPointDTO userPointDTO = requestDTO.getConvertUserPointDTO();
        userPointService.retrieveAllPoint(userPointDTO);
        //이미지삭제
        imageService.deleteImage(requestDTO);
        //리뷰삭제
        reviewService.deleteReview(requestDTO);
    }
}
