
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>

    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/myPageHeader.css"/>">

    <title>commentManage</title>
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

        #commentListContainer {
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
        .actions { width: 10%; }

        #nocomment {
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
    <div id="commentListContainer">

    <h1 class="domestic">작성 댓글</h1>

        <c:if test="${empty commentDtos}">
            <div id="nocomment">댓글이 없습니다.</div>
        </c:if>

        <c:if test="${not empty commentDtos}">
            <table>
                <thead>
                <tr>
                    <th class="no">게시글 번호</th>
                    <th class="title">댓글 내용</th>
                    <th class="writer">닉네임</th>
                    <th class="regdt">등록일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="commentDto" items="${commentDtos}">
                    <tr>
                        <td class="no">
                            <a href="/board/read?bno=${commentDto.bno}">
                                <c:out value="${commentDto.bno}"/>
                            </a>
                        </td>
                        <td class="title">
                             ${commentDto.comment}
                        </td>
                        <td class="writer">${commentDto.commenter}</td>
                        <td class="regdt">
                            <fmt:formatDate value="${commentDto.createdAt}" pattern="yyyy-MM-dd" type="date"/>
                        </td>
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

