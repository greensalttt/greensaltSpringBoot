<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setTimeZone value="Asia/Seoul" />

<html>
<head>
    <title>회원 목록</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        #custlist {
            width: 90%;
            max-width: 1200px;
            margin: 80px auto;
            background-color: white;
            border: 1px solid #ccc;
            padding: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .table-wrapper {
            max-height: 500px;
            overflow-y: auto;
            overflow-x: auto;
            border: 1px solid #ddd;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 1000px;
        }

        thead th {
            position: sticky;
            top: 0;
            background-color: #f2f2f2;
            z-index: 1;
            text-align: left;
        }

        th, td {
            padding: 5px;
            border: 1px solid #ddd;
            white-space: nowrap;
        }

        h2{
            font-size: 15px;
        }

        th{
            font-size: 13px;
        }

        td{
            font-size: 12px;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tbody tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>

<div id="custlist">
    <h2>회원 목록 (상태 D는 탈퇴한 회원입니다.)</h2>
    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>회원번호</th>
                <th>상태</th>
                <th>이메일</th>
                <th>닉네임</th>
                <th>우편번호</th>
                <th>도로명 주소</th>
                <th>지번 주소</th>
                <th>상세 주소</th>
                <th>최종 로그인</th>
                <th>가입일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="custDto" items="${custDtos}">
                <tr>
                    <td>${custDto.CId}</td>
                    <td>${custDto.statCd}</td>
                    <td>${custDto.CEmail}</td>
                    <td>${custDto.CNick}</td>
                    <td>${custDto.CZip}</td>
                    <td>${custDto.CRoadA}</td>
                    <td>${custDto.CJibunA}</td>
                    <td>${custDto.CDetA}</td>
                    <td>${custDto.loginDt}</td>
                    <td><fmt:formatDate value="${custDto.createdAt}" pattern="yyyy-MM-dd" /></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
