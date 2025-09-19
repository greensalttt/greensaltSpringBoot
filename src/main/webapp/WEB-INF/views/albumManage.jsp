<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>


<%--<head>--%>
<%--    <title>albumManage</title>--%>

<%--    <style>--%>

<%--        * {--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--            color: black;--%>
<%--            box-sizing: border-box;--%>
<%--            text-decoration: none;--%>
<%--        }--%>

<%--        body {--%>
<%--            background-color: whitesmoke;--%>
<%--            margin: 0 auto;--%>
<%--            max-width: 1130px;--%>
<%--            height: 160px;--%>
<%--            position: relative;--%>
<%--            overflow-x: hidden;--%>
<%--        }--%>


<%--        #wrapper {--%>
<%--            min-height: 100vh;--%>
<%--            display: flex;--%>
<%--            flex-direction: column;--%>
<%--        }--%>

<%--        #albumListContainer {--%>
<%--            padding: 20px;--%>
<%--            flex: 1;--%>
<%--            width: 80%;--%>
<%--            margin: 100px auto 100px auto;--%>
<%--        }--%>

<%--        #albumListContainer .domestic {--%>
<%--            display: flex;--%>
<%--            align-items: center;--%>
<%--            border-bottom: 2px solid #333;--%>
<%--            padding-bottom: 10px;--%>
<%--            margin-bottom: 20px;--%>
<%--        }--%>

<%--        #albumListContainer .domestic .cate a {--%>
<%--            color: #333;--%>
<%--            text-decoration: none;--%>
<%--            font-weight: bold;--%>
<%--            margin: 0 5px;--%>
<%--        }--%>

<%--        #albumListContainer .list li {--%>
<%--            background-color: white;--%>
<%--            border-radius: 8px;--%>
<%--            display: flex;--%>
<%--            margin-bottom: 10px;--%>
<%--            overflow: hidden;--%>
<%--            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);--%>
<%--            transition: transform 0.2s ease;--%>
<%--        }--%>

<%--        #albumListContainer .list img{--%>
<%--            width: 100px;--%>
<%--            height: 100px;--%>
<%--            object-fit: cover;--%>
<%--            flex-shrink: 0;--%>
<%--        }--%>


<%--        #albumListContainer .list .title {--%>
<%--            font-size: 16px;--%>
<%--            font-weight: bold;--%>
<%--        }--%>

<%--        .album-item {--%>
<%--            display: flex;--%>
<%--            align-items: center;--%>
<%--            background: #fff;--%>
<%--            border-radius: 8px;--%>
<%--            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);--%>
<%--        }--%>

<%--        .album-item a{--%>
<%--            display: flex;--%>
<%--            align-items: center;--%>
<%--        }--%>

<%--        .album-item .info p {--%>
<%--            margin: 4px 0;--%>
<%--        }--%>
<%--        .info{--%>
<%--            margin-left: 20px;--%>
<%--        }--%>

<%--        .artist{--%>
<%--            font-size: 14px;--%>
<%--        }--%>

<%--        .date{--%>
<%--            font-size: 12px;--%>
<%--        }--%>

<%--        button{--%>
<%--            margin-left: auto;--%>
<%--            padding: 0 10px;"--%>
<%--        }--%>

<%--        #noAlbum{--%>
<%--            margin-top: 100px;--%>
<%--            margin-bottom: 120px;--%>
<%--        }--%>


<%--    </style>--%>

<%--</head>--%>
<%--<body>--%>
<%--<div id="wrapper">--%>
<%--    <div id="albumListContainer">--%>
<%--        <div class="domestic">--%>
<%--     <span class="cate">--%>
<%--			<a href="/admin/page">관리자 메인 페이지</a>--%>
<%--		</span>--%>
<%--        </div>--%>
<%--        <ul class="list">--%>
<%--            <c:if test="${empty albumDtos}">--%>
<%--                <div id="noAlbum"> 앨범이 없습니다. </div>--%>
<%--            </c:if>--%>
<%--            <c:forEach var="albumDto" items="${albumDtos}">--%>
<%--                <li class="album-item">--%>
<%--                    <a>--%>
<%--                        <img src="${albumDto.img}" class="img" alt="">--%>
<%--                        <div class="info">--%>
<%--                            <p class="title">${albumDto.title}</p>--%>
<%--                            <p class="artist">${albumDto.artist}</p>--%>
<%--                            <p class="date">Released: ${albumDto.released}</p>--%>
<%--                        </div>--%>
<%--                    </a>--%>
<%--                    <button onclick="location.href='/admin/album_edit?ano=${albumDto.ano}'">수정</button>--%>
<%--                    <form id="removeForm" action="/admin/album_remove" method="post">--%>
<%--                    <input type="hidden" name="ano" value="${albumDto.ano}" />--%>
<%--                    <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>--%>
<%--                    </form>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</div>--%>

<%--</body>--%>

<%--<script>--%>

<%--    let testAid = "${testAid}";--%>
<%--    if (testAid === "msg") {--%>
<%--        alert("테스트 아이디는 삭제할 수 없습니다.");--%>
<%--    }--%>

<%--    let removeFail = "${removeFail}"--%>
<%--    if(removeFail==="msg") {--%>
<%--        alert("앨범 삭제가 실패했습니다.")--%>
<%--    }--%>

<%--    let remove = "${remove}"--%>
<%--    if(remove==="msg") {--%>
<%--        alert("앨범 삭제가 완료되었습니다.")--%>
<%--    }--%>

<%--    let modify = "${modify}"--%>
<%--    if(modify==="msg") {--%>
<%--        alert("앨범 수정에 성공했습니다.")--%>
<%--    }--%>

<%--</script>--%>

