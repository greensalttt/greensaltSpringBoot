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
            width: 70%;
            flex-grow: 1;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-label {
            font-weight: bold;
        }
        .submit-btn {
            margin-top: 30px;
        }
    </style>
</head>

<body>
<div id="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <br><br>

    <div id="paymentContainer">
        <h2>결제 정보 확인</h2>

        <form action="<c:url value='/payment/submit'/>" method="post">
            <input type="hidden" name="pno" value="${pno}" />
            <input type="hidden" name="userId" value="${user.id}" />

            <div class="form-group">
                <label class="form-label">공연 제목:</label>
                <span>${pno}</span>
            </div>

            <div class="form-group">
                <label class="form-label">티켓 수량:</label>
                <input type="number" name="ticketCount" value="1" min="1" required />
            </div>

            <div class="form-group">
                <label class="form-label">결제 금액 (1장당 15,000원):</label>
                <span id="totalPrice">15,000 원</span>
            </div>

            <div class="form-group">
                <label class="form-label">결제 방법:</label>
                <select name="paymentMethod" required>
                    <option value="toss">토스 페이</option>
                </select>
            </div>

            <div class="submit-btn">
                <button type="submit">결제하기</button>
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

    const pricePerTicket = 15000;

    ticketInput.addEventListener("input", function() {
        const count = parseInt(ticketInput.value) || 1;
        const total = count * pricePerTicket;
        priceDisplay.textContent = total.toLocaleString() + " 원";
    });
</script>
</body>

