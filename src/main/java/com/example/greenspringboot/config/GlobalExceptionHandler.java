package com.example.greenspringboot.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
 * 옵셔널 객체 예외처리 설명:
 * - JPA의 findById 메서드는 엔티티에서 설정한 ID(PK)를 기준으로 Optional 객체를 반환함
 * - 레포지토리에서 별도 메서드를 만들지 않아도 JPA가 자동 생성해 줌
 * - 서비스 레이어에서 orElseThrow를 사용해 데이터가 없을 경우 IllegalArgumentException 예외 발생
 * - 이 예외를 글로벌 예외처리기에서 잡아 적절한 에러 페이지로 안내
 */


/*
 * UX 관점: 입력 오류는 try-catch로 처리해 페이지를 유지하며 메시지 제공
 * 시스템 관점: NullPointer 등 예측 불가 오류는 글로벌 예외 처리기로 일괄 처리
 */

//글로벌 예외처리기를 어떻게 써야 더 좋은건지 생각해보기

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

//    옵셔널 객체 예외 메서드
    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleIllegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        log.warn("잘못된 요청 예외 발생: {}", e.getMessage());

        String uri = request.getRequestURI();

        if (uri.startsWith("/api/")) {
            // API 호출이면 JSON 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "잘못된 요청", "message", e.getMessage()));
        } else {
            return "errorPage";
        }
    }

//    널체크 메서드
    @ExceptionHandler(NullPointerException.class)
    public Object handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        log.error("널 포인터 예외 발생", e);

        String uri = request.getRequestURI();

        if (uri.startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "내부 서버 오류", "message", e.getMessage()));
        } else {
            return "errorPage";
        }
    }

//    나머지 예외
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        log.error("예외 발생: ", e);

        String uri = request.getRequestURI();

        if (uri.startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "서버 오류", "message", e.getMessage()));
        } else {
            return "errorPage";
        }
    }
}
