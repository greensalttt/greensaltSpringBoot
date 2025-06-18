<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setTimeZone value="Asia/Seoul" />

<head>
    <title>myCommentList</title>
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
            padding: 20px;
            flex: 1;
            width: 90%;
            margin: 100px auto 100px auto;
        }

        .domestic {
            display: flex;
            align-items: center;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
            margin-bottom: 20px;
            font-size: 20px;
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
            font-size: 14px;
        }

        td {
            font-size: 13px;
        }

        .no { width: 10%; }
        .title { width: 40%; text-align: left; }
        .writer { width: 15%; }
        .regdt { width: 25%; }
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
<div id="wrapper">
    <div id="commentListContainer">
        <div class="domestic">작성 댓글</div>

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
                    <th class="actions">관리</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="commentDto" items="${commentDtos}">
                    <tr>
                        <td class="no">
                            <a href="/board/read?bno=${commentDto.bno}">
                                <c:out value="${commentDto.bno}" />
                            </a>
                        </td>
                        <td class="title">${commentDto.comment}</td>
                        <td class="writer">${commentDto.commenter}</td>
                        <c:choose>
                            <c:when test="${commentDto.createdAt.time >= startOfToday}">
                                <td class="regdt">
                                    <fmt:formatDate value="${commentDto.createdAt}" pattern="HH:mm" />
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="regdt">
                                    <fmt:formatDate value="${commentDto.createdAt}" pattern="yyyy-MM-dd" />
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <td class="actions">
                            <form action="/admin/comment_remove" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                <input type="hidden" name="cno" value="${commentDto.cno}">
                                <input type="hidden" name="bno" value="${commentDto.bno}">
                                <input type="hidden" name="createdBy" value="${commentDto.createdBy}">

                                <button type="submit" class="delete-btn">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<a href="/admin/page" class="back-button">← 관리자 대시보드로</a>
</body>

<script>
    let testAid = "${testAid}";
    if (testAid === "msg") {
        alert("테스트 아이디는 삭제할 수 없습니다.");
    }

    let removeFail = "${removeFail}"
    if(removeFail==="msg") {
        alert("댓글 삭제가 실패했습니다.")
    }

    let remove = "${remove}"
    if(remove==="msg") {
        alert("댓글 삭제가 완료되었습니다.")
    }
</script>
