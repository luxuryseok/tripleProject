package com.ryuProject.tripleProject.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    //이미 작성된 장소에 유저와 리뷰번호 중복 조회
    DUPLICATE_REVIEW("이미 작성된 리뷰가 존재합니다."),
    //유저정보 없는경우
    NOT_EXISTS_USER("존재하지 않는 사용자입니다."),
    //장소정보 없는경우
    NOT_EXISTS_PLACE("존재하지 않는 장소입니다."),
    NOT_EXISTS_REVIEW("존재하지 않는 리뷰입니다."),
    //컨텐츠내용 없는경우
    IS_EMPTY_CONTENT("리뷰 내용이 존재하지 않습니다."),
    //컨트롤러에서 액션 타입 없는 경우
    NOT_EXISTS_ACTION_TYPE("존재하지 않는 ACTION TYPE 입니다."),
    //다른 작성자가 수정/삭제 할 수 없는 경우
    IS_WRITTEN_USER_MODIFY("작성자만 수정할 수 있습니다."),
    IS_WRITTEN_USER_DELETE("작성자만 삭제할 수 있습니다.")
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
