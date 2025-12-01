<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>나의 주문 내역</title>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/myPageHeader.css'/>">

    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #orderContainer {
            width: 90%;
            margin: 0 auto;
            flex: 1;
        }

        .order-card {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 20px;
            margin: 10px 0;
        }

        .order-header {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .order-detail {
            font-size: 14px;
            color: #444;
            margin: 3px 0;
        }

        .order-status {
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 13px;
            font-weight: bold;
            display: inline-block;
        }


        #noOrder {
            margin: 100px 0;
            text-align: center;
            font-size: 18px;
            color: #888;
        }

        #top {
            margin-bottom: 150px;
        }
    </style>
</head>

<body>
<header id="top">
    <jsp:include page="header.jsp"/>
</header><br><br>

<jsp:include page="myPageHeader.jsp"/>

<div id="wrapper">
    <div id="orderContainer">

        <div class="container">
            <h2 style="margin: 40px 0 20px 0;">나의 주문 내역</h2>

            <c:if test="${empty myOrders}">
                <div id="noOrder">주문 내역이 없습니다.</div>
            </c:if>

            <c:if test="${not empty myOrders}">
                <c:forEach var="orderDto" items="${myOrders}">
                    <div class="order-card">
                        <div class="order-header">주문번호: ${orderDto.orderId}</div>
                        <div class="order-detail">주문자명: ${orderDto.ordererName}</div>
                        <div class="order-detail">티켓 수량: ${orderDto.ticketCount}매</div>
                        <div class="order-detail">총 결제 금액: <fmt:formatNumber value="${orderDto.totalPrice}" type="number"/>원</div>
                        <div class="order-detail">주문일: <fmt:formatDate value="${orderDto.createdAtAsDate}" pattern="yyyy-MM-dd HH:mm"/></div>
                        <div class="order-detail">
                            상태:
                            <span class="order-status status-${orderDto.status}">
                        <c:choose>
                            <c:when test="${orderDto.status == 'pending' or orderDto.status == 'paid'}">

                            <span class="order-status status-${orderDto.status}">
                                <c:choose>
                                    <c:when test="${orderDto.status == 'pending'}">대기중</c:when>
                                    <c:when test="${orderDto.status == 'paid'}">결제완료</c:when>
                                </c:choose>
                            </span>

                            <form action="/order/cancel" method="post" style="display:inline;" onsubmit="return confirmCancel();">
                                <input type="hidden" name="ono" value="${orderDto.ono}">
                                <input type="hidden" name="createdBy" value="${orderDto.createdBy}">
                                <button type="submit" style="padding:2px 6px; font-size:12px; margin-left:5px;">취소</button>
                            </form>

                            </c:when>

                            <c:when test="${orderDto.status == 'expired'}">만료됨</c:when>
                            <c:when test="${orderDto.status == 'canceled'}">취소됨</c:when>
                            <c:otherwise>${orderDto.status}</c:otherwise>
                        </c:choose>
                    </span>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>

<script>
    function confirmCancel() {
        return confirm("정말로 이 주문을 취소하시겠습니까?");
    }
</script>
