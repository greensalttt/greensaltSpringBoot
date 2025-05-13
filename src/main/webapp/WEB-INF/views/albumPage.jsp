<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <title>albumPage</title>

    <style>

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #albumPageContainer {
            margin-top: 200px;
            padding: 20px;
            flex: 1;
            text-align: center;

        }

        .album-image {
            margin-bottom: 25px;
        }

        .album-image img {
            width: 250px;
            height: 250px;
            object-fit: cover;
            border-radius: 10px;
        }

        .album-info h2 {
            font-size: 15px;
        }

        .album-info p {
            margin: 4px 0;
            font-size: 14px;
        }

        .album-content {
            margin-top: 100px;
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
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <div id="albumPageContainer">
            <div class="album-image">
                <img src="../../album_img/sultan.webp" alt="앨범 이미지">
            </div>

            <div class="album-info">
                <h2>앨범 제목</h2>
                <p><strong>아티스트:</strong> 아티스트 이름</p>
                <p><strong>국적:</strong> 국내 / 해외</p>
                <p><strong>장르:</strong> 장르명</p>
                <p><strong>발매일:</strong> 2025-05-01</p>
            </div>

            <div class="album-content">
                <h3>앨범 소개</h3>
                <p>
                    이 앨범은 아티스트의 새로운 시도와 음악적 실험이 담긴 작품입니다. 다양한 장르를 넘나드는 곡들이 수록되어 있으며, 감성적인 가사와 세련된 사운드가 조화를 이루며 깊은 인상을 남깁니다.이 앨범은 아티스트의 새로운 시도와 음악적 실험이 담긴 작품입니다. 다양한 장르를 넘나드는 곡들이 수록되어 있으며,
                    감성적인 가사와 세련된 사운드가 조화를 이루며 깊은 인상을 남깁니다.이 앨범은 아티스트의 새로운 시도와 음악적 실험이 담긴 작품입니다. 다양한 장르를 넘나드는 곡들이 수록되어 있으며,
                    감성적인 가사와 세련된 사운드가 조화를 이루며 깊은 인상을 남깁니다.이 앨범은 아티스트의 새로운 시도와 음악적 실험이 담긴 작품입니다. 다양한 장르를 넘나드는 곡들이 수록되어 있으며,
                    감성적인 가사와 세련된 사운드가 조화를 이루며 깊은 인상을 남깁니다.
                </p>
            </div>

        </div>
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>
