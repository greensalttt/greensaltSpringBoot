<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setTimeZone value="Asia/Seoul" />


<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">

    <style>

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .notice-container {
            width: 80%;
            text-align:center;
            margin: 120px auto 0 auto;
            flex: 1;
        }

        a {
            text-decoration: none;
            color: black;
        }
        button,
        input {
            border: none;
            outline: none;
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


        .community-title {
            font-weight: bold;
            color: #333;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 80px;

        }

        #nonotice {
            margin-top: 120px;
            margin-bottom: 120px;
            text-align: center;
            font-size: 18px;
        }

    </style>
</head>
<body>
<div id="wrapper">
<header id="top">
    <jsp:include page="header.jsp"/>
</header><br><br>

<script>
    let msg = "${msg}";
    if(msg=="DEL_OK")    alert("성공적으로 삭제되었습니다.");
    if(msg=="WRT_OK")    alert("성공적으로 등록되었습니다.");
</script>

<div class="notice-container">
        <div class="community-title">Notice</div>



    <table>
        <tr>
            <th class="no">번호</th>
            <th class="title">제목</th>
            <th class="writer">작성자</th>
            <th class="regdt">등록일</th>
            <th class="viewcnt">조회수</th>
        </tr>
        <c:forEach var="noticeDto" items="${noticeDtos}">
            <tr>
                <td class="no">${noticeDto.nno}</td>
                <td class="title"><a href="<c:url value="/notice/read?nno=${noticeDto.nno}"/>"><c:out value="${noticeDto.title}"/></a></td>
                <td class="writer">${noticeDto.writer}</td>

                    <%--                JSTL로 반복문,조건문,URL,날짜 형식화 등을 할 수 있다--%>
                <c:choose>
                    <c:when test="${noticeDto.createdAt.time >= startOfToday}">
                        <td class="regdt"><fmt:formatDate value="${noticeDto.createdAt}" pattern="HH:mm" type="time"/></td>
                    </c:when>
                    <c:otherwise>
                        <td class="regdt"><fmt:formatDate value="${noticeDto.createdAt}" pattern="yyyy-MM-dd" type="date"/></td>
                    </c:otherwise>
                </c:choose>
                <td class="viewcnt">${noticeDto.viewCnt}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${empty noticeDtos}">
        <div id="nonotice">공지사항이 없습니다.</div>
    </c:if>
</div>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</div>
</body>