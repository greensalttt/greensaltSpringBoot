<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공지사항 등록</title>
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
    <h2>공지사항 등록</h2>

    <form id="noticeForm" action="/admin/notice_write" method="post">
        
        <label for="title">제목:</label>
        <input type="text" name="title" id="title" maxlength="30" required>
        
        <label for="content">내용:</label>
        <textarea name="content" id="content" rows="8"></textarea>
        
        <button type="submit">공지사항 등록</button>
    </form>
</div>

<c:if test="${not empty adminWriteFail}">
    <script>
        alert("${adminWriteFail}");
    </script>
</c:if>

<c:if test="${not empty testAid}">
    <script>
        alert("${testAid}");
    </script>
</c:if>



<script>

    // 기본 유효성 검사
    document.getElementById('noticeForm').addEventListener('submit', function (e) {
        const requiredFields = ['title', 'content'];
        for (let id of requiredFields) {
            const input = document.getElementById(id);
            if (!input.value.trim()) {
                alert(`${input.previousElementSibling.innerText} 항목을 입력해주세요.`);
                input.focus();
                e.preventDefault();
                return false;
            }
        }
    });


    <%--let testAid = "${testAid}";--%>
    <%--if (testAid === "msg") {--%>
    <%--    alert("테스트 아이디는 등록할 수 없습니다.");--%>
    <%--}--%>

    <%--let adminWriteFail = "${adminWriteFail}";--%>
    <%--if (adminWriteFail === "msg") {--%>
    <%--    alert("공지사항 등록에 실패했습니다.");--%>
    <%--}--%>
</script>
</body>
</html>
