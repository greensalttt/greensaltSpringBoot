<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setTimeZone value="Asia/Seoul" />

<head>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">
    <title>공지사항</title>

    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #noticePageContainer {
            margin: 200px auto 150px auto;
            padding: 50px;
            flex: 1;
            width: 70%;
            background-color: #fff;
            border: 1px solid #ddd;
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.06);
            border-radius: 12px;
        }

        .notice-title p {
            font-size: 26px;
            font-weight: 800;
            margin-bottom: 16px;
            color: #111;
            text-align: left;
            padding-bottom: 14px;
            border-bottom: 2px solid #333;
        }

        .notice-meta {
            text-align: right;
            font-size: 13px;
            color: #888;
            margin-bottom: 30px;
        }

        .notice-meta p {
            margin: 0;
        }

        .notice-content {
            text-align: left;
        }

        .notice-content p {
            font-size: 16px;
            line-height: 1.9;
            color: #333;
            white-space: pre-wrap;
            padding: 10px 0;
        }
    </style>
</head>

<body>
<div id="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header><br><br>

    <div id="noticePageContainer">
        <div class="notice-title">
            <p>${noticeDto.title}</p>
        </div>

        <div class="notice-meta">
            <p>
                ${noticeDto.writer} |
                <c:choose>
                    <c:when test="${noticeDto.createdAt.time >= startOfToday}">
                        <fmt:formatDate value="${noticeDto.createdAt}" pattern="HH:mm" type="time"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate value="${noticeDto.createdAt}" pattern="yyyy-MM-dd" type="date"/>
                    </c:otherwise>
                </c:choose>
            </p>
        </div>

        <div class="notice-content">
            <p>${noticeDto.content}</p>
        </div>
    </div>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>
