## 인디음악 커뮤니티 녹색소금  

![메인페이지](https://github.com/user-attachments/assets/6af207dc-411e-4174-b308-098587b8d834)
![메인 히스토리](https://github.com/user-attachments/assets/aa58e739-4fcc-48f7-97db-e867d57d9b82)
![로그인](https://github.com/user-attachments/assets/ce7eb3de-3e0a-4fd9-b08a-17da5f6009ec)
![회원가입](https://github.com/user-attachments/assets/2cfd2468-e035-4756-a89a-414e4c3005f7)
![Image](https://github.com/user-attachments/assets/00546d23-2433-4f5e-a4fb-e3de2ff56ed8)
![Image](https://github.com/user-attachments/assets/eed054c2-0c1d-4a41-98a9-3f3548c334a1)
![마이페이지](https://github.com/user-attachments/assets/579f217b-b834-4996-ae9f-a467df35e780)
![개인정보 수정](https://github.com/user-attachments/assets/cbe2c221-86a4-4181-b301-2e5d0b9ae516)
![비밀번호 변경](https://github.com/user-attachments/assets/d426b105-c725-44f4-94fe-752fceff1a6e)
![게시판 목록](https://github.com/user-attachments/assets/a66cc888-e6ad-464b-a47d-0d56b70c8a3d)
![게시판 검색](https://github.com/user-attachments/assets/2b4e14d1-5c63-492c-8eeb-b5bd6ef69a12)
![게시글 및 댓글](https://github.com/user-attachments/assets/2283f7d9-0212-4bdc-8190-9c31be65f6a4)


## 개발환경

> Java 11 + Spring Boot 2.8.7 + JSP + JPA

```yaml
DBMS: MySQL 8.x
WAS : tomcat 9.0.x
VCS: github
Build: Maven
IDE: IntelliJ IDEA
```
<br>


## Commit Message Convention

```
feat: 새로운 기능 추가
fix: 버그 수정
test: 테스트 코드
remove: 파일 삭제
docs재로그인시 암호화된 이메일 쿠키가 복호화되어 로그인폼에 표시되게 구현
    
#### 관리자페이지
   + 총 회원수, 게시글 수, 댓글 수를 확인 할 수 있는 대시보드 구현

#### 마이페이지
   + 개인정보 수정
   + 비밀번호 변경
     + 비밀번호 변경시 보안 강화를 위해 자동으로 로그아웃

#### 게시판
   + 게시글, 댓글, 대댓글 CRUD 구현
   + SearchCondition를 사용한 검색 필터
   + PageHandler를 사용한 페이징 네비게이션
   + RESTful API와 Ajax를 사용하여 비동기 통신으로 댓글 관리
     
   
