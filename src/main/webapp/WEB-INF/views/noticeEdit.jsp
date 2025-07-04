<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 수정</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        #notice {
            max-width: 700px;
            margin: 100px auto;
            background-color: #fff;
            padding: 40px 50px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 20px;
            margin-bottom: 5px;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
        }

        button {
            margin-top: 30px;
            width: 100%;
            padding: 12px;
            background-color: darkgreen;
            color: white;
            border: none;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<div id="notice">
    <h2>공지사항 수정</h2>

    <form id="noticeForm" action="/notice/modify" method="post">
        <!-- 수정할 공지사항 번호를 hidden으로 넘겨줘야 함 -->
        <input type="hidden" name="nno" value="${noticeDto.nno}" />

        <label for="title">제목:</label>
        <input type="text" name="title" id="title" maxlength="30" required value="${noticeDto.title}" />

        <label for="content">내용:</label>
        <textarea name="content" id="content" rows="8">${noticeDto.content}</textarea>

        <button type="submit">공지사항 수정</button>
    </form>
</div>

<c:if test="${not empty msg}">
    <script>
        alert("${msg}")
    </script>
</c:if>

<script>
    document.getElementById('noticeForm').addEventListener('submit', function (e) {
        const titleInput = document.getElementById('title');
        const contentInput = document.getElementById('content');

        const title = titleInput.value.trim();
        const content = contentInput.value.trim();

        if (!title) {
            alert('제목 항목을 입력해주세요.');
            titleInput.focus();
            e.preventDefault();
            return false;
        }

        if (!content) {
            alert('내용 항목을 입력해주세요.');
            contentInput.focus();
            e.preventDefault();
            return false;
        }
    });

</script>
</body>
</html>
