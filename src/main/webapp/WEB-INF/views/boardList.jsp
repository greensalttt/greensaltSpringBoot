<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setTimeZone value="Asia/Seoul" />
<%@ page session="true"%>
<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">

    <style>
        a {
            text-decoration: none;
            color: black;
        }
        button,
        input {
            border: none;
            outline: none;
        }

        body {
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        .board-container {
            width: 80%;
            text-align:center;
            margin: 120px auto 0 auto;
            flex: 1;
        }

        table {
            width: 100%;
            border-top: 2px solid rgb(39, 39, 39);
        }

        tr:nth-child(even) {
            background-color: #f0f0f070;
        }

        th,
        td {
            width:400px;
            text-align: center;
            padding: 10px 12px;
            border-bottom: 1px solid #ddd;
        }

        td {
            color: rgb(53, 53, 53);
        }

        .no      {
            width:150px;
        }

        .title   {
            width:50%;
        }

        .regdt {
            width: 450px;
        }

        .writer {
            width: 500px;
        }

        .viewcnt{
            width: 300px;
        }


        td.title   { text-align: left;  }

        td.title:hover {
            text-decoration: underline;
        }


        #writeBtn {
            background-color: rgb(236, 236, 236);
            border: none;
            color: black;
            padding: 6px 12px;
            font-size: 15px;
            cursor: pointer;
            border-radius: 5px;
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


        #noBoard{
            margin-top: 150px;
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

        .board-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .community-title {
            font-weight: bold;
            color: #333;
        }




    </style>
</head>
<body>
<header id="top">
    <jsp:include page="header.jsp"/>
</header><br><br>

<script>
    let msg = "${msg}";
    if(msg=="DEL_OK")    alert("성공적으로 삭제되었습니다.");
    if(msg=="WRT_OK")    alert("성공적으로 등록되었습니다.");
</script>

    <div class="board-container">
    <div class="board-header">
        <div class="community-title">Open Forum</div>

        <div class="search-container">
            <form action="<c:url value='/board/list' />" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="title" ${ph.sc.option=='title' ? "selected" : ""}>제목만</option>
                    <option value="content" ${ph.sc.option=='content' ? "selected" : ""}>내용만</option>
                    <option value="writer" ${ph.sc.option=='writer' ? "selected" : ""}>작성자</option>
                    <option value="both" ${ph.sc.option=='both' ? "selected" : ""}>제목+내용</option>
                </select>

                <div class="search-container2">
                    <input type="text" name="keyword" class="search-input" placeholder="검색어를 입력해주세요">
                    <img src="/icon_img/searchIcon.png" class="search-icon" onclick="this.closest('form').submit()" alt="검색">
                </div>
            </form>

            <button id="writeBtn" onclick="location.href='<c:url value='/board/write'/>'">글쓰기</button>
        </div>
    </div>

        <table>
            <tr>
                <th class="no">번호</th>
                <th class="title">제목</th>
                <th class="writer">닉네임</th>
                <th class="regdt">등록일</th>
                <th class="viewcnt">조회수</th>
            </tr>
            <c:forEach var="boardDto" items="${list}">
                <tr>
                    <td class="no">${boardDto.bno}</td>
                    <td class="title"><a href="<c:url value="/board/read${ph.sc.queryString}&bno=${boardDto.bno}"/>"><c:out value="${boardDto.title}"/></a></td>
                    <td class="writer">${boardDto.writer}</td>

<%--                JSTL로 반복문,조건문,URL,날짜 형식화 등을 할 수 있다--%>
                    <c:choose>
                        <c:when test="${boardDto.createdAt.time >= startOfToday}">
                            <td class="regdt"><fmt:formatDate value="${boardDto.createdAt}" pattern="HH:mm" type="time"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="regdt"><fmt:formatDate value="${boardDto.createdAt}" pattern="yyyy-MM-dd" type="date"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="viewcnt">${boardDto.viewCnt}</td>
                </tr>
            </c:forEach>
        </table>
    </div><br>

        <div class="paging-container">
                <c:if test="${totalCnt==null || totalCnt==0}">
                    <div id="noBoard"> 게시물이 없습니다. </div>
                </c:if>
                <c:if test="${totalCnt!=null && totalCnt!=0}">
                    <div id="okBoard">
                    <c:if test="${ph.showPrev}">
                        <a class="page" href="<c:url value="/board/list${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                        <a class="page ${i==ph.sc.page? "paging-active" : ""}" href="<c:url value="/board/list${ph.sc.getQueryString(i)}"/>">${i}</a>
                    </c:forEach>
                    <c:if test="${ph.showNext}">
                        <a class="page" href="<c:url value="/board/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                    </c:if>
                    </div>
                </c:if>
        </div><br>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>