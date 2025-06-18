<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>댓글 이력 보기</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 24px;
            margin-bottom: 20px;
            border-left: 5px solid darkgreen;
            padding-left: 15px;
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #2c3e50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 16px;
            background-color: darkgreen;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>댓글 이력 보기</h2>

    <table>
        <thead>
        <tr>
            <th>이력 번호</th>
            <th>댓글 번호</th>
            <th>게시글 번호</th>
            <th>작성자 ID</th>
            <th>변경 코드</th>
            <th>변경 전</th>
            <th>변경 후</th>
            <th>변경 일시</th>
            <th>수정자 ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hist" items="${commentHistList}">
            <tr>
                <td>${hist.coHistNum}</td>
                <td>${hist.cno}</td>
                <td>${hist.bno}</td>
                <td>${hist.CId}</td>
                <td>${hist.coCngCd}</td>
                <td>${hist.coBf}</td>
                <td>${hist.coAf}</td>
                <td><fmt:formatDate value="${hist.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${hist.createdBy}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/admin/page" class="back-button">← 관리자 대시보드로</a>
</div>
</body>
</html>
