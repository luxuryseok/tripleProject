package com.ryuProject.tripleProject.service;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.enums.ActionType;
import com.ryuProject.tripleProject.exception.CustomException;
import jdk.jfr.internal.tool.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainServiceTest {
    @Autowired
    private MainService mainService;

    @Test
    @DisplayName("리뷰 신규 생성")
    void create() {
        //given
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("e47a2bd8-f4ad-4ef3-9fd1-299626862dbd")
                .action(ActionType.ADD)
                .type("REVIEW")
                .content("좋아요!")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();

        mainService.create(requestDTO);


    }

    @Test
    @DisplayName("리뷰 중복 신규 생성")
    @Transactional
    void dup_create() {
        //given
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                .action(ActionType.ADD)
                .type("REVIEW")
                .content("좋아요!")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();

        try {
            mainService.create(requestDTO);
        }catch (CustomException e){
        }

    }

    @Test
    @DisplayName("리뷰 수정 이벤트")
    void modify() {
         //given
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("e47a2bd8-f4ad-4ef3-9fd1-299626862dbd")
                .action(ActionType.MOD)
                .type("REVIEW")
                .content("안좋아요!")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .attachedPhotoIds(attachedPhotoIds)
                .build();

        mainService.modify(requestDTO);
    }
    @Test
    @DisplayName("유저가 다른 경우 수정 이벤트")
    @Transactional
    void different_user_modify() {
        //given
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("acc3644b-3e8a-4349-a446-eae670c06f37")
                .action(ActionType.MOD)
                .type("REVIEW")
                .content("안좋아요!")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .attachedPhotoIds(attachedPhotoIds)
                .build();

        try{
            mainService.modify(requestDTO);
        }catch (CustomException e){

        }

    }

    @Test
    @DisplayName("글과 사진이 있는 리뷰에서 사진을 모두 삭제한 경우")
    void delete_photo_modify() {
        //given
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("e47a2bd8-f4ad-4ef3-9fd1-299626862dbd")
                .action(ActionType.MOD)
                .type("REVIEW")
                .content("안좋아요!")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();

        mainService.modify(requestDTO);

    }

    @Test
    @DisplayName("글만 작성된 리뷰에 사진이 등록된 경우 ")
    void input_photo_modify() {
        //given
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("e47a2bd8-f4ad-4ef3-9fd1-299626862dbd")
                .action(ActionType.MOD)
                .type("REVIEW")
                .content("안좋아요!")
                .attachedPhotoIds(attachedPhotoIds)
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();

        mainService.modify(requestDTO);

    }

    @Test
    @DisplayName("리뷰 삭제테스트")
    void delete() {
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("e47a2bd8-f4ad-4ef3-9fd1-299626862dbd")
                .action(ActionType.DELETE)
                .type("REVIEW")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();

        mainService.delete(requestDTO);
    }
    @Test
    @DisplayName("작성자가 다른 삭제")
    @Transactional
    void different_user_delete() {
        RequestDTO requestDTO = RequestDTO.builder()
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("4d992e1a-55c2-4cc2-92bc-16712de746b3")
                .action(ActionType.DELETE)
                .type("REVIEW")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();

        try{
            mainService.delete(requestDTO);
        }catch (CustomException e){

        }

    }
}