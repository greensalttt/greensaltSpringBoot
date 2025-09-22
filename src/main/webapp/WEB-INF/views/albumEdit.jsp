<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<head>--%>
<%--    <title>앨범 수정</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;--%>
<%--            background-color: #f4f6f9;--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--        }--%>

<%--        #wrapper {--%>
<%--            min-height: 100vh;--%>
<%--            display: flex;--%>
<%--            flex-direction: column;--%>
<%--        }--%>

<%--        #albumPageContainer {--%>
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

<%--        .album-image img {--%>
<%--            width: 100px;--%>
<%--            height: 100px;--%>
<%--            object-fit: cover;--%>
<%--            border-radius: 4px;--%>
<%--            border: 1px solid #ddd;--%>
<%--            margin-top: 10px;--%>
<%--            display: block;--%>
<%--        }--%>

<%--        .album-content {--%>
<%--            margin-top: 20px;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div id="wrapper">--%>
<%--    <form action="/admin/album_modify" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">--%>
<%--        <div id="albumPageContainer">--%>
<%--            <h2>앨범 수정</h2>--%>

<%--            <input type="hidden" name="ano" value="${albumDto.ano}" />--%>

<%--            <label for="albumPreview">앨범 이미지:</label>--%>
<%--            <img id="albumPreview" src="${albumDto.img}" alt="앨범 이미지" style="max-width: 200px;">--%>
<%--            <label for="imgFile">이미지 변경:</label>--%>
<%--            <input type="file" name="imgFile" id="imgFile" accept="image/*">--%>

<%--            <label for="title">제목:</label>--%>
<%--            <input type="text" name="title" id="title" value="${albumDto.title}" maxlength="30" required>--%>

<%--            <label for="artist">아티스트:</label>--%>
<%--            <input type="text" name="artist" id="artist" value="${albumDto.artist}" maxlength="20" required>--%>

<%--            <label for="type">유형:</label>--%>
<%--            <select name="type" id="type" required>--%>
<%--                <option value="">-- 선택하세요 --</option>--%>
<%--                <option value="싱글" ${albumDto.type == '싱글' ? 'selected' : ''}>싱글</option>--%>
<%--                <option value="EP" ${albumDto.type == 'EP' ? 'selected' : ''}>EP</option>--%>
<%--                <option value="정규" ${albumDto.type == '정규' ? 'selected' : ''}>정규</option>--%>
<%--                <option value="믹스테잎" ${albumDto.type == '믹스테잎' ? 'selected' : ''}>믹스테잎</option>--%>
<%--            </select>--%>

<%--            <label for="genre">장르:</label>--%>
<%--            <input type="text" name="genre" id="genre" value="${albumDto.genre}" maxlength="15">--%>

<%--            <label for="released">발매일:</label>--%>
<%--            <input type="date" name="released" id="released" value="${albumDto.released}" maxlength="10">--%>

<%--            <div class="album-content">--%>
<%--                <label for="content">앨범 소개:</label>--%>
<%--                <textarea name="content" id="content" rows="10">${albumDto.content}</textarea>--%>
<%--            </div>--%>

<%--            <button type="submit">수정 완료</button>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>


<%--<script>--%>
<%--     document.getElementById('imgFile').addEventListener('change', function (e) {--%>
<%--        const previewImg = document.getElementById('albumPreview');--%>
<%--        const file = e.target.files[0];--%>

<%--        if (file && file.type.startsWith('image/')) {--%>
<%--        const reader = new FileReader();--%>
<%--        reader.onload = function (event) {--%>
<%--        previewImg.src = event.target.result; // 기존 이미지 태그의 src를 변경--%>
<%--    };--%>
<%--        reader.readAsDataURL(file);--%>
<%--    } else {--%>
<%--        alert("이미지 파일을 선택해주세요.");--%>
<%--    }--%>
<%--    });--%>


<%--function validateForm() {--%>
<%--        const title = document.querySelector('input[name="title"]').value.trim();--%>
<%--        const artist = document.querySelector('input[name="artist"]').value.trim();--%>
<%--        const type = document.querySelector('input[name="type"]').value.trim();--%>
<%--        const genre = document.querySelector('input[name="genre"]').value.trim();--%>
<%--        const released = document.querySelector('input[name="released"]').value;--%>
<%--        const content = document.querySelector('textarea[name="content"]').value.trim();--%>

<%--        if (!title) {--%>
<%--            alert("앨범 제목을 입력하세요.");--%>
<%--            return false;--%>
<%--        }--%>
<%--        if (!artist) {--%>
<%--            alert("아티스트 이름을 입력하세요.");--%>
<%--            return false;--%>
<%--        }--%>
<%--        if (!type) {--%>
<%--            alert("앨범 타입을 입력하세요.");--%>
<%--            return false;--%>
<%--        }--%>
<%--        if (!genre) {--%>
<%--            alert("장르를 입력하세요.");--%>
<%--            return false;--%>
<%--        }--%>
<%--        if (!released) {--%>
<%--            alert("발매일을 선택하세요.");--%>
<%--            return false;--%>
<%--        }--%>
<%--        if (!content) {--%>
<%--            alert("앨범 소개를 입력하세요.");--%>
<%--            return false;--%>
<%--        }--%>

<%--        return true; // 모든 검사를 통과했을 때만 제출 허용--%>
<%--    }--%>


<%--     let testAid = "${testAid}";--%>
<%--     if (testAid === "msg") {--%>
<%--         alert("테스트 아이디는 수정할 수 없습니다.");--%>
<%--     }--%>


<%--    let modifyFail = "${modifyFail}"--%>
<%--    if(modifyFail==="msg") {--%>
<%--        alert("앨범 수정에 실패했습니다.")--%>
<%--    }--%>
<%--</script>--%>
