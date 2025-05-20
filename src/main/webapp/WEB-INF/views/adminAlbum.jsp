<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        #album{
            margin-left: 200px;
            margin-top: 100px;
        }
    </style>
</head>

<body>
<div id="album">
<h2>앨범 등록</h2>

<form action="/admin/write" method="post" enctype="multipart/form-data">
<label>국내/해외(domestic):</label>
    <select name="domestic" required>
        <option value="">-- 선택하세요 --</option>
        <option value="국내">국내</option>
        <option value="해외">해외</option>
    </select><br>

    <label>유형(type):</label>
    <select name="type" required>
        <option value="">-- 선택하세요 --</option>
        <option value="싱글">싱글</option>
        <option value="EP">EP</option>
        <option value="정규">정규</option>
        <option value="믹스테잎">믹스테잎</option>
    </select><br>

    <label>장르(genre):</label>
    <input type="text" name="genre"><br>

    <label>앨범 제목(title):</label>
    <input type="text" name="title" required><br>

    <label>아티스트(artist):</label>
    <input type="text" name="artist" required><br>

    <label>내용(content):</label><br>
    <textarea name="content" rows="15" cols="50"></textarea><br>

    <label>발매일(released):</label>
    <input type="date" name="released"><br>

    <label>이미지 URL(img):</label>
    <input type="file" name="imgFile"><br><br>

    <button type="submit">앨범 등록</button>
</form>
</div>


<script>
    let adminWriteFail = "${adminWriteFail}"
    if(adminWriteFail==="msg") {
        alert("앨범 등록에 실패했습니다.");
    }
</script>

</body>
</html>
