<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>공연 상세 페이지</title>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">

    <style>
        #performancePageContainer {
            margin: 200px auto 0 auto;
            width: 70%;
            display: flex;
            justify-content: center;
        }


        .performance-image img {
            width: 320px;
            height: 460px;
            object-fit: cover;
            border-radius: 10px;
            border: 1px solid #ddd;
        }

        .performance-info {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 460px;
            /*font-size: 16px;*/
            margin-left: 200px;
        }

        .subTitle{
            font-size: 20px;
            margin-bottom: 50px;
        }

        .sub p{
            margin-bottom: 5px;
            font-size: 16px;
            color: #444;
        }

        .btn-book {
            display: inline-block;
            margin-top: 30px;
            padding: 14px 0;
            text-align: center;
            background-color: #0056b3;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        .performance-content {
            width: 70%;
            margin: 40px auto 100px auto;
        }

        .performance-content h3 {
            font-size: 20px;
            font-weight: 600;
            margin-bottom: 20px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 8px;
            color: #222;
        }

        .performance-content p {
            font-size: 15px;
            line-height: 1.8;
            color: #444;
        }
    </style>
</head>

<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<div id="performancePageContainer">
        <div class="performance-image">
            <img src="${performanceDto.img}" alt="공연 이미지">
        </div>

    <div class="performance-info">
        <div class="info-texts">
            <div class="subTitle">
            <p>${performanceDto.title}</p>
            <p>${performanceDto.artist}</p>
            </div>
            <div class="sub">
            <p>공연 시간: ${performanceDto.duration}</p>
            <p>관람 연령: ${performanceDto.rating}</p>
            <p>장소: ${performanceDto.venue}</p>
            <p>장르: ${performanceDto.genre}</p>
            <p>날짜: ${performanceDto.date}</p>
            <p>가격: 70000원</p>
            </div>
        </div>
        <a href="#" class="btn-book">예매하기</a>
    </div>

</div>

<div class="performance-content">
    <h3>공연 소개</h3>
    <p>${performanceDto.content}</p>
</div>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>

