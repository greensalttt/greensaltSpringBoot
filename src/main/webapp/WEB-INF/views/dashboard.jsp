<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 대시보드</title>
    <style>
        body {
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        .container {
            position: relative;
            max-width: 900px;
            margin: 40px auto;
            background-color: #fff;
            padding: 40px 60px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h1 {
            font-size: 22px;
            margin: 15px 0;
            color: #333;
        }

        #write, #delete {
            display: inline-block;
            margin-top: 20px;
            background-color: darkgreen;
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-weight: bold;
        }


        button#adminlogoutLink {
            padding: 10px 20px;
            background-color: darkgreen;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        .title {
            font-size: 26px;
            margin-bottom: 30px;
            color: #2c3e50;
        }

        .dashboard-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="dashboard-header">
        <div class="title">관리자 대시보드</div>

        <div id="logout-wrapper">
            <form action="<c:url value='/admin/logout'/>" method="POST">
                <button type="submit" id="adminlogoutLink">로그아웃</button>
            </form>
        </div>
    </div>



    <h1>총 회원 수: <span>${custCount}</span></h1>
    <h1>총 게시글 수: <span>${boardCount}</span></h1>
    <h1>총 댓글 수: <span>${commentCount}</span></h1>
    <h1>총 앨범 수: <span>${albumCount}</span></h1>

    <a id="write" href="/admin/album">앨범 글 올리기</a>
    <a id="delete" href="/admin/manage">앨범 관리</a>


</div>

<script>
    window.onload = function () {
        if ("${sessionScope.aId}" !== "") {
            document.getElementById('adminlogoutLink').onclick = function () {
                if (confirm('관리자 페이지를 나가시겠습니까?')) {
                    alert('로그아웃이 되어 메인페이지로 이동합니다.');
                }
            };
        }

        let adminWrite = "${adminWrite}";
        if(adminWrite === "msg") {
            alert("앨범 등록에 성공했습니다.");
        }
    };
</script>
</body>
</html>

