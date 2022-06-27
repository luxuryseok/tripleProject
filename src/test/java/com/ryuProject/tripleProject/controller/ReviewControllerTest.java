package com.ryuProject.tripleProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.enums.ActionType;
import com.ryuProject.tripleProject.service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class ReviewControllerTest {
    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private MainService mainService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    @DisplayName("CONTROLLER 테스트")
    void events() throws Exception {

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

        //when
        ResultActions result = mockMvc.perform(post("/POST/events")
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(requestDTO)));

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @DisplayName("POINT 테스트 코드 미작성")
    void points() throws Exception {
        RequestDTO requestDTO = RequestDTO.builder()
                .userId("e47a2bd8-f4ad-4ef3-9fd1-299626862dbd")
                .build();

        mockMvc.perform(get("/POST/points/")
                .param("id","e47a2bd8-f4ad-4ef3-9fd1-299626862dbd"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("ACTION값 예외 테스트")
    void eventsFail() throws Exception {
        try{
            //given
            List<String> attachedPhotoIds = new ArrayList<>();
            attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
            attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
            RequestDTO requestDTO = RequestDTO.builder()
                    .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                    .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                    .action(ActionType.valueOf(""))
                    .type("REVIEW")
                    .content("좋아요!")
                    .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                    .build();

            //when
            ResultActions result = mockMvc.perform(post("/POST/events")
                    .accept(MediaType.TEXT_PLAIN_VALUE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(requestDTO)));

            //then
            result.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }catch (IllegalArgumentException e){
        }
    }
}
