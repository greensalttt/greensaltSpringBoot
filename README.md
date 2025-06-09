![home](https://github.com/user-attachments/assets/1e7758ab-546d-46cb-8f6b-417bd3ba2a98)

## 음악 커뮤니티 녹색소금

https://greensalt.site/

## 배포환경
> Spring Boot + JSP

```yaml
Packaging: WAR
Build: Maven
Deployment: AWS EC2
Deployment Method: PuTTY + FileZilla
OS: Ubuntu(Linux)
WAS: External Tomcat
DBMS: MySQL(AppPaaS)
SSL: Cloudflare(HTTPS)
```


## 개발환경

> Spring Boot + JSP + JPA

```yaml
WAS : Embedded Tomcat
DBMS: MySQL
VCS: github
Build: Maven
IDE: IntelliJ IDEA
```

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

## 개발기능

#### 홈
   + 앨범, 공연 정보를 최신순으로 표시

#### 회원가입
   + 587포트와 Ajax를 활용하여 이메일 인증
   + 클라이언트, 서버 2중 유효성 검사
   + 카카오 API를 활용하여 주소 찾기
   + Security를 활용하여 비밀번호 해시화

#### 로그인
   + 회원, 관리자 로그인 구분
   + 세션을 통한 비밀번호 찾기 
   + Cookie를 활용한 이메일 저장 체크박스
     + 체크하고 로그인에 성공하면 이메일이 암호화 된 상태로 쿠키에 저장
     + 재로그인시 암호화 된 이메일 쿠키가 복호화 되어 로그인폼에 표시
   + Interceptor를 활용하여 접근 제어 강화

#### 관리자페이지
   + 회원 목록을 볼 수 있는 리스트
   + 앨범, 공연 정보를 업로드 및 수정, 삭제를 할 수 있는 시스템

#### 마이페이지
   + 개인정보 수정
   + 비밀번호 변경
   + 회원탈퇴

#### 게시판
   + 게시글, 댓글, 대댓글 CRUD
   + SearchCondition를 사용한 검색 필터
   + PageHandler를 사용한 페이징 네비게이션
   + RESTful API와 Ajax를 사용하여 비동기 통신으로 댓글 관리

#### 앨범리스트, 공연리스트
   + 업로드한 앨범, 공연 정보를 확인할 수 있는 리스트
   + 개별 클릭시 상세 페이지로 이동
   + SearchCondition를 사용한 검색 필터
   + PageHandler를 사용한 페이징 네비게이션
   
