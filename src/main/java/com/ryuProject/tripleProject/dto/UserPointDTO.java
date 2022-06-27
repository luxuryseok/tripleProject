package com.ryuProject.tripleProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ryuProject.tripleProject.enums.BusinessMessage;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserPointDTO {
    private String userId;
    private String reviewId;
    private String pointType;
    private Integer point;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;

    public void retrieveAllPoint(BusinessMessage businessMessage, int userPoint) {
        this.point = -userPoint;
        this.pointType = businessMessage.getPointType();
    }

    public void inputPoint(BusinessMessage businessMessage) {
        this.point = businessMessage.getPoint();
        this.pointType = businessMessage.getPointType();
    }
}
