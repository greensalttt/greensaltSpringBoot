<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <title>albumEdit</title>

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

        #albumPageContainer {
            margin-top: 100px;
            margin-bottom: 100px;
            padding: 20px;
            flex: 1;
            text-align: center;

        }

        .album-image {
            margin-bottom: 20px;
        }

        .album-image img {
            width: 250px;
            height: 250px;
            object-fit: cover;
            border-radius: 10px;
        }

        .album-info {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-left: 135px;
        }

        .album-info p {
            display: flex;
            align-items: center;
            font-size: 14px;
            width: 300px;
        }


        .album-content {
            margin-top: 77px;
            margin-bottom: 100px;
            text-align: left;
        }

        .album-content h3 {
            font-size: 14px;
            margin-bottom: 10px;
        }

        .album-content p {
            font-size: 15px;
            line-height: 1.6;
            color: #444;
        }
    </style>

</head>
<body>

<div id="wrapper">
    <form action="/admin/album_modify" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div id="albumPageContainer">
            <div class="album-image">
                <img id="albumPreview" src="${albumDto.img}" alt="앨범 이미지" style="max-width: 200px;">
                <p>
                    <label>이미지 변경:
                        <input type="file" name="imgFile" id="imgFile">
                    </label>
                </p>
            </div>

    <div class="album-info">
                <input type="hidden" name="ano" value="${albumDto.ano}" />


        <p><strong>title:</strong>
                    <input type="text" name="title" value="${albumDto.title}" />
                </p>

                <p><strong>artist:</strong>
                    <input type="text" name="artist" value="${albumDto.artist}" />
                </p>


                <label for="type">유형:</label>
                <select name="type" id="type" required>
                    <option value="">-- 선택하세요 --</option>
                    <option value="싱글" ${albumDto.type == '싱글' ? 'selected' : ''}>싱글</option>
                    <option value="EP" ${albumDto.type == 'EP' ? 'selected' : ''}>EP</option>
                    <option value="정규" ${albumDto.type == '정규' ? 'selected' : ''}>정규</option>
                    <option value="믹스테잎" ${albumDto.type == '믹스테잎' ? 'selected' : ''}>믹스테잎</option>
                </select>



        <p><strong>genre:</strong>
                    <input type="text" name="genre" value="${albumDto.genre}" />
                </p>

                <p><strong>released:</strong>
                    <input type="date" name="released" value="${albumDto.released}" />
                </p>
            </div>

            <div class="album-content">
                <h3>앨범 소개</h3>
                <textarea name="content" rows="10" cols="50">${albumDto.content}</textarea>
            </div>

                <button type="submit">수정 완료</button>

        </div>
    </form>


</div>
</body>

<script>
     document.getElementById('imgFile').addEventListener('change', function (e) {
        const previewImg = document.getElementById('albumPreview');
        const file = e.target.files[0];

        if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = function (event) {
        previewImg.src = event.target.result; // 기존 이미지 태그의 src를 변경
    };
        reader.readAsDataURL(file);
    } else {
        alert("이미지 파일을 선택해주세요.");
    }
    });


function validateForm() {
        const title = document.querySelector('input[name="title"]').value.trim();
        const artist = document.querySelector('input[name="artist"]').value.trim();
        const type = document.querySelector('input[name="type"]').value.trim();
        const genre = document.querySelector('input[name="genre"]').value.trim();
        const released = document.querySelector('input[name="released"]').value;
        const content = document.querySelector('textarea[name="content"]').value.trim();

        if (!title) {
            alert("앨범 제목을 입력하세요.");
            return false;
        }
        if (!artist) {
            alert("아티스트 이름을 입력하세요.");
            return false;
        }
        if (!type) {
            alert("앨범 타입을 입력하세요.");
            return false;
        }
        if (!genre) {
            alert("장르를 입력하세요.");
            return false;
        }
        if (!released) {
            alert("발매일을 선택하세요.");
            return false;
        }
        if (!content) {
            alert("앨범 소개를 입력하세요.");
            return false;
        }

        return true; // 모든 검사를 통과했을 때만 제출 허용
    }


     let testAid = "${testAid}";
     if (testAid === "msg") {
         alert("테스트 아이디는 수정할 수 없습니다.");
     }


    let modifyFail = "${modifyFail}"
    if(modifyFail==="msg") {
        alert("앨범 수정에 실패했습니다.")
    }
</script>
