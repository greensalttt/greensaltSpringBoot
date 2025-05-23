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
    <form action="/admin/modify" method="post" enctype="multipart/form-data">
        <div id="albumPageContainer">
            <div class="album-image">
                <img src="${albumDto.img}" alt="앨범 이미지" style="max-width: 200px;">
                <p>
                    <label>이미지 변경:
                        <input type="file" name="imgFile">
                    </label>
                </p>
            </div>
            <div class="album-info">
                <input type="hidden" name="ano" value="${albumDto.ano}" />

                <p><strong>album:</strong>
                    <input type="text" name="title" value="${albumDto.title}" />
                </p>

                <p><strong>artist:</strong>
                    <input type="text" name="artist" value="${albumDto.artist}" />
                </p>

                <p><strong>type:</strong>
                    <input type="text" name="type" value="${albumDto.type}" />
                </p>

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
    let modifyFail = "${modifyFail}"
    if(modifyFail==="msg") {
        alert("앨범 수정에 실패했습니다.")
    }
</script>
