package com.ryuProject.tripleProject.controller;

import com.ryuProject.tripleProject.dto.RequestDTO;
import com.ryuProject.tripleProject.enums.ActionType;
import com.ryuProject.tripleProject.enums.ErrorMessage;
import com.ryuProject.tripleProject.exception.CustomException;
import com.ryuProject.tripleProject.service.MainService;
import com.ryuProject.tripleProject.service.subService.UserPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/POST")
public class ReviewController {

    private MainService mainService;
    private UserPointService userPointService;

    public ReviewController(MainService mainService, UserPointService userPointService) {
        this.mainService = mainService;
        this.userPointService = userPointService;
    }

    /**
     * 리뷰 이벤트 처리 컨트롤러
     */
    @PostMapping("/events")
    public ResponseEntity<?> events(@RequestBody RequestDTO requestDTO) throws IllegalArgumentException{
        ActionType actionType = requestDTO.getAction();

        //action 타입과 다른 경우 null로 리턴
        if(Objects.isNull(actionType)){
            throw new CustomException(ErrorMessage.NOT_EXISTS_ACTION_TYPE);
        }

        switch (actionType){
            case ADD:
                mainService.create(requestDTO);
                break;
            case MOD:
                mainService.modify(requestDTO);
                break;
            case DELETE:
                mainService.delete(requestDTO);
                break;
            default:
                throw new CustomException(ErrorMessage.NOT_EXISTS_ACTION_TYPE);
        }

        return ResponseEntity.ok().build();
    }

    /**
     * user 포인트 조회
     * @param userId
     * @return
     */
    @GetMapping("/points/{id}")
    public ResponseEntity<?> points(@PathVariable(name="id") String userId){

        return ResponseEntity.ok().body(mainService.getUserPoint(userId));
    }
}
