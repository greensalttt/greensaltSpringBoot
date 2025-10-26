![home](https://github.com/user-attachments/assets/1e7758ab-546d-46cb-8f6b-417bd3ba2a98)

## 음악 커뮤니티 및 공연 예매 사이트 녹색소금

https://greensalt.site/

## 배포환경

```
Packaging: WAR
Build: Maven
Deployment: AWS EC2
Deployment Method: Docker
OS: Ubuntu(Linux)
WAS: Tomcat 9(Docker container)
DBMS: MySQL(AppPaaS)
SSL: Cloudflare(HTTPS)
```


## 개발환경

> Spring Boot + JSP + Vue.js + JPA

```
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
refactor: 기능 변경 없이 구조 개선
docs: README.md 문서 수정  
test: 테스트 코드 관련  
chore: 기타 변경사항 (빌드, 설정, 삭제, 주석, 버그 추적 등)
```

## Backend Architecture

#### 사용자 웹 화면 세션 기반 인증 및 접근 제어
   + Interceptor를 통해 인증 및 권한 체크 수행
   + 회원 로그인 시 사용자 고유번호를 세션에 저장하여 인증 상태 유지

#### 관리자 페이지 JWT 기반 인증 및 RESTful API 연동
   + 관리자 로그인 시 JWT 발급 및 로컬 스토리지 저장
   + axios 요청 시 토큰을 헤더에 포함하여 서버에서 검증 및 권한 확인
   + Vue.js SPA 구조와의 통신을 위해 RESTful API를 설계 및 구현

#### 서비스 레이어에서 Optional의 orElseThrow 활용
   + 데이터가 없을 경우 즉시 IllegalArgumentException 예외 발생
   + 비즈니스 로직 단계에서 무결성 보장과 조기 오류 발견

#### 글로벌 예외 처리기 적용
   + 컨트롤러 내 try-catch 중복 코드 제거
   + 예외 발생 시 자동으로 로그 기록 및 에러 페이지 반환

#### 미결제 주문 자동 상태 변경 스케줄러 도입
   + @Scheduled를 이용해 일정 시간 지난 미결제 상태의 주문을 '만료됨'으로 변경
   + 트랜잭션과 조건 업데이트 쿼리를 통해 비정상 주문에 대한 정리 자동화

#### 모든 주요 테이블에 감사 필드 추가
   + 생성일, 수정일, 생성자, 수정자 자동 기록
   + 변경 이력 추적 및 데이터 감사 목적 활용 가능
<br>

## 개발기능

#### 회원가입
   + 587포트와 Ajax를 활용하여 이메일 인증
   + 카카오 API를 활용하여 주소 찾기
   + Security를 활용하여 비밀번호 해시화

#### 회원 로그인
   + 세션을 통한 로그인 권한 및 비밀번호 찾기 
   + Cookie를 활용한 이메일 저장 체크박스
     + 암호화된 이메일을 쿠키에 저장, 재로그인 시 복호화하여 로그인 폼에 표시

#### 관리자페이지
   + Vue.js와 RESTful API 기반으로 개발
   + JWT 기반 인증 및 토큰을 통한 권한 검증 방식으로 구현
   + 회원 목록과 개인정보 변경 이력 확인
   + 앨범, 공연, 공지사항 업로드 및 수정과 삭제
   + 규칙 위반 게시글, 댓글 관리 및 이력, 주체 확인

#### 마이페이지
   + 작성 게시글, 댓글 리스트 확인
   + 주문 내역, 예매 내역 확인
      + 주문 내역에서 대기중, 만료됨, 결제완료 3가지 주문 상태 코드 확인
   + 개인정보 수정, 비밀번호 변경
   + 회원탈퇴
     + 회원탈퇴시 상태 코드 변경으로 로그인 차단

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

#### 공연 예매
   + Toss Payments의 SDK와 API를 활용하여 테스트 결제 기능 
   + 주문 및 결제 테이블 분리, 주문 시 상태 컬럼 `대기중`로 주문 데이터 저장
   + 결제 완료 시 결제 데이터 저장 및 주문 테이블 상태 컬럼 `결제 완료`로 갱신
   + 10분 간 미결제 유지 시 주문 상태 컬럼 `만료됨`으로 변경되는 스케줄러 운영
   + 예매 완료 내역은 주문, 결제, 공연 테이블 조인하여 조회

#### 공지사항
   + 업로드한 공지사항을 볼 수 있는 리스트
   + 개별 클릭시 공지사항 상세 페이지로 이동
