<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setTimeZone value="Asia/Seoul" />

<head>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">
    <title>결제 페이지</title>
    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #paymentContainer {
            margin: 200px auto 150px auto;
            width: 60%;
            flex-grow: 1;
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
        }


        h2 {
            margin-bottom: 30px;
            font-size: 24px;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }

        .form-group {
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
            font-size: 16px;
        }

        .form-group input[type="number"],
        .form-group select {
            width: 100%;
            padding: 10px 12px;
            font-size: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        /*#totalPrice {*/
        /*    display: block;*/
        /*    margin-top: 5px;*/
        /*    font-size: 18px;*/
        /*    font-weight: bold;*/
        /*}*/

        .submit-btn {
            margin-top: 30px;
            text-align: center;
        }

        .submit-btn button {
            padding: 12px 30px;
            background-color: #1976d2;
            color: white;
            border: none;
            font-size: 16px;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .submit-btn button:hover {
            background-color: #1565c0;
        }

    </style>
</head>

<body>
<div id="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <div id="paymentContainer">
        <h2>결제 정보 확인</h2>

        <form action="/payment/submit/" method="post">
            <input type="hidden" name="pno" value="${orderDto.pno}"/>

            <div class="form-group">
                <label class="form-label">공연 제목</label>
                <span>${performanceDto.title}</span>
            </div>

            <div class="form-group">
                <label class="form-label">주문자 이름</label>
                <span>${orderDto.orderName}</span>
            </div>

            <div class="form-group">
                <label class="form-label">티켓 수량 </label>
                <span>${orderDto.ticketCount}장</span>
            </div>

            <div class="form-group">
                <label class="form-label">최종 결제 금액</label>
                <span>${orderDto.totalPrice}원</span>
            </div>

            <div class="form-group">
                <label class="form-label">결제 방법</label>
                <select name="paymentMethod" required>
                    <option value="toss">토스 페이</option>
                </select>
            </div>


            <div class="submit-btn">
                <button type="button" onclick="requestTossPayment()">결제하기</button>
            </div>

        </form>
    </div>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>

</body>

<script src="https://js.tosspayments.com/v1/payment"></script>

<script>
    function requestTossPayment() {
        const tossPayments = TossPayments("test_ck_4yKeq5bgrpXkYXZmgWZ0rGX0lzW6");

        tossPayments.requestPayment('카드', {
            amount: ${orderDto.totalPrice}, // 결제 금액
            // orderId: 'order-' + new Date().getTime(), // 유니크한 주문번호
            orderId: '${orderDto.orderId}',
            orderName: '${orderDto.orderName}',
            successUrl: location.origin + '/payment/success',
            failUrl: location.origin + '/payment/fail'
        })
            .catch(function (error) {
                if (error.code === 'USER_CANCEL') {
                    alert('결제를 취소했어요.');
                } else {
                    alert('결제 중 오류가 발생했어요.');
                    console.error(error);
                }
            });
    }
</script>
