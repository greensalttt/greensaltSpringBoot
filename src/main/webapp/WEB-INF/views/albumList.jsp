<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <title>albumList</title>

    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #albumListContainer {
            margin-top: 150px;
            padding: 20px;
            flex: 1;
        }

        #albumListContainer .domestic {
            display: flex;
            align-items: center;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        #albumListContainer .domestic .cate a {
            color: #333;
            text-decoration: none;
            font-weight: bold;
            margin: 0 5px;
        }

        #albumListContainer .list li {
            background-color: white;
            border-radius: 8px;
            display: flex;
            margin-bottom: 10px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s ease;
        }

        #albumListContainer .list img{
            width: 100px;
            height: 100px;
            object-fit: cover;
            flex-shrink: 0;
        }


        #albumListContainer .list .title {
            font-size: 16px;
            font-weight: bold;
        }

        .album-item {
            display: flex;
            align-items: center;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
        }

        .album-item a{
            display: flex;
            align-items: center;
        }

        .album-item .info p {
            margin: 4px 0;
        }
        .info{
            margin-left: 20px;
        }

        .artist{
            font-size: 14px;
        }

        .date{
            font-size: 12px;
        }

    </style>

</head>
<body>
<div id="wrapper">
<header>
    <jsp:include page="header.jsp"/>
</header>
    <div id="albumListContainer">
                <div class="domestic">
     <span class="cate">
			<a href="#">국내 앨범</a>
			<span class="bar">|</span>
			<a href="#">해외 앨범</a>
		</span>
                </div>

        <ul class="list">


            <li class="album-item">
                <a href="/album/page">
                <img src="../../album_img/sultan.webp" class="img" alt="">
                <div class="info">
                    <p class="title">album title</p>
                    <p class="artist">아티스트</p>
                    <p class="date">날짜</p>
                </div>
                </a>
            </li>


                </ul>
        </div>

<footer>
<jsp:include page="footer.jsp"/>
</footer>
    </div>

</body>
