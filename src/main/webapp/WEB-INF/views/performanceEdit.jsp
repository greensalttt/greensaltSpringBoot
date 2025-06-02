<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Performance Edit</title>
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
            height: 160px;
            position: relative;
            overflow-x: hidden;
        }

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #performancePageContainer {
            margin-top: 100px;
            margin-bottom: 100px;
            padding: 20px;
            flex: 1;
            text-align: center;
        }

        .performance-image {
            margin-bottom: 20px;
        }

        .performance-image img {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }

        .performance-info {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-left: 135px;
        }

        .performance-info p {
            display: flex;
            align-items: center;
            font-size: 14px;
            width: 300px;
            margin-bottom: 8px;
        }

        .performance-content {
            margin-top: 77px;
            margin-bottom: 100px;
            text-align: left;
        }

        .performance-content h3 {
            font-size: 14px;
            margin-bottom: 10px;
        }

        .performance-content textarea {
            font-size: 15px;
            line-height: 1.6;
            color: #444;
            width: 100%;
        }

        button {
            padding: 10px 20px;
            font-size: 14px;
            margin-top: 20px;
            cursor: pointer;
        }
    </style>
</head>

<body>
<div id="wrapper">
    <form action="/admin/performance_modify" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div id="performancePageContainer">
            <div class="performance-image">
                <img id="performancePreview" src="${performanceDto.img}" alt="공연 이미지">
                <p>
                    <label>이미지 변경:
                        <input type="file" name="imgFile" id="imgFile">
                    </label>
                </p>
            </div>

            <div class="performance-info">
                <input type="hidden" name="pno" value="${performanceDto.pno}" />

                <p><strong>공연 제목:</strong>&nbsp;
                    <input type="text" name="title" value="${performanceDto.title}" />
                </p>

                <p><strong>아티스트:</strong>&nbsp;
                    <input type="text" name="artist" value="${performanceDto.artist}" />
                </p>

                <p><strong>공연 시간:</strong>&nbsp;
                    <input type="text" name="duration" value="${performanceDto.duration}" />
                </p>

                <p><strong>관람 연령:</strong>&nbsp;
                    <input type="text" name="rating" value="${performanceDto.rating}" />
                </p>

                <p><strong>장소:</strong>&nbsp;
                    <input type="text" name="venue" value="${performanceDto.venue}" />
                </p>

                <p><strong>장르:</strong>&nbsp;
                    <input type="text" name="genre" value="${performanceDto.genre}" />
                </p>

                <p><strong>공연 날짜:</strong>&nbsp;
                    <input type="date" name="date" value="${performanceDto.date}" />
                </p>
            </div>

            <div class="performance-content">
                <h3>공연 소개</h3>
                <textarea name="content" rows="10" cols="50">${performanceDto.content}</textarea>
            </div>

            <button type="submit">수정 완료</button>
        </div>
    </form>
</div>
</body>

<script>
    document.getElementById('imgFile').addEventListener('change', function (e) {
        const previewImg = document.getElementById('performancePreview');
        const file = e.target.files[0];

        if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function (event) {
                previewImg.src = event.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            alert("이미지 파일을 선택해주세요.");
        }
    });

    function validateForm() {
        const fields = ["title", "artist", "duration", "rating", "venue", "genre", "date"];
        for (let field of fields) {
            const value = document.querySelector(`input[name="${field}"]`).value.trim();
            if (!value) {
                alert(`${field}을(를) 입력해주세요.`);
                return false;
            }
        }
        const content = document.querySelector("textarea[name='content']").value.trim();
        if (!content) {
            alert("공연 소개를 입력해주세요.");
            return false;
        }
        return true;
    }

    let performanceModifyFail = "${performanceModifyFail}";
    if (performanceModifyFail === "msg") {
        alert("공연 수정에 실패했습니다.");
    }

    let testAid = "${testAid}";
    if (testAid === "msg") {
        alert("테스트 아이디는 수정할 수 없습니다.");
    }

</script>

