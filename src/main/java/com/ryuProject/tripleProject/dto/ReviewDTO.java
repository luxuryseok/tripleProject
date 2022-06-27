package com.ryuProject.tripleProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewDTO {
    //저장관련
    private String reviewId;
    private String userId;
    private String placeId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime datetime;

    //호출관련
    private List<String> attachedPhotoIds;
    private String userPoints;

}
