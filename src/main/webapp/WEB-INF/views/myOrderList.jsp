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

        .status-pending { background-color: #ffefc3; color: #b88d00; }
        .status-paid { background-color: #d3f4d1; color: #2a7a2e; }
        .status-expired { background-color: #eee; color: #777; }

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
        <c:forEach var="dto" items="${myOrders}">
            <div class="order-card">
                <div class="order-header">주문번호: ${dto.orderId}</div>
                <div class="order-detail">주문자명: ${dto.ordererName}</div>
                <div class="order-detail">티켓 수량: ${dto.ticketCount}매</div>
                <div class="order-detail">총 결제 금액: <fmt:formatNumber value="${dto.totalPrice}" type="number"/>원</div>
                <div class="order-detail">주문일: <fmt:formatDate value="${dto.createdAtAsDate}" pattern="yyyy-MM-dd HH:mm"/></div>
                <div class="order-detail">
                    상태:
                    <span class="order-status status-${dto.status}">
                        <c:choose>
<%--                            <c:when test="${dto.status == 'pending'}">대기중</c:when>--%>

                            <c:when test="${dto.status == 'pending'}">
                                <a href="/payment?orderId=${dto.orderId}" class="order-status status-pending">대기중</a>
                            </c:when>
                            <c:when test="${dto.status == 'paid'}">결제완료</c:when>
                            <c:when test="${dto.status == 'expired'}">만료됨</c:when>
                            <c:otherwise>${dto.status}</c:otherwise>
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
