<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setTimeZone value="Asia/Seoul" />

<head>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">
    <title>주문 페이지</title>
    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #orderContainer {
            margin: 200px auto 150px auto;
            width: 60%;
            flex-grow: 1;
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
        }

        .ticket-note {
            margin-top: 6px;
            font-size: 13px;
            color: #d32f2f;
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
        .form-group input[type="text"] {
            width: 100%;
            padding: 10px 12px;
            font-size: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .submit-btn {
            margin-top: 30px;
            text-align: center;
        }

        .submit-btn button {
            padding: 12px 30px;
            background-color: #43a047;
            color: white;
            border: none;
            font-size: 16px;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .submit-btn button:hover {
            background-color: #388e3c;
        }
    </style>
</head>

<body>
<div id="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <div id="orderContainer">
        <h2>주문 정보 입력</h2>

        <form action="/payment" method="post">

            <input type="hidden" name="pno" value="${performanceDto.pno}" />

            <div class="form-group">
                <label class="form-label">공연 제목</label>
                <span>${performanceDto.title}</span>
            </div>

            <div class="form-group">
                <label class="form-label" for="ordererName">주문자 이름</label>
                <input type="text" id="ordererName" name="ordererName" placeholder="주문자 이름을 입력하세요" required />
            </div>

            <div class="form-group">
                <label class="form-label">티켓 수량 (1인 최대 2매)</label>
                <input type="number" name="ticketCount" value="1" min="1" max="2" required />
                <p class="ticket-note">※ 티켓은 현장 수령입니다.</p>
            </div>

            <div class="form-group">
                <label class="form-label">결제 금액</label>
                <span id="totalPrice" data-price="${performanceDto.price}">
                    ${performanceDto.price} 원
                </span>
            </div>

            <div class="submit-btn">
                <button type="submit">주문 계속하기</button>
            </div>
        </form>
    </div>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>

<script>
    const ticketInput = document.querySelector('input[name="ticketCount"]');
    const priceDisplay = document.getElementById("totalPrice");

    const pricePerTicket = parseInt(priceDisplay.dataset.price);

    function updateTotal() {
        let count = parseInt(ticketInput.value);

        if (isNaN(count) || count < 1) {
            count = 1;
            ticketInput.value = 1;
        } else if (count > 2) {
            count = 2;
            ticketInput.value = 2;
        }

        const total = count * pricePerTicket;
        priceDisplay.textContent = total.toLocaleString() + " 원";
    }

    ticketInput.addEventListener("input", updateTotal);
    updateTotal();
</script>
</body>
