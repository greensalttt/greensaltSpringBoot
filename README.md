
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
   
