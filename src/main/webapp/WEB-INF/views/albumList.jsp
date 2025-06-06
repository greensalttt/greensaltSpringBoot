<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <title>albumList</title>

    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }


        #albumListContainer {
            width: 80%;
            margin: 120px auto 0 auto;
            flex: 1;
        }


        .album-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #333;

        }


        .miniHeader{
            font-weight: bold;
            color: #333;
        }

        #albumListContainer .list li {
            background-color: white;
            border-radius: 8px;
            display: flex;
            margin-bottom: 10px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s ease;
        }

        #albumListContainer .list img{
            width: 80px;
            height: 80px;
            object-fit: cover;
            flex-shrink: 0;
        }

        .album-item {
            display: flex;
            align-items: center;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
        }

        .album-item a{
            display: flex;
            align-items: center;
        }

        .album-item .info p {
            margin: 4px 0;
        }
        .info{
            margin-left: 20px;
        }

        .artist{
            font-size: 16px;
            font-weight: bold;
        }

        .title{
            font-size: 12px;
        }

        .date{
            font-size: 12px;
        }


        .paging-container {
            width:100%;
            display: flex;
            margin : auto;
            color: black;
            align-items: center;
            justify-content: center;
        }

        .page {
            color: black;
            margin-right: 10px;
        }


        #noAlbum{
            margin-top: 100px;
            margin-bottom: 120px;
        }


        .search-container {
            height: 80px;
            display: flex;
            align-items: center;
        }
        .search-form {
            height: 32px;
            display: flex;
        }
        .search-option {
            width: 70px;
            height: 100%;
            outline: none;
            margin-right: 5px;
            border: 1px solid #ccc;
            color: gray;
        }

        .search-option > option {
            text-align: center;
        }

        .search-input::placeholder {
            color: gray;
        }

        .search-container2 {
            position: relative;
            width: 200px;
        }

        .search-input {
            width: 200px;
            height: 100%;
            padding: 5px 7px;
            padding-right: 40px;
            box-sizing: border-box;
            font-size: 14px;
            color: gray;
            background-color: white;
            border: 1px solid #ccc;
        }


        .search-icon {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            width: 20px;
            height: 20px;
            cursor: pointer;
        }


    </style>

</head>
<body>
<div id="wrapper">
<header>
    <jsp:include page="header.jsp"/>
</header><br><br>

    <div id="albumListContainer">
        <div class="album-header">

                <div class="miniHeader">Album</div>

        <div class="search-container">
            <form action="<c:url value='/album/list' />" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="title" ${ph.sc.option=='title' ? "selected" : ""}>제목</option>
                    <option value="content" ${ph.sc.option=='artist' ? "selected" : ""}>아티스트</option>
                </select>

                <div class="search-container2">
                    <input type="text" name="keyword" class="search-input" placeholder="검색어를 입력해주세요">
                    <img src="/icon_img/searchIcon.png" class="search-icon" onclick="this.closest('form').submit()" alt="검색">
                </div>
            </form>
            </div>
        </div>

        <ul class="list">
            <c:forEach var="albumDto" items="${list}">
                <li class="album-item">
                    <a href="/album/read?ano=${albumDto.ano}">
                    <img src="${albumDto.img}" class="img" alt="">
                        <div class="info">
                            <p class="title">${albumDto.title}</p>
                            <p class="artist">${albumDto.artist}</p>
                            <p class="date">Released: ${albumDto.released}</p>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
        </div><br>

    <div class="paging-container">
        <c:if test="${totalCnt==null || totalCnt==0}">
            <div id="noAlbum"> 앨범이 없습니다. </div>
        </c:if>
        <c:if test="${totalCnt!=null && totalCnt!=0}">
            <div id="okAlbum">
                <c:if test="${ph.showPrev}">
                    <a class="page" href="<c:url value="/album/list${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                </c:if>
                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <a class="page ${i==ph.sc.page? "paging-active" : ""}" href="<c:url value="/album/list${ph.sc.getQueryString(i)}"/>">${i}</a>
                </c:forEach>
                <c:if test="${ph.showNext}">
                    <a class="page" href="<c:url value="/album/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                </c:if>
            </div>
        </c:if>
    </div><br>



    <footer>
<jsp:include page="footer.jsp"/>
</footer>
    </div>

</body>
