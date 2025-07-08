<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/index.css'/>">
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

    <section id="main_section">
        <div class="miniHeader">
        <h1 class="week">Performance</h1>
        <h1 class="more">
            <a href="<c:url value='/performance/list'/>"> ❯</a>
        </h1>
        </div>

        <div id="god">
            <c:if test="${not empty performanceDtos}">
                <c:forEach var="i" begin="0" end="${fn:length(performanceDtos) - 1}" varStatus="loop">
                    <c:if test="${loop.count <= 4}">
                        <c:set var="performanceDto" value="${performanceDtos[i]}" />
                        <div class="card2">
                            <a href="/performance/read?pno=${performanceDto.pno}">
                                <img src="${performanceDto.img}" alt="포스터 이미지">
                                <div class="container3">
                                    <h4>${performanceDto.artist}</h4>
                                    <p class="sub">${performanceDto.title}</p>
                                    <p class="small">${performanceDto.date}</p>
                                    <p class="small">${performanceDto.venue}</p><br>
                                </div>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>

                <c:if test="${fn:length(performanceDtos) < 4}">
                    <c:forEach begin="1" end="${4 - fn:length(performanceDtos)}" var="i">
                        <div class="card2">
                            <div class="container3" style="text-align:center; padding-top: 150px;">
                                <p style="color:gray;">비어 있음</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </c:if>

            <c:if test="${empty performanceDtos}">
                <c:forEach begin="1" end="4" var="i">
                    <div class="card2">
                        <div class="container3" style="text-align:center; padding-top: 150px;">
                            <p style="color:gray;">비어 있음</p>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

        </div>

<%--        ㅡㅡ--%>


        <div class="album-video-wrap">
    <div class="video-box">
        <div class="container2">
            <h1 class="week">Video</h1><br>

            <div class="mySlides2">
                <a href="https://www.youtube.com/watch?v=B_gKTJTIcYw" target="_blank">
                    <div class="img-container">
                        <img src="../../album_img/balming.jpg" alt="Balming Tiger - Buriburi">
                        <div class="overlay-title">Balming Tiger - Buriburi</div>
                    </div>
                </a>
            </div>

            <div class="mySlides2">
                <a href="https://www.youtube.com/watch?v=JaIMSzE5yLA" target="_blank">
                    <div class="img-container">
                        <img src="../../album_img/nopain.jpg" alt="실리카겔 - NO PAIN">
                        <div class="overlay-title">실리카겔 - NO PAIN</div>
                    </div>
                </a>
            </div>

            <div class="mySlides2">
                <a href="https://www.youtube.com/watch?v=2fDzCWNS3ig" target="_blank">
                    <div class="img-container">
                        <img src="../../album_img/theweekend.jpg" alt="The Weeknd - Out of Time">
                        <div class="overlay-title">The Weeknd - Out of Time</div>
                    </div>
                </a>
            </div>

            <div class="mySlides2">
                <a href="https://www.youtube.com/watch?v=H-OtP2KKtPc" target="_blank">
                    <div class="img-container">
                        <img src="../../album_img/sultan.webp" alt="Sultan of The Disco - Shining Road (Live)">
                        <div class="overlay-title">Sultan of The Disco - Shining Road (Live)</div>
                    </div>
                </a>
            </div>

            <div class="mySlides2">
                <a href="https://www.youtube.com/watch?v=kGAhLNbZ864" target="_blank">
                    <div class="img-container">
                        <img src="../../album_img/driveme.jpg" alt="Lil Yachty - drive ME crazy! (Live)">
                        <div class="overlay-title">Lil Yachty - drive ME crazy! (Live)</div>
                    </div>
                </a>
            </div>


            <div id="move">

            <a class="prev2" onclick="plusSlides2(-1)">❮</a>
            <a class="next2" onclick="plusSlides2(1)">❯</a>

            <div style="text-align:center">
                <span class="dot" onclick="currentSlide2(1)"></span>
                <span class="dot" onclick="currentSlide2(2)"></span>
                <span class="dot" onclick="currentSlide2(3)"></span>
                <span class="dot" onclick="currentSlide2(4)"></span>
                <span class="dot" onclick="currentSlide2(5)"></span>
            </div>
            </div>

        </div>
    </div>


    <div id="cdBox">
        <div class="albumHeader">
        <h1 class="week">Album</h1>
        <h1 class="more">
            <a href="<c:url value='/album/list'/>"> ❯</a>
        </h1>
        </div>
<%--        <ul>--%>
        <ul class="album-list">
            <c:if test="${not empty albumDtos}">
                <c:forEach begin="0" end="5" var="i">
                    <c:choose>
                        <c:when test="${i < fn:length(albumDtos)}">
                            <c:set var="albumDto" value="${albumDtos[i]}" />
                            <li class="one">
                                <a href="/album/read?ano=${albumDto.ano}">
                                    <div class="img-container">
                                        <img src="${albumDto.img}" alt="${albumDto.title}" />
                                        <div class="overlay-title">${albumDto.title}</div>
                                    </div>
                                </a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li class="one">
                            <p style="color: gray; font-size: 14px;">비어 있음</p>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

            <c:if test="${empty albumDtos}">
                <c:forEach begin="0" end="5" var="i">
                    <li class="one">
                        <p style="color: gray; font-size: 14px;">비어 있음</p>
                    </li>
                </c:forEach>
            </c:if>

        </ul>
    </div>
        </div>
    </section>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>

<script>

    let pwdClear = "${pwdClear}"
    if(pwdClear==="pwdMsg") {
        alert("변경하신 비밀번호로 로그인해주세요.")
    }

    let dropClear = "${dropClear}"
    if(dropClear==="pwdMsg") {
        alert("회원탈퇴가 완료되었습니다.")
    }


    // 두번째 슬라이드
    let slideIndex2 = 1;
    showSlides2(slideIndex2);

    function plusSlides2(n) {
        showSlides2(slideIndex2 += n);
    }

    function currentSlide2(n) {
        showSlides2(slideIndex2 = n);
    }

    function showSlides2(n) {
        let i;
        let slides2 = document.getElementsByClassName("mySlides2");
        let dots = document.getElementsByClassName("dot");
        if (n > slides2.length) { slideIndex2 = 1 }
        if (n < 1) { slideIndex2 = slides2.length }
        for (i = 0; i < slides2.length; i++) {
            slides2[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides2[slideIndex2 - 1].style.display = "block";
        dots[slideIndex2 - 1].className += " active";
    }

    // 새로고침시 스크롤 맨위로
    history.scrollRestoration = "manual"
</script>
</body>