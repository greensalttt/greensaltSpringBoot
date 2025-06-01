<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <title>performancePage</title>

    <style>

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #performancePageContainer {
            margin-top: 200px;
            padding: 20px;
            flex: 1;
            text-align: center;

        }

        .performance-image {
            margin-bottom: 20px;
        }

        .performance-image img {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }

        .performance-info {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-left: 135px;
        }

        .performance-info p {
            display: flex;
            align-items: center;
            font-size: 14px;
            width: 300px;
        }


        .performance-content {
            margin-top: 77px;
            margin-bottom: 100px;
            text-align: left;
        }

        .performance-content h3 {
            font-size: 14px;
            margin-bottom: 10px;
        }

        .performance-content p {
            font-size: 15px;
            line-height: 1.6;
            color: #444;
        }
    </style>

</head>
<body>

<div id="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <div id="performancePageContainer">
        <div class="performance-image">
            <img src="${performanceDto.img}" alt="앨범 이미지">
        </div>

        <div class="performance-info">
            <p><strong>아티스트:</strong>&nbsp;${performanceDto.artist}</p>
            <p><strong>공연제목:</strong>&nbsp;${performanceDto.title}</p>
            <p><strong>공연시간:</strong>&nbsp;${performanceDto.duration}</p>
            <p><strong>관람연령:</strong>&nbsp;${performanceDto.rating}</p>
            <p><strong>장소:</strong>&nbsp;${performanceDto.venue}</p>
            <p><strong>장르:</strong>&nbsp;${performanceDto.genre}</p>
            <p><strong>날짜:</strong>&nbsp;${performanceDto.date}</p>
        </div>

        <div class="performance-content">
            <h3>공연 소개</h3>
            <p>${performanceDto.content}</p>
        </div>



    </div>
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>
