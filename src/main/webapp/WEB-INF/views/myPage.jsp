<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/myPageHeader.css"/>">



    <title>My Page</title>

    <style>
        .myPageH {
            font-size: 17px;
            margin: auto;
            margin-top: 40px;
            margin-bottom: 20px;
            text-align: center;
            color: black;
        }

        .sp {
            font-size: 16px;
            margin: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .oneone {
            display: flex;
            justify-content: center;
            margin: auto;
            box-shadow: 0 2px 8px 0 rgba(0, 0, 0, 0.2);
            width: 600px;
        }

        .ma {
            margin-bottom: 10px;
        }

        .ma2 {
            font-size: 11px;
        }

        #top {
            margin-bottom: 150px;
        }

    </style>

</head>

<body>

<header id="top">
<jsp:include page="header.jsp"/></header><br><br>

<jsp:include page="myPageHeader.jsp"/>

<p class="myPageH">My Activity</p>
        <div class="oneone">
            <div class="sp">
                <span class="ma">작성 게시글</span>
                <span class="ma2">${custDto.boardCount}개</span>
            </div>
            <div class="sp">
                <span class="ma">작성 댓글</span>
                <span class="ma2">${custDto.commentCount}개</span>
            </div>
    </div>
<br><br><br><br>

<footer>
<jsp:include page="footer.jsp" flush="false" />
</footer>

<script>
    let msg = "${msg}";
    if(msg=="CMOD_OK")    alert("개인정보가 수정되었습니다.");

    function test(){
        alert("테스트중입니다")
    }
</script>

</body>

