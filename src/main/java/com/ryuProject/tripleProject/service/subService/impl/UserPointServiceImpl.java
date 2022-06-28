package com.ryuProject.tripleProject.service.subService.impl;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.dto.UserPointDTO;
import com.ryuProject.tripleProject.enums.BusinessMessage;
import com.ryuProject.tripleProject.enums.ErrorMessage;
import com.ryuProject.tripleProject.exception.CustomException;
import com.ryuProject.tripleProject.mapper.UserPointMapper;
import com.ryuProject.tripleProject.service.subService.UserPointService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserPointServiceImpl implements UserPointService {
    private UserPointMapper userPointMapper;

    public UserPointServiceImpl(UserPointMapper userPointMapper) {
        this.userPointMapper = userPointMapper;
    }

    @Override
    public void inputContentPoint(UserPointDTO userPointDTO) {
        userPointDTO.inputPoint(BusinessMessage.ADD_CONTENT_POINT);
        savePoint(userPointDTO);
    }

    @Override
    public void inputImagePoint(UserPointDTO userPointDTO) {
        userPointDTO.inputPoint(BusinessMessage.ADD_IMAGE_POINT);
        savePoint(userPointDTO);
    }

    @Override
    public void inputFirstPlacePoint(UserPointDTO userPointDTO) {
        userPointDTO.inputPoint(BusinessMessage.ADD_FIRST_PLACE_POINT);
        savePoint(userPointDTO);
    }

    @Override
    public void retrieveImagePoint(UserPointDTO userPointDTO) {
        userPointDTO.inputPoint(BusinessMessage.RETRIEVE_IMAGE_POINT);
        savePoint(userPointDTO);
    }

    @Override
    public void retrieveAllPoint(UserPointDTO userPointDTO) {
        Integer userPoint = userPointMapper.getUserReviewSumPoint(userPointDTO);
        userPointDTO.retrieveAllPoint(BusinessMessage.RETRIEVE_ALL_POINT, userPoint);
        savePoint(userPointDTO);
    }

    @Override
    public Integer getUserPoint(String userId) {
        Integer result = userPointMapper.getUserSumPoint(userId);
        if(ObjectUtils.isEmpty(result) ){
            throw new CustomException(ErrorMessage.IS_NOT_USER_POINT);
        }
        return result;
    }

    @Override
    public void createUserPoint(RequestDTO requestDTO, int placeByReviewCount) {
        UserPointDTO userPointDTO = requestDTO.getConvertUserPointDTO();

        if(requestDTO.isContentsByOneMore()){
            inputContentPoint(userPointDTO);
        }
        if(requestDTO.isExistAttachedPhotoIds()) {
            inputImagePoint(userPointDTO);
        }
        if( placeByReviewCount == 1) {
            inputFirstPlacePoint(userPointDTO);
        }
    }

    @Override
    public void modifyUserPoint(RequestDTO requestDTO, boolean isImageByReview) {
        UserPointDTO userPointDTO = requestDTO.getConvertUserPointDTO();

        if(isImageByReview){
            if(!requestDTO.isExistAttachedPhotoIds()){
                retrieveImagePoint(userPointDTO);
            }
        }else{
            if(requestDTO.isExistAttachedPhotoIds()){
                inputImagePoint(userPointDTO);
            }
        }
    }
    private void savePoint(UserPointDTO userPointDTO){
        userPointMapper.inputUserPoint(userPointDTO);
    }
}
