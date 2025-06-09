
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>

    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/myPageHeader.css"/>">

    <title>boardManage</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            color: black;
            box-sizing: border-box;
            text-decoration: none;
        }

        body {
            background-color: whitesmoke;
            margin: 0 auto;
            max-width: 1130px;
            position: relative;
            overflow-x: hidden;
        }

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #boardListContainer {
            width: 80%;
            margin: 0 auto;
            flex: 1;
        }

        .domestic {
            font-size: 20px;
            margin-bottom: 40px;
            margin-top: 40px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .no { width: 8%; }
        .title { width: 35%; text-align: left; }
        .writer { width: 15%; }
        .regdt { width: 15%; }
        .viewcnt { width: 10%; }

        #noboard {
            margin-top: 100px;
            margin-bottom: 120px;
            text-align: center;
            font-size: 18px;
        }

        a {
            color: #333;
        }

        #top{
            margin-bottom: 150px;
        }

        th{
            font-size: 14px;
        }

        td{
            font-size: 13px;
        }

    </style>
</head>

<body>

<header id="top">
    <jsp:include page="header.jsp"/></header><br><br>
<jsp:include page="myPageHeader.jsp"/>


<div id="wrapper">
    <div id="boardListContainer">
    <h1 class="domestic">작성글</h1>

        <c:if test="${empty boardDtos}">
            <div id="noboard">게시글이 없습니다.</div>
        </c:if>

        <c:if test="${not empty boardDtos}">
            <table>
                <thead>
                <tr>
                    <th class="no">번호</th>
                    <th class="title">제목</th>
                    <th class="writer">닉네임</th>
                    <th class="regdt">등록일</th>
                    <th class="viewcnt">조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="boardDto" items="${boardDtos}">
                    <tr>
                        <td class="no">
                            <a href="/board/read?bno=${boardDto.bno}">
                            <c:out value="${boardDto.bno}"/>
                        </a>
                        </td>
                        <td class="title">${boardDto.title}</td>
                        <td class="writer">${boardDto.writer}</td>
                        <td class="regdt">
                            <fmt:formatDate value="${boardDto.createdAt}" pattern="yyyy-MM-dd" type="date"/>
                        </td>
                        <td class="viewcnt">${boardDto.viewCnt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

    <footer>
        <jsp:include page="footer.jsp" flush="false" />
    </footer>

</div>

<%--<footer>--%>
<%--    <jsp:include page="footer.jsp" flush="false" />--%>
<%--</footer>--%>

</body>

