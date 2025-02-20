<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <title>My Page</title>

    <style>
        #myPage {
            margin: auto;
        }

        .none {
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
            text-align: center;
            color: dimgray;
            font-weight: bold;
        }
        .sp {
            /*font-size: 16px;*/
            margin: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .one1 {
            margin-top: -15px;
            display: flex;
            justify-content: center;
        }

        .one2 {
            display: flex;
            justify-content: center;
            margin-top: -15px
        }

        .ma2 {
            font-size: 12px;
            color: black;
        }

        .date {
            font-size: 14px;
            margin-bottom: 10px;
            color: gray;
        }


        #title1 {
            text-align: center;
            font-weight: bold;
        }

        #title2{
            cursor: pointer;
            text-decoration: none;
            /*color: black;*/
            color: dimgray;
            font-size: 14px;
        }
    </style>
</head>

<body>
<div id="myPage">
    <p id="title1"><a id="title2" href="/mypage/list"><%=session.getAttribute("cName")%>님의 MY PAGE</a></p>
    <div class="one1">
        <div class="sp">
            <span class="date">닉네임</span>
            <span class="ma2">
                    <%=session.getAttribute("cNick")%>
                </span>
        </div>
        <div class="sp">
            <span class="date">등급</span>
            <span class="ma2">
<%--                    <%=session.getAttribute("grdName")%>--%>
                Bronze
                </span>
        </div>
        <div class="sp">
            <span class="date">포인트</span>
            <span class="ma2">
<%--                    <%=session.getAttribute("tot_amt")%>p--%>
                0원
                </span>
        </div>
        <div class="sp">
            <span class="date">방문</span>
            <span class="ma2">
                <%=session.getAttribute("visitCnt")%>회
<%--                0회--%>
            </span>
        </div>
        <div class="sp">
            <span class="date">가입일</span>
            <span class="ma2"><%=session.getAttribute("regDt")%></span>
        </div>
    </div>

    <div class="one2">
        <div class="sp">
            <span><a class="none" href="/orderDetail">작성글</a></span>
        </div>
        <div class="sp">
            <span><a class="none" onclick="test()">작성 댓글</a></span>
        </div>
        <div class="sp">
            <span><a class="none" onclick="test()">좋아요</a></span>
        </div>
<%--        <div class="sp">--%>
<%--            <span><a class="none" onclick="test()">배송지 목록</a></span>--%>
<%--        </div>--%>
        <div class="sp">
            <span><a class="none" href="/mypage/info">개인정보 수정</a></span>
        </div>
        <div class="sp">
            <span><a class="none" href="/mypage/editPwd">비밀번호 수정</a></span>
        </div>
    </div>
</div>

<script>


    function test(){
        alert("테스트중입니다!")
    }
</script>

</body>

