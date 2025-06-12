<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <title>performanceList</title>

    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }


        #performanceListContainer {
            width: 80%;
            margin: 120px auto 0 auto;
            flex: 1;
        }


        .performance-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #333;

        }


        .domestic{
            font-weight: bold;
            color: #333;
        }

        .list {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-top: 30px;
        }

        .performance-card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.2s ease;
            display: flex;
            flex-direction: column;
            text-align: left;
        }

        .performance-card img {
            width: 100%;
            height: 300px;
        }

        .performance-card .info {
            padding: 10px;
        }

        .artist {
            font-size: 16px;
            font-weight: bold;
        }

        .title{
            color: grey;
            font-size: 14px;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .date .venue {
            font-size: 12px;
            color: #666;
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


        #noPerformance{
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


        .date, .venue {
            font-size: 13px;
        }
        .title {
            color: grey;
            font-size: 14px;
            margin-top: 5px;
            margin-bottom: 5px;
        }



    </style>

</head>
<body>
<div id="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header><br><br>

    <div id="performanceListContainer">
        <div class="performance-header">

            <div class="domestic">Performance</div>

            <div class="search-container">
                <form action="<c:url value='/performance/list' />" class="search-form" method="get">
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
        <div class="list">
            <c:forEach var="performanceDto" items="${list}">
                <div class="performance-card">
                    <a href="/performance/read?pno=${performanceDto.pno}">
                        <img src="${performanceDto.img}" class="img" alt="포스터 이미지">
                        <div class="info">
                            <p class="artist">${performanceDto.artist}</p>
                            <p class="title">${performanceDto.title}</p>
                            <p class="date">${performanceDto.date}</p>
                            <p class="venue">${performanceDto.venue}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div><br><br>


    <div class="paging-container">
        <c:if test="${totalCnt==null || totalCnt==0}">
            <div id="noPerformance"> 등록된 공연이 없습니다. </div>
        </c:if>
        <c:if test="${totalCnt!=null && totalCnt!=0}">
            <div id="okPerformance">
                <c:if test="${ph.showPrev}">
                    <a class="page" href="<c:url value="/performance/list${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                </c:if>
                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <a class="page ${i==ph.sc.page? "paging-active" : ""}" href="<c:url value="/performance/list${ph.sc.getQueryString(i)}"/>">${i}</a>
                </c:forEach>
                <c:if test="${ph.showNext}">
                    <a class="page" href="<c:url value="/performance/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                </c:if>
            </div>
        </c:if>
    </div><br>


    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>

</body>
