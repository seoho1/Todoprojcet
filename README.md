
# 목차
## 1. API명세서
## 2. ERD
## 3. Query 목록

## 1.  API 명세서

| 기능  | Method | URL     | Request | Response | 상태코드
|-------|------|----------|-------|------|----------|
| 할일 등록  | POST   | /todos | { <br>"todo" : "할일4", <br> "author" : "황서호",<br>"password" : 1234 <br>} | 등록 정보 | 200: 정상 등록
| 할일 전체 조회 | GET | /todos | 요청 param | 다건 응답 정보 | 200: 정상 조회
| 할일 조회 | GET | /todos/{id} | 요청 param | 단건 응답 정보 | 200: 정상 조회
| 할일 수정 | PUT | /todos/{id} | {<br>"todo": "수정된 내용",<br>"password" : "1234"<br>} | 수정 정보 | 200: 정상 수정
| 할일 삭제 | DELETE | /todos/{id} | {<br>"password": "1234" <br>} | - | 삭제 정보 | 200: 정상 삭제
| 수정자 조회 | GET | /todos/author | 요청 param | 다건 응답 정보 | 200 : 정상 조회
| 작성자 조회 | GET | /totods/author/{author} | 요청 param | 다건 응답 조회 | 200 : 정상 조회


## 2. ERD
![image](https://github.com/user-attachments/assets/3dfcaa3f-21e0-4dde-a35a-5e0ebcba3c53)


## 3. Query 목록
![image](https://github.com/user-attachments/assets/3fd960aa-69cf-46b2-bae2-a6bdeddea04c)
