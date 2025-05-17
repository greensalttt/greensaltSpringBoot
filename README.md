## 인디음악 커뮤니티 녹색소금  

![인덱스](https://github.com/user-attachments/assets/12930e8a-cbdf-4bd5-a9cb-bad5c30865f7)
![앨범히스토리](https://github.com/user-attachments/assets/2e654d8b-69e9-4b07-abf3-fc4fa931c95d)
![로그인](https://github.com/user-attachments/assets/cec83a96-cf38-43bc-bdc2-2a2e543cbe69)
![회원가입](https://github.com/user-attachments/assets/74fb9831-b8c8-4a6a-b147-add9de481efe)
![비밀번호찾기](https://github.com/user-attachments/assets/396e371f-383a-4d30-86b8-ea41e973f2fc)
![비밀번호찾기2](https://github.com/user-attachments/assets/7c2b372f-90de-41d1-8a68-d831626fec58)
![마이페이지](https://github.com/user-attachments/assets/5934dae1-da1f-4a8e-9dbd-0a1c7ac19cf9)
![개인정보 수정](https://github.com/user-attachments/assets/cbe2c221-86a4-4181-b301-2e5d0b9ae516)
![회원탈퇴](https://github.com/user-attachments/assets/30689756-b731-407b-8e9a-31166c81f7d1)
![비밀번호변경](https://github.com/user-attachments/assets/f17361b1-14d9-4251-bea2-50e66ced780e)
![게시판](https://github.com/user-attachments/assets/12649633-3658-4f3b-bf98-751b88795f82)
![게시글검색](https://github.com/user-attachments/assets/1f59e8eb-1ff7-44de-849a-d5b180018114)
![게시글 및 댓글](https://github.com/user-attachments/assets/2283f7d9-0212-4bdc-8190-9c31be65f6a4)
![앨범리스트](https://github.com/user-attachments/assets/e806500b-b37e-4baa-a9e0-bdea1f0e13e5)
![앨범상세페이지](https://github.com/user-attachments/assets/d9c63fe2-30cd-4bb1-8f96-832975d5c268)
![앨범등록](https://github.com/user-attachments/assets/6cc8f4a7-a13f-4cae-8eea-5ae64a264bbe)


## 개발환경

> Java + Spring Boot + JSP + JPA

```yaml
DBMS: MySQL
WAS : tomcat
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
docs: README 변경
style: 코드 스타일 변경
comment: 주석 추가 및 변경
chore: 빌드 업데이트 및 자잘한 수정
refactor: 코드 구조 개선 (기능 변경 X)

```

<br>


## 개발기능 (~ing)

#### 인덱스
   + 앨범 History
     + 클릭한 앨범을 로컬 스토리지에 저장하고 최신순으로 표시

#### 회원가입
   + 587포트와 Ajax를 활용하여 이메일 인증
   + 2중 유효성 검사
     + 클라이언트 측 검사로 서버 부하 감소 및 사용자 경험 개선
     + Validator를 사용하여 서버 유효성 검사 
   + 카카오 API를 활용하여 주소 찾기
   + Security를 활용하여 비밀번호 해시화

#### 로그인
   + 회원, 관리자 로그인 구분
   + 세션을 통한 비밀번호 찾기 기능 구현
   + Cookie를 활용한 이메일 저장 체크박스
     + 체크하고 로그인에 성공하면 이메일이 암호화 된 상태로 쿠키에 저장
     + 재로그인시 암호화 된 이메일 쿠키가 복호화 되어 로그인폼에 표시되게 구현
   + Interceptor를 활용하여 접근 제어 강화

#### 관리자페이지
   + 총 회원수, 게시글 수, 댓글, 앨범 수를 확인 할 수 있는 대시보드 구현
   + 관리자 페이지에서 앨범을 업로드 할 수 있는 페이지 구현

#### 마이페이지
   + 개인정보 수정
   + 비밀번호 변경
   + 회원탈퇴

#### 게시판
   + 게시글, 댓글, 대댓글 CRUD 구현
   + SearchCondition를 사용한 검색 필터
   + PageHandler를 사용한 페이징 네비게이션
   + RESTful API와 Ajax를 사용하여 비동기 통신으로 댓글 관리

#### 앨범리스트
   + 새로운 앨범을 확인할 수 있는 앨범 리스트 목록 구현
   + 관리자 업로드 시 앨범 리스트에 등록되며 개별 앨범 클릭시 상세 페이지 이동
   
