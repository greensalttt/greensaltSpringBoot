<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/myPageHeader.css'/>">

    <title>예매 확인</title>
    <style>
        /*body {*/
        /*    background-color: whitesmoke;*/
        /*    margin: 0 auto;*/
        /*    max-width: 1130px;*/
        /*    position: relative;*/
        /*    overflow-x: hidden;*/
        /*    font-family: 'Segoe UI', sans-serif;*/
        /*}*/

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #reservationContainer {
            width: 90%;
            margin: 0 auto;
            flex: 1;
        }

        .title-section {
            font-size: 22px;
            margin: 40px 0 20px 0;
            font-weight: bold;
        }

        .reservation-card {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 15px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
        }

        .card-info {
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

        .card-info div {
            font-size: 14px;
            color: #333;
        }

        .card-info{
            font-weight: bold;
            color: #222;
            font-size: 16px;
        }

        .detail-button {
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            background-color: #4a77f5;
            color: white;
            cursor: pointer;
            transition: background 0.3s;
            font-size: 14px;
        }

        .detail-button:hover {
            background-color: #365edc;
        }

        #noReservation {
            margin: 100px 0;
            text-align: center;
            font-size: 18px;
            color: #888;
        }

        #top {
            margin-bottom: 150px;
        }

        /* 모달 */
        #reservationModal {
            display: none;
            position: fixed;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            z-index: 1000;
            align-items: center;
            justify-content: center;
        }

        #reservationModal .modal-content {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            width: 500px;
            position: relative;
        }

        #reservationModal table {
            width: 100%;
            border-collapse: collapse;
        }

        #reservationModal th, #reservationModal td {
            text-align: left;
            padding: 8px 5px;
        }

        #modalClose {
            position: absolute;
            top: 10px;
            right: 15px;
            cursor: pointer;
            font-weight: bold;
            font-size: 20px;
        }
    </style>
</head>

<body>

<header id="top">
    <jsp:include page="header.jsp"/>
</header><br><br>

<jsp:include page="myPageHeader.jsp"/>

<div id="wrapper">
    <div id="reservationContainer">

        <div class="title-section">예매 확인</div>

        <c:if test="${empty reservationDtos}">
            <div id="noReservation">예매 내역이 없습니다.</div>
        </c:if>

        <c:if test="${not empty reservationDtos}">
<%--            <div style="margin-bottom: 10px; font-weight: bold; color: #555;">--%>
<%--                ※ 결제가 완료된 내역만 보여집니다.--%>
<%--            </div>--%>

            <c:forEach var="dto" items="${reservationDtos}">
                <div class="reservation-card">
                    <div class="card-info">
                        <div><a href="/performance/read?pno=${dto.pno}">${dto.title}</a></div>
                        <div>${dto.artist}</div>
                        <div>${dto.venue}</div>
                        <div>${dto.date}</div>
                        <div>수량: ${dto.ticketCount}매</div>
                        <div>결제 금액: <fmt:formatNumber value="${dto.totalPrice}" type="number"/>원</div>
                    </div>
                    <div>
                        <button class="detail-button open-modal"
                                data-title="${dto.title}"
                                data-orderid="${dto.orderId}"
                                data-name="${dto.ordererName}"
                                data-date="<fmt:formatDate value='${dto.createdAt}' pattern='yyyy-MM-dd HH:mm:ss'/>"
                                data-count="${dto.ticketCount}"
                                data-amount="<fmt:formatNumber value='${dto.totalPrice}' type='number'/>"
                                data-method="${dto.paymentMethod}">
                            상세 보기
                        </button>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <footer>
        <jsp:include page="footer.jsp" flush="false" />
    </footer>
</div>

<!-- 모달 -->
<div id="reservationModal">
    <div class="modal-content">
        <span id="modalClose">×</span>
        <h2>예매 상세 정보</h2><br>
        <table>
            <tr><th>공연 제목</th><td id="m_title"></td></tr>
            <tr><th>예매 번호</th><td id="m_orderId"></td></tr>
            <tr><th>예매자명</th><td id="m_name"></td></tr>
            <tr><th>예매일</th><td id="m_date"></td></tr>
            <tr><th>수량</th><td id="m_count"></td></tr>
            <tr><th>최종 결제 금액</th><td id="m_amount"></td></tr>
            <tr><th>결제 방법</th><td id="m_method"></td></tr>
        </table>
    </div>
</div>

<!-- 모달 스크립트 -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const modal = document.getElementById("reservationModal");
        const closeBtn = document.getElementById("modalClose");

        document.querySelectorAll(".open-modal").forEach(el => {
            el.addEventListener("click", function () {
                document.getElementById("m_orderId").innerText = this.dataset.orderid;
                document.getElementById("m_title").innerText = this.dataset.title;
                document.getElementById("m_name").innerText = this.dataset.name;
                document.getElementById("m_date").innerText = this.dataset.date;
                document.getElementById("m_count").innerText = this.dataset.count + "매";
                document.getElementById("m_amount").innerText = this.dataset.amount + "원";
                document.getElementById("m_method").innerText = this.dataset.method;

                modal.style.display = "flex";
            });
        });

        closeBtn.addEventListener("click", function () {
            modal.style.display = "none";
        });

        window.addEventListener("click", function (e) {
            if (e.target === modal) {
                modal.style.display = "none";
            }
        });
    });
</script>

</body>
