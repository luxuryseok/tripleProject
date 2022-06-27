package com.ryuProject.tripleProject.enums;

import lombok.Getter;

@Getter
public enum BusinessMessage {
    //USER POINT TYPE
    ADD_CONTENT_POINT("ADD_CONTENT", 1),
    ADD_IMAGE_POINT("ADD_IMAGE", 1),
    ADD_FIRST_PLACE_POINT("ADD_FIRST_PLACE", 1),
    RETRIEVE_IMAGE_POINT("RETRIEVE_IMAGE", -1),
    RETRIEVE_ALL_POINT("RETRIEVE_ALL")

    ;

    private String pointType;
    private int point;

    BusinessMessage(String pointType, int point) {
        this.pointType = pointType;
        this.point = point;
    }
    BusinessMessage(String pointType) {
        this.pointType = pointType;
    }

}
