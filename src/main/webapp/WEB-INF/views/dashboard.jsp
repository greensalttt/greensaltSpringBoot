<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <style>
        #write{
            cursor: pointer;
            color: black;
        }
    </style>
</head>
<body>
<p>관리자 사이트입니다</p>

<h1>총 회원 수: <span>${custCount}</span></h1>
<h1>총 게시글 수: <span>${boardCount}</span></h1>
<h1>총 댓글 수: <span>${commentCount}</span></h1>
<h1>총 앨범 수: <span>${albumCount}</span></h1>

<a id="write" href="/admin/album">앨범 글 올리기</a><br><br>

<form action="<c:url value='/admin/logout'/>" method="POST">
    <button type="submit" id="adminlogoutLink">Logout</button>
</form>

<script>
    window.onload = function () {
        if ("${sessionScope.aId}" !== "") {
            document.getElementById('adminlogoutLink').onclick = function () {
                if (confirm('관리자 페이지를 나가시겠습니까?')) {
                    alert('로그아웃이 되어 메인페이지로 이동합니다.');
                }
            };
        }
    };

    let adminWrite = "${adminWrite}"
    if(adminWrite==="msg") {
        alert("앨범 등록에 성공했습니다.");
    }

</script>
</body>
