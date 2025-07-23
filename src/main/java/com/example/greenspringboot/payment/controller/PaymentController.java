package com.example.greenspringboot.payment.controller;
import com.example.greenspringboot.order.service.OrderService;
import com.example.greenspringboot.payment.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    PaymentService paymentService;


    @GetMapping("/success")
    public String paymentSuccess(
            @SessionAttribute("cId") Integer cId,
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Integer amount,
            Model model
    ) {
        try {
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
//                간편결제 종류 확인
                JSONObject json = new JSONObject(response.body());
                String method = json.getString("method");
                String provider = "";

                if (method.equals("간편결제") && json.has("easyPay")) {
                    provider = json.getJSONObject("easyPay").getString("provider"); // KAKAO_PAY, NAVER_PAY 등
                }
                String paymentMethod = provider.isEmpty() ? method : provider;

                // DB 저장: 서비스 호출
                paymentService.savePayment(orderId, paymentKey, paymentMethod, amount, cId);

                return "paymentSuccess";
            } else {
                return "errorPage";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

}
