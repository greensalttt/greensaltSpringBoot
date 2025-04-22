<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>관리자 사이트입니다</p>

<p>현재 회원가입 수</p>
<p>현재 게시글 수</p>
<p>현재 댓글 수</p>

<form action="<c:url value='/adminlogout'/>" method="POST">
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
</script>
</body>
