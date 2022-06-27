package com.ryuProject.tripleProject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {
    private String reviewId;
    private String attachedPhotoId;

    private int imageByReviewCount;

    public boolean isImageByReview() {
        return imageByReviewCount > 0;
    }
}
