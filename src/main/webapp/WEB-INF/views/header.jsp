<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">


<script>
    window.onload = function () {
        if ("${sessionScope.cId}" !== "") {
            document.getElementById('logoutLink').onclick = function () {
                if (confirm('정말로 로그아웃을 하시겠습니까?')) {
                    alert('로그아웃이 되어 메인페이지로 이동합니다.');
                }
            };
        }
    };
</script>


    <div id="headerTitle">
        <h1><a href="<c:url value='/'/>">Green Salt !</a></h1>
   </div>


<div id="custIcon">
    <img id="custIconImg" src="https://cdn-icons-png.flaticon.com/128/3462/3462172.png" alt="User Icon">
    <div id="links">
        <c:if test="${sessionScope.cId != null}">
            <form action="<c:url value='/logout'/>" method="POST">
                <button type="submit" id="logoutLink">Logout</button>
            </form>
        </c:if>
        <c:if test="${sessionScope.cId == null}">
            <a href="<c:url value='/login'/>">Login</a>
        </c:if>
        <a href="/mypage/list">My Page</a>
    </div>
</div>


<%--    <input id="search1" type="text" placeholder="Search.." onkeydown="handleKeyPress(event)">--%>

    <nav id="headerNav">
        <ul>
            <li><a href="<c:url value='/album/list'/>">Album</a></li>
            <li><a href="#">Video</a></li>
            <li><a href="#">Interview</a></li>
            <li><a href="#">Performance</a></li>
            <li><a href="<c:url value='/board/list'/>">Community</a></li>
            <li><a href="#">Help</a></li>
        </ul>
    </nav>

<script>


    document.getElementById('custIconImg').addEventListener('click', function (event) {
        var links = document.getElementById('links');
        links.classList.toggle('show');
        event.stopPropagation(); // 이벤트 전파 중지
    });

    document.addEventListener('click', function (event) {
        var links = document.getElementById('links');
        var isClickInside = document.getElementById('custIcon').contains(event.target);

        if (!isClickInside) {
            links.classList.remove('show');
        }
    });

</script>