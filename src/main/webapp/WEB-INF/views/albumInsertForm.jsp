<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>앨범 등록</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;--%>
<%--            background-color: #f4f6f9;--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--        }--%>

<%--        #album {--%>
<%--            max-width: 700px;--%>
<%--            margin: 100px auto;--%>
<%--            background-color: #fff;--%>
<%--            padding: 40px 50px;--%>
<%--            border-radius: 8px;--%>
<%--            box-shadow: 0 0 10px rgba(0,0,0,0.1);--%>
<%--        }--%>

<%--        h2 {--%>
<%--            text-align: center;--%>
<%--            color: #333;--%>
<%--            margin-bottom: 30px;--%>
<%--        }--%>

<%--        label {--%>
<%--            font-weight: bold;--%>
<%--            display: block;--%>
<%--            margin-top: 20px;--%>
<%--            margin-bottom: 5px;--%>
<%--        }--%>

<%--        input[type="text"],--%>
<%--        input[type="date"],--%>
<%--        select,--%>
<%--        textarea,--%>
<%--        input[type="file"] {--%>
<%--            width: 100%;--%>
<%--            padding: 10px;--%>
<%--            font-size: 14px;--%>
<%--            border: 1px solid #ccc;--%>
<%--            border-radius: 4px;--%>
<%--            box-sizing: border-box;--%>
<%--        }--%>

<%--        textarea {--%>
<%--            resize: vertical;--%>
<%--        }--%>

<%--        button {--%>
<%--            margin-top: 30px;--%>
<%--            width: 100%;--%>
<%--            padding: 12px;--%>
<%--            background-color: darkgreen;--%>
<%--            color: white;--%>
<%--            border: none;--%>
<%--            font-size: 16px;--%>
<%--            border-radius: 4px;--%>
<%--            cursor: pointer;--%>
<%--        }--%>

<%--        #preview {--%>
<%--            margin-top: 10px;--%>
<%--            width: 100px;--%>
<%--            height: 100px;--%>
<%--            display: none;--%>
<%--            border-radius: 4px;--%>
<%--            border: 1px solid #ddd;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="album">--%>
<%--    <h2>앨범 등록</h2>--%>

<%--    <form id="albumForm" action="/admin/album_write" method="post" enctype="multipart/form-data">--%>

<%--        <label for="type">유형:</label>--%>
<%--        <select name="type" id="type" required>--%>
<%--            <option value="">-- 선택하세요 --</option>--%>
<%--            <option value="싱글">싱글</option>--%>
<%--            <option value="EP">EP</option>--%>
<%--            <option value="정규">정규</option>--%>
<%--            <option value="믹스테잎">믹스테잎</option>--%>
<%--        </select>--%>

<%--        <label for="genre">장르:</label>--%>
<%--        <input type="text" name="genre" id="genre" maxlength="15" required>--%>

<%--        <label for="title">앨범 제목:</label>--%>
<%--        <input type="text" name="title" id="title" maxlength="30" required>--%>

<%--        <label for="artist">아티스트:</label>--%>
<%--        <input type="text" name="artist" id="artist" maxlength="20" required>--%>

<%--        <label for="content">내용:</label>--%>
<%--        <textarea name="content" id="content" rows="8"></textarea>--%>

<%--        <label for="released">발매일:</label>--%>
<%--        <input type="date" name="released" id="released" maxlength="10">--%>

<%--        <label for="imgFile">앨범 이미지:</label>--%>
<%--        <input type="file" name="imgFile" id="imgFile" accept="image/*">--%>
<%--        <img id="preview" alt="미리보기 이미지"><br>--%>

<%--        <button type="submit">앨범 등록</button>--%>
<%--    </form>--%>
<%--</div>--%>

<%--<script>--%>
<%--    // 이미지 미리보기--%>
<%--    document.getElementById('imgFile').addEventListener('change', function (e) {--%>
<%--        const preview = document.getElementById('preview');--%>
<%--        const file = e.target.files[0];--%>

<%--        if (file && file.type.startsWith('image/')) {--%>
<%--            const reader = new FileReader();--%>
<%--            reader.onload = function (event) {--%>
<%--                preview.src = event.target.result;--%>
<%--                preview.style.display = 'block';--%>
<%--            };--%>
<%--            reader.readAsDataURL(file);--%>
<%--        } else {--%>
<%--            preview.style.display = 'none';--%>
<%--        }--%>
<%--    });--%>

<%--    // 기본 유효성 검사--%>
<%--    document.getElementById('albumForm').addEventListener('submit', function (e) {--%>
<%--        const requiredFields = ['type', 'genre', 'title', 'artist',  'content','released', 'imgFile'];--%>
<%--        for (let id of requiredFields) {--%>
<%--            const input = document.getElementById(id);--%>
<%--            if (!input.value.trim()) {--%>
<%--                alert(`${input.previousElementSibling.innerText} 항목을 입력해주세요.`);--%>
<%--                input.focus();--%>
<%--                e.preventDefault();--%>
<%--                return false;--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>


<%--    let testAid = "${testAid}";--%>
<%--    if (testAid === "msg") {--%>
<%--        alert("테스트 아이디는 등록할 수 없습니다.");--%>
<%--    }--%>

<%--    let adminWriteFail = "${adminWriteFail}";--%>
<%--    if (adminWriteFail === "msg") {--%>
<%--        alert("앨범 등록에 실패했습니다.");--%>
<%--    }--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
