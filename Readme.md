# [TRIPLE] 주문(Order) 백엔드 엔지니어 백엔드 엔지니어 과제 전형
***
## 개발환경
* Java 8 + Spring Boot 2.6.5
* MySQL 8.0.29
* Mybatis
* Gradle 7.3.3
* GitHub
* IntelliJ
***
## 테스트
- API 테스트 URL : Swagger : http://localhost:8081/swagger-ui/
- 서버실행시 `schema.sql` 파일로 테이블이 자동생성됩니다.
- 입력 데이터
```
  {
  "type": "REVIEW",
  "action": "ADD", /* "MOD", "DELETE" */
  "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
  "content": "좋아요!",
  "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
  "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
  "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
  }
```
***
## 기능구현 ##
## 1. 이벤트 : POST/events ##

### ■ 리뷰신규생성(create) ###
- 제약   
작성된 리뷰가 존재   
리뷰 내용이 없는 경우   
- 처리   
리뷰, 이미지를 저장   
포인트 이력 저장 : 합산점수가 아닌 이력마다 기록하도록 함   
  "ADD_CONTENT", 1   
  "ADD_IMAGE", 1   
  "ADD_FIRST_PLACE", 1   
  "RETRIEVE_IMAGE", -1

#### ▷ 정상케이스
- DB 확인

#### ▷ 비정상케이스
- {
  "status": 400,
  "message": "이미 작성된 리뷰가 존재합니다."
  }
- {
  "status": 400,
  "message": "리뷰 내용이 존재하지 않습니다."
  }

### ■ 리뷰수정(modify) ###
- 제약   
동일 ID인 경우만 수정 가능   
리뷰 내용이 없는 경우
- 처리   
리뷰업데이트   
점수 이력 저장     
-> 글만 작성된 리뷰에 사진추가 (+1)  
-> 글과 사진이 작성된 리뷰에 사진삭제 (-1)   
이미지 삭제 후 저장

#### ▷ 정상케이스
- DB 확인

#### ▷ 비정상케이스
- {
  "status": 400,
  "message": "작성자만 수정할 수 있습니다."
  }
- {
  "status": 400,
  "message": "리뷰 내용이 존재하지 않습니다."
  }

### ■ 리뷰삭제(delete) ###
- 제약   
  동일 ID인 경우만 삭제 가능   
- 처리   
  포인트 이력 저장   
  -> 기존 적립된 포인트 회수      
  이미지 삭제  
  리뷰삭제

#### ▷ 정상케이스
- DB 확인

#### ▷ 비정상케이스
- {
"status": 400,
"message": "작성자만 삭제할 수 있습니다."
}

## 2. 유저 포인트조회 : POST/points/{id} ##
- 유저의 포인트를 조회한다.
- 포인트 이력 테이블에서 유저정보로 합계로 DB 에서 조회한다.

#### ▷ 정상케이스
- 포인트점수 조회

#### ▷ 비정상케이스