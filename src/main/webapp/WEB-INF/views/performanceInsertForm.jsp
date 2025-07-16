<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공연 등록</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        #performance {
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
        input[type="date"],
        select,
        textarea,
        input[type="file"] {
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

        #preview {
            margin-top: 10px;
            width: 100px;
            height: 100px;
            display: none;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
    </style>
</head>
<body>
<div id="performance">
    <h2>공연 등록</h2>

    <form id="performanceForm" action="/admin/performance_write" method="post" enctype="multipart/form-data">

        <label for="title">공연 제목:</label>
        <input type="text" name="title" id="title" maxlength="30" required>

        <label for="artist">아티스트명:</label>
        <input type="text" name="artist" id="artist" maxlength="20" required>

<%--        <label for="genre">장르:</label>--%>
<%--        <input type="text" name="genre" id="genre" maxlength="15">--%>

        <label for="genre">장르:</label>
        <select name="genre" id="genre" required>
            <option value="">-- 선택하세요 --</option>
            <option value="콘서트">콘서트</option>
            <option value="스포츠">스포츠</option>
        </select>


        <label for="venue">공연장:</label>
        <input type="text" name="venue" id="venue" maxlength="20" required>

        <label for="duration">관람 시간:</label>
        <input type="text" name="duration" id="duration" placeholder="예: 120분" maxlength="10" required>

        <label for="rating">관람 등급:</label>
        <input type="text" name="rating" id="rating" placeholder="예: 전체관람가" maxlength="10" required>

        <label for="date">공연 날짜:</label>
        <input type="date" name="date" id="date" maxlength="10" required>

        <label for="price">가격:</label>
        <input type="text" name="price" id="price" maxlength="10" required>

        <label for="content">공연 설명:</label>
        <textarea name="content" id="content" rows="6"></textarea>

        <label for="imgFile">공연 포스터:</label>
        <input type="file" name="imgFile" id="imgFile" accept="image/*">
        <img id="preview" alt="미리보기 이미지"><br>

        <button type="submit">공연 등록</button>
    </form>
</div>

<script>
    // 이미지 미리보기
    document.getElementById('imgFile').addEventListener('change', function (e) {
        const preview = document.getElementById('preview');
        const file = e.target.files[0];

        if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function (event) {
                preview.src = event.target.result;
                preview.style.display = 'block';
            };
            reader.readAsDataURL(file);
        } else {
            preview.style.display = 'none';
        }
    });

    // 기본 유효성 검사
    document.getElementById('performanceForm').addEventListener('submit', function (e) {
        const requiredFields = [ 'title', 'artist', 'genre',
            'venue', 'duration', 'rating', 'date', 'price',
            'content', 'imgFile'];
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

    let performanceWriteFail = "${performanceWriteFail}";
    if (performanceWriteFail === "msg") {
        alert("공연 등록에 실패했습니다.");
    }


    let testAid = "${testAid}";
    if (testAid === "msg") {
        alert("테스트 아이디는 등록할 수 없습니다.");
    }

</script>
</body>
</html>
