<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setTimeZone value="Asia/Seoul" />


<head>
    <title>boardManage</title>
    <style>
        /** {*/
        /*    margin: 0;*/
        /*    padding: 0;*/
        /*    color: black;*/
        /*    box-sizing: border-box;*/
        /*    text-decoration: none;*/
        /*}*/

        /*body {*/
        /*    background-color: whitesmoke;*/
        /*    margin: 0 auto;*/
        /*    max-width: 1130px;*/
        /*    height: 160px;*/
        /*    position: relative;*/
        /*    overflow-x: hidden;*/
        /*}*/

        /*#wrapper {*/
        /*    min-height: 100vh;*/
        /*    display: flex;*/
        /*    flex-direction: column;*/
        /*}*/

        /*#boardListContainer {*/
        /*    padding: 20px;*/
        /*    flex: 1;*/
        /*    width: 90%;*/
        /*    margin: 100px auto 100px auto;*/
        /*}*/

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

        .domestic {
            display: flex;
            align-items: center;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .cate a {
            color: #333;
            font-weight: bold;
            margin: 0 5px;
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

        #noboard {
            margin-top: 100px;
            margin-bottom: 120px;
            text-align: center;
            font-size: 18px;
        }

        a {
            color: #333;
        }

        .delete-btn {
            background-color: darkgreen;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
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
<%--<div id="wrapper">--%>
    <div class="container">
<%--        <div class="domestic">--%>
<%--            <span class="cate">--%>
<%--                <a href="/admin/page">관리자 메인 페이지</a>--%>
<%--            </span>--%>
<%--        </div>--%>
    <div class="domestic">게시글 관리</div>


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
                    <th class="actions">관리</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="boardDto" items="${boardDtos}">
                    <tr>
                        <td class="no">${boardDto.bno}</td>
                        <td class="title">
                            <a href="/board/read?bno=${boardDto.bno}">
                                <c:out value="${boardDto.title}"/>
                            </a>
                        </td>
                        <td class="writer">${boardDto.writer}</td>

                        <c:choose>
                            <c:when test="${boardDto.createdAt.time >= startOfToday}">
                                <td class="regdt">
                                    <fmt:formatDate value="${boardDto.createdAt}" pattern="HH:mm" type="time"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="regdt">
                                    <fmt:formatDate value="${boardDto.createdAt}" pattern="yyyy-MM-dd" type="date"/>
                                </td>
                            </c:otherwise>
                        </c:choose>

                        <td class="viewcnt">${boardDto.viewCnt}</td>
                        <td class="actions">
                            <form action="/admin/board_remove" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                <input type="hidden" name="bno" value="${boardDto.bno}">
                                <input type="hidden" name="createdBy" value="${boardDto.createdBy}">
                                <button type="submit" class="delete-btn">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <a href="/admin/page" class="back-button">← 관리자 대시보드로</a>
    </div>
<%--</div>--%>

</body>


<script>

    let testAid = "${testAid}";
    if (testAid === "msg") {
        alert("테스트 아이디는 삭제할 수 없습니다.");
    }

    let removeFail = "${removeFail}"
    if(removeFail==="msg") {
        alert("게시글 삭제가 실패했습니다.")
    }

    let remove = "${remove}"
    if(remove==="msg") {
        alert("게시글 삭제가 완료되었습니다.")
    }

</script>

