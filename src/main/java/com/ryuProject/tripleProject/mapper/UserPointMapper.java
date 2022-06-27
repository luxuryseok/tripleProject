package com.ryuProject.tripleProject.mapper;

import com.ryuProject.tripleProject.dto.UserPointDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPointMapper {

    void inputUserPoint(UserPointDTO userPointDTO);

    Integer getUserReviewSumPoint(UserPointDTO userPointDTO);

    Integer getUserSumPoint(String userId);
}
