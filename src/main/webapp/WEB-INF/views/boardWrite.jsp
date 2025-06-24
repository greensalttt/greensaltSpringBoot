<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page session="true"%>--%>

<%--<head>--%>
<%--    <title>Green Salt</title>--%>
<%--    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">--%>
<%--    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>--%>
<%--    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">--%>
<%--    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">--%>

<%--    <style>--%>
<%--        * {--%>
<%--            box-sizing: border-box;--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--        }--%>

<%--        .container {--%>
<%--            width: 50%;--%>
<%--            margin: 200px auto 150px auto;--%>
<%--        }--%>

<%--        .writing-header {--%>
<%--            font-size: 18px;--%>
<%--            font-weight: bold;--%>
<%--            margin: 20px 0;--%>
<%--            padding-bottom: 10px;--%>
<%--            border-bottom: 1px solid #323232;--%>
<%--        }--%>

<%--        .boardTitle, textarea {--%>
<%--            width: 100%;--%>
<%--            background: #f8f8f8;--%>
<%--            margin: 5px 0 10px 0;--%>
<%--            border: 1px solid #e9e8e8;--%>
<%--            padding: 8px;--%>
<%--            font-size: 14px;--%>
<%--        }--%>

<%--        textarea {--%>
<%--            resize: none;--%>
<%--            height: 300px;--%>
<%--        }--%>

<%--        .btn {--%>
<%--            background-color: rgb(236, 236, 236);--%>
<%--            border: none;--%>
<%--            color: black;--%>
<%--            padding: 6px 12px;--%>
<%--            font-size: 14px;--%>
<%--            cursor: pointer;--%>
<%--            border-radius: 5px;--%>
<%--        }--%>

<%--        .btn:hover {--%>
<%--            background-color: #ddd;--%>
<%--        }--%>

<%--        .btn-group {--%>
<%--            margin-top: 20px;--%>
<%--            display: flex;--%>
<%--            gap: 10px;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>

<%--<body>--%>
<%--<header>--%>
<%--    <jsp:include page="header.jsp"/>--%>
<%--</header>--%>

<%--<div class="container">--%>
<%--    <p class="writing-header">글쓰기</p>--%>

<%--    <form id="writeForm" action="<c:url value='/board/write'/>" method="post">--%>
<%--        <input class="boardTitle" name="title" type="text" placeholder="제목을 입력해 주세요."><br>--%>
<%--        <textarea name="content" placeholder="내용을 입력해 주세요."></textarea><br>--%>

<%--        <div class="btn-group">--%>
<%--            <button type="submit" class="btn">등록</button>--%>
<%--            <button type="button" id="cancelBtn" class="btn">취소</button>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>

<%--<footer>--%>
<%--    <jsp:include page="footer.jsp"/>--%>
<%--</footer>--%>

<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $("#writeForm").on("submit", function (e) {--%>
<%--            const title = $("input[name='title']").val().trim();--%>
<%--            const content = $("textarea[name='content']").val().trim();--%>

<%--            if (title === "") {--%>
<%--                alert("제목을 입력해 주세요.");--%>
<%--                $("input[name='title']").focus();--%>
<%--                e.preventDefault();--%>
<%--                return false;--%>
<%--            }--%>

<%--            if (content === "") {--%>
<%--                alert("내용을 입력해 주세요.");--%>
<%--                $("textarea[name='content']").focus();--%>
<%--                e.preventDefault();--%>
<%--                return false;--%>
<%--            }--%>
<%--        });--%>

<%--        $("#cancelBtn").on("click", function () {--%>
<%--            if (confirm("작성을 취소하시겠습니까?")) {--%>
<%--                location.href = "<c:url value='/board/list'/>";--%>
<%--            }--%>
<%--        });--%>

<%--        // 알림 메시지 처리 (등록 실패 등)--%>
<%--        const msg = "${msg}";--%>
<%--        if (msg === "WRT_ERR") {--%>
<%--            alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<%--</body>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>

<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/header.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/footer.css'/>">

    <style>
        /* 기존 스타일 그대로 유지 */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 50%;
            margin: 200px auto 150px auto;
        }

        .writing-header {
            font-size: 18px;
            font-weight: bold;
            margin: 20px 0;
            padding-bottom: 10px;
            border-bottom: 1px solid #323232;
        }

        .boardTitle, textarea {
            width: 100%;
            background: #f8f8f8;
            margin: 5px 0 10px 0;
            border: 1px solid #e9e8e8;
            padding: 8px;
            font-size: 14px;
        }

        textarea {
            resize: none;
            height: 300px;
        }

        .btn {
            background-color: rgb(236, 236, 236);
            border: none;
            color: black;
            padding: 6px 12px;
            font-size: 14px;
            cursor: pointer;
            border-radius: 5px;
        }

        .btn:hover {
            background-color: #ddd;
        }

        .btn-group {
            margin-top: 20px;
            display: flex;
            gap: 10px;
        }
    </style>
</head>

<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<div class="container">
    <p class="writing-header">
        <c:choose>
            <c:when test="${mode eq 'modify'}">글 수정</c:when>
            <c:otherwise>글쓰기</c:otherwise>
        </c:choose>
    </p>

    <form id="writeForm"
          action="<c:choose>
                      <c:when test='${mode eq "modify"}'>
                          <c:url value='/board/modify'/>
                      </c:when>
                      <c:otherwise>
                          <c:url value='/board/write'/>
                      </c:otherwise>
                  </c:choose>"
          method="post">

        <!-- 수정일 경우 bno 필요 -->
        <c:if test="${mode eq 'modify'}">
            <input type="hidden" name="bno" value="${boardDto.bno}"/>
        </c:if>

        <input class="boardTitle" name="title" type="text"
               value="<c:out value='${boardDto.title}'/>"
               placeholder="제목을 입력해 주세요."><br>

        <textarea name="content" placeholder="내용을 입력해 주세요."><c:out value='${boardDto.content}'/></textarea><br>

        <div class="btn-group">
            <button type="submit" class="btn">
                <c:choose>
                    <c:when test="${mode eq 'modify'}">수정</c:when>
                    <c:otherwise>등록</c:otherwise>
                </c:choose>
            </button>

            <button type="button" id="cancelBtn" class="btn">취소</button>
        </div>
    </form>
</div>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

<script>
    $(document).ready(function () {
        $("#writeForm").on("submit", function (e) {
            const title = $("input[name='title']").val().trim();
            const content = $("textarea[name='content']").val().trim();

            if (title === "") {
                alert("제목을 입력해 주세요.");
                $("input[name='title']").focus();
                e.preventDefault();
                return false;
            }

            if (content === "") {
                alert("내용을 입력해 주세요.");
                $("textarea[name='content']").focus();
                e.preventDefault();
                return false;
            }
        });

        $("#cancelBtn").on("click", function () {
            if (confirm("작업을 취소하시겠습니까?")) {
                location.href = "<c:url value='/board/list'/>";
            }
        });

        const msg = "${msg}";
        if (msg === "WRT_ERR") {
            alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
        }
        if (msg === "MOD_ERR") {
            alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
        }
    });
</script>
</body>
