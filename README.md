![home](https://github.com/user-attachments/assets/1e7758ab-546d-46cb-8f6b-417bd3ba2a98)

## 음악 커뮤니티 녹색소금

https://greensalt.site/

## 배포환경
> Spring Boot + JSP

```
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
style: 코드 스타일 및 주석 변경  
docs: 문서 수정 (README 등)  
test: 테스트 코드 관련  
chore: 기타 변경사항 (빌드, 설정, 삭제 등)
```

## Backend Architecture

#### 세션 기반 인증 및 접근 제어
   + Interceptor를 통해 인증 및 권한 체크 수행
   + 로그인 시 사용자 고유번호를 세션에 저장하여 인증 상태 유지

#### 서비스 레이어에서 Optional의 orElseThrow 활용
   + 데이터가 없을 경우 즉시 IllegalArgumentException 예외 발생
   + 비즈니스 로직 단계에서 무결성 보장과 조기 오류 발견

#### 글로벌 예외 처리기(@ControllerAdvice) 적용
   + 컨트롤러 내 try-catch 중복 코드 제거
   + 예외 발생 시 자동으로 로그 기록 및 에러 페이지 반환

#### 미결제 주문 자동 정리 스케줄러 도입
   + @Scheduled 스케줄링으로 일정 시간 미결제 상태의 주문 데이터를 자동 정리
   + 트랜잭션과 조건 삭제 쿼리를 통해 불필요한 데이터 누적 방지 및 DB 성능 최적화

#### 모든 주요 테이블에 감사 필드 추가
   + 생성일, 수정일, 생성자, 수정자 자동 기록
   + 변경 이력 추적 및 데이터 감사 목적 활용 가능
<br>

## 개발기능

#### 회원가입
   + 587포트와 Ajax를 활용하여 이메일 인증
   + 카카오 API를 활용하여 주소 찾기
   + Security를 활용하여 비밀번호 해시화

#### 로그인
   + 회원, 관리자 로그인 구분
   + 비밀번호 찾기 
   + Cookie를 활용한 이메일 저장 체크박스
     + 암호화된 이메일을 쿠키에 저장, 재로그인 시 복호화하여 로그인 폼에 표시

#### 관리자페이지
   + 회원 목록과 개인정보 변경 이력 확인
   + 앨범, 공연, 공지사항 업로드 및 수정과 삭제
   + 규칙에 맞지 않는 회원의 게시글, 댓글 삭제
   + 게시글, 댓글 이력 및 주체(회원/관리자) 확인

#### 마이페이지
   + 작성 게시글, 댓글 리스트, 예매 리스트 확인
   + 개인정보 수정, 비밀번호 변경
   + 회원탈퇴
     + 회원 상태 코드 변경으로 로그인 차단

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
   + Toss Payments의 SDK와 API를 활용하여 테스트 결제 기능 구현
   + 주문 및 결제 테이블 분리, 주문 시 상태 컬럼 미결제로 주문 데이터 저장
   + 결제 완료 시 결제 데이터 저장 및 주문 테이블 상태 컬럼 '결제 완료'로 갱신
   + 10분 간 미결제 유지 시 주문 데이터를 자동 삭제하는 스케줄러 운영
   + 예매 완료 내역은 주문, 결제, 공연 테이블을 조인하여 조회 처리

#### 공지사항
   + 업로드한 공지사항을 볼 수 있는 리스트
   + 개별 클릭시 공지사항 상세 페이지로 이동
