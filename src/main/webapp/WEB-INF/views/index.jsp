<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>





<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/index.css'/>">
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">

</head>

<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<div id="content">
    <section id="main_section">
        <div id="king">
            <div class="container1">
                <h1 class="week">Interview</h1><br>

                <div class="mySlides">
                    <a href="#">
                        <img src="/album_img/SilicaGel.avif" style="width:100%" height="325">
                    </a>
                </div>

                <div class="mySlides">
                    <a href="#">
                        <img src="../../album_img/caesar.webp" style="width:100%" height="325">
                    </a>
                </div>

                <div class="mySlides">
                    <a href="#">
                        <img src="/album_img/sultan.webp" style="width:100%" height="325">
                    </a>
                </div>

                <div class="mySlides">
                    <a href="#">
                        <img src="../../album_img/rashad.webp" style="width:100%" height="325">
                    </a>
                </div>

                <div class="mySlides">
                    <a href="#">
                        <img src="../../album_img/newjeans.jpg" style="width:100%" height="325">
                    </a>
                </div>

                <div class="mySlides">
                    <a href="#">
                        <img src="../../album_img/singclair.jpg" style="width:100%" height="325">
                    </a>
                </div>

                <div class="caption-container">
                    <p id="caption"></p>
                </div>

                <div class="row">
                    <div class="column">
                        <img class="demo cursor" src="../../album_img/SilicaGel.avif" style=width:100%
                             onclick="currentSlide(1)" alt="실리카겔">
                    </div>
                    <div class="column">
                        <img class="demo cursor" src="../../album_img/caesar.webp" style="width:100%"
                             onclick="currentSlide(2)" alt="Daniel Caesar">
                    </div>


                    <div class="column">
                        <img class="demo cursor" src="../../album_img/sultan.webp" style="width: 100%;"
                             onclick="currentSlide(3)" alt="Sultan of the Disco">
                    </div>
                    <div class="column">
                        <img class="demo cursor" src="../../album_img/rashad.webp" style="width:100%"
                             onclick="currentSlide(4)" alt="Isaiah Rashad">
                    </div>
                    <div class="column">
                        <img class="demo cursor" src="../../album_img/newjeans.jpg" style="width:100%"
                             onclick="currentSlide(5)" alt="NewJeans">
                    </div>
                    <div class="column">
                        <img class="demo cursor" src="../../album_img/singclair.jpg" style="width:100%"
                             onclick="currentSlide(6)" alt="Dylan sinclair">
                    </div>
                </div>
            </div>



            <div class="container2">
                <h1 class="week">Video</h1><br>

                <div class="mySlides2">
                    <a href="https://www.youtube.com/watch?v=B_gKTJTIcYw" target="_blank">
                        <img width="100%" height="350" src="../../album_img/baming.jpg">
                    </a>
                    <div class="text">Balming Tiger - Buriburi</div>
                </div>

                <div class="mySlides2">
                    <a href="https://www.youtube.com/watch?v=JaIMSzE5yLA" target="_blank">
                        <img width="100%" height="350" src="../../album_img/nopain.jpg">
                    </a>
                    <div class="text">실리카겔 - NO PAIN</div>
                </div>

                <div class="mySlides2">
                    <a href="https://www.youtube.com/watch?v=2fDzCWNS3ig" target="_blank">
                        <img width="100%" height="350" src="../../album_img/theweekend.jpg">
                    </a>
                    <div class="text">The Weeknd - Out of Time</div>
                </div>


                <div class="mySlides2">
                    <a href="https://www.youtube.com/watch?v=H-OtP2KKtPc" target="_blank">
                        <img width="100%" height="350" src="../../album_img/sultan.webp">
                    </a>
                    <div class="text">Sultan of The Disco - Shining Road (Live)</div>
                </div>
                <div class="mySlides2">
                    <a href="https://www.youtube.com/watch?v=kGAhLNbZ864" target="_blank">
                        <img width="100%" height="350" src="../../album_img/driveme.jpg">
                    </a>
                    <div class="text">Lil Yachty - drive ME crazy! (Live)</div>
                </div>

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



        <article class="box">

            <div id="cdBox">
                <h1 class="week">Album</h1>
                <h1 class="more">
                    <a href="<c:url value='/album/list'/>"> ❯</a>
                </h1>
                <ul>
                    <c:forEach begin="0" end="9" var="i">
                        <c:choose>
                            <c:when test="${i < fn:length(albumDtos)}">
                                <c:set var="albumDto" value="${albumDtos[i]}" />

                                <li class="one">
                                    <a href="/album/read?ano=${albumDto.ano}" onclick="moveAll(event, ${i})">
                                        <div>
                                            <img class="albumAll"
                                                 src="${albumDto.img}"
                                                 style="width:100%" height="170" alt="${albumDto.title}" />
                                        </div>
                                        <div class="three">
                                            <p>${albumDto.title}</p>
                                        </div>
                                    </a>
                                </li>

                            </c:when>
                            <c:otherwise>
                                <li class="one">
                                    <div>
                                        <img class="albumAll"
                                             style="width:100%" height="170" alt="No Album" />
                                    </div>
                                    <div class="three">
                                        <p>비어 있음</p>
                                    </div>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </article>


        <h1 class="week">Performance</h1>

        <div id="god">
<%--                    <h1 class="week">Performance</h1><br>--%>
                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/silPerformance.gif">
                                <div class="container3">
                                    <br>
                                    <h4>실리카겔</h4><br>
                                    <p class="sub">POWER ANDRE 99</p><br>
                                    <p class="small">2025.11.10 ~2025.11.12</p>
                                    <p class="small">블루스퀘어 마스터카드홀</p><br>
                                </div>
                            </a>
                        </div>

                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/caesarPerformance.jpg">
                                <div class="container3">
                                    <br>
                                    <h4>Daniel Caesar</h4><br>
                                    <p class="sub">Superpowers World Tour</p><br>
                                    <p class="small">2025.12.11 ~ 2025.12.12</p>
                                    <p class="small">올림픽공원 올림픽홀</p><br>
                                </div>
                            </a>
                        </div>

                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/blackPerformance.jpg">
                                <div class="container3">
                                    <br>
                                    <h4>검정치마</h4><br>
                                    <p class="sub">201 DAYS OF HOLIDAY</p><br>
                                    <p class="small">2025.12.23 ~ 2025.12.31</p>
                                    <p class="small">예스24 라이브홀</p><br>
                                </div>
                            </a>
                    </div>

                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/newboyPerformance.gif">
                                <div class="container3">
                                    <br>
                                    <h4>새소년</h4><br>
                                    <p class="sub">SE SO NEON 2025</p><br>
                                    <p class="small">2025.11.04</p>
                                    <p class="small">올림픽공원 올림픽홀</p><br>
                                </div>
                            </a>
                        </div>
            </div>


    </section>
</div>


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

    // 첫번째 슬라이드
    let slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }
    function currentSlide(n) {
        showSlides(slideIndex = n);
    }
    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("demo");
        let captionText = document.getElementById("caption");
        if (n > slides.length) { slideIndex = 1 }
        if (n < 1) { slideIndex = slides.length }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
        captionText.innerHTML = dots[slideIndex - 1].alt;
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