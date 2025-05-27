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
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <div id="albumPageContainer">
            <div class="album-image">
                <img src="${albumDto.img}" alt="앨범 이미지">
            </div>

            <div class="album-info">
                <p><strong>album:</strong>&nbsp;${albumDto.title}</p>
                <p><strong>artist:</strong>&nbsp;${albumDto.artist}</p>
                <p><strong>type:</strong>&nbsp;${albumDto.type}</p>
                <p><strong>genre:</strong>&nbsp;${albumDto.genre}</p>
                <p><strong>released:</strong>&nbsp;${albumDto.released}</p>
            </div>

            <div class="album-content">
                <h3>앨범 소개</h3>
                <p>${albumDto.content}</p>
            </div>



        </div>
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>
</body>
