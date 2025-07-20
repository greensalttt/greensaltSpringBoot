package com.example.greenspringboot.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("예외 발생: ", e);
        return "errorPage";  // 에러 페이지 뷰 이름
    }

    /*
     * 옵셔널 객체 예외처리 설명:
     * - JPA의 findById 메서드는 엔티티에서 설정한 ID(PK)를 기준으로 Optional 객체를 반환함
     * - 레포지토리에서 별도 메서드를 만들지 않아도 JPA가 자동 생성해 줌
     * - 서비스 레이어에서 orElseThrow를 사용해 데이터가 없을 경우 IllegalArgumentException 예외 발생
     * - 이 예외를 글로벌 예외처리기에서 잡아 적절한 에러 페이지로 안내
     */

}
