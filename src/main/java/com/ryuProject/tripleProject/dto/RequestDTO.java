package com.ryuProject.tripleProject.dto;

import com.ryuProject.tripleProject.enums.ActionType;
import com.ryuProject.tripleProject.enums.ErrorMessage;
import com.ryuProject.tripleProject.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private String type;
    private ActionType action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

    public boolean isExistAttachedPhotoIds(){
        return !ObjectUtils.isEmpty(attachedPhotoIds);
    }

    public boolean isContentsByOneMore(){
        return content.length() > 1;
    }

    public UserPointDTO getConvertUserPointDTO() {
        return UserPointDTO.builder()
                .userId(userId)
                .reviewId(reviewId)
                .createDate(LocalDateTime.now())
                .build();
    }

    public void updateContent() {
        this.content = content;
    }

    public void isExistContents() {
        if(ObjectUtils.isEmpty(content)){
            throw new CustomException(ErrorMessage.IS_EMPTY_CONTENT);
        }
    }
}
