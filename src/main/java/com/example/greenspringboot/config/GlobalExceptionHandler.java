package com.example.greenspringboot.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 옵셔널 객체 예외처리 설명:
 * - JPA의 findById 메서드는 엔티티에서 설정한 ID(PK)를 기준으로 Optional 객체를 반환함
 * - 레포지토리에서 별도 메서드를 만들지 않아도 JPA가 자동 생성해 줌
 * - 서비스 레이어에서 orElseThrow를 사용해 데이터가 없을 경우 IllegalArgumentException 예외 발생
 * - 이 예외를 글로벌 예외처리기에서 잡아 적절한 에러 페이지로 안내
 */


// 로그 기록 어노테이션
@Slf4j
// 트라이캣치 없어도 전역 예외처리 가능 어노테이션
@ControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException e) {
        log.warn("잘못된 요청 예외 발생: {}", e.getMessage());
        return "errorPage";
    }

    // NullPointerException 처리
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException e) {
        log.error("널 포인터 예외 발생", e);
        return "errorPage";
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("예외 발생: ", e);
        return "errorPage";
    }
}

