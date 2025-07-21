package com.example.greenspringboot.payment.controller;
import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.order.service.OrderService;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Controller
@RequestMapping("/payment")
public class PaymentController {


    @Value("${toss.secret.key}")
    private String secretKey;

    @Autowired
    OrderService orderService;


//    @GetMapping("/success")
//    public String paymentSuccess(
//            @SessionAttribute("cId") Integer cId,
//            @RequestParam String paymentKey,
//            @RequestParam String orderId,
//            @RequestParam Long amount,
//            Model model
//    ) {
//
//        try {
//            // 승인 요청
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
//                    .header("Authorization", "Basic " + Base64.getEncoder()
//                            .encodeToString((secretKey + ":").getBytes()))
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(
//                            "{\"paymentKey\":\"" + paymentKey + "\",\"orderId\":\"" + orderId + "\",\"amount\":" + amount + "}"
//                    ))
//                    .build();
//
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // 응답 처리
//            if (response.statusCode() == 200) {
//                model.addAttribute("message", "결제 성공!");
//                return "paymentSuccess"; // 결제 성공 페이지 JSP
//            } else {
//                model.addAttribute("error", response.body());
//                return "errorPage";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("error", "결제 승인 중 오류가 발생했습니다.");
//            return "errorPage";
//        }
//    }

    @GetMapping("/success")
    public String paymentSuccess(
            @SessionAttribute("cId") Integer cId,
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Long amount,
            @ModelAttribute OrderDto orderDto,
            Integer pno,
            Model m
    ) {
        try {
            // 1. 결제 승인
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
                    .header("Authorization", "Basic " + Base64.getEncoder()
                            .encodeToString((secretKey + ":").getBytes()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{\"paymentKey\":\"" + paymentKey + "\",\"orderId\":\"" + orderId + "\",\"amount\":" + amount + "}"
                    ))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // 2. 세션에서 주문 정보 꺼내기
                OrderDto orderConfirm = orderService.orderConfirm(orderDto);
//                PerformanceDto performanceDto = orderService.orderPage(pno);

                m.addAttribute("orderDto", orderConfirm);
//                m.addAttribute("performanceDto", performanceDto);
                // 4. 주문 저장
                orderService.saveOrder(orderDto, orderId, cId);

                // 5. 완료 페이지
//                model.addAttribute("message", "결제 성공!");
                return "paymentSuccess";
            } else {
                m.addAttribute("error", response.body());
                return "errorPage";
            }

        } catch (Exception e) {
            e.printStackTrace();
//            model.addAttribute("error", "결제 승인 중 오류가 발생했습니다.");
            return "errorPage";
        }
    }


}
