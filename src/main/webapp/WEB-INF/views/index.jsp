<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="<c:url value="https://cdn-icons-png.flaticon.com/128/15439/15439306.png"/>">


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
                <h1 class="week">CD</h1>
                <h1 class="more"><a
                        href="https://music.apple.com/us/playlist/top-100-global/pl.d25f5d1181894928af76c85c967f8f31"
                        target="_blank"> ❯</a></h1>
                <ul>
                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 0)"
                                     src="../../album_img/SilicaGelalbum.webp" style="width:100%" height="170" />
                            </div>
                            <div class="three">
                                <p>실리카겔</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 1)" src="../../album_img/orange.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>Channel ORANGE</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 5)" src="../../album_img/teambaby.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>TEAM BABY</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 3)"
                                     src="../../album_img/Let's_Start_Here.jpg" style="width:100%" height="170" />
                            </div>
                            <div class="three">
                                <p>Let's Start Here</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 4)" src="../../album_img/magic.jpg"
                                     style="width:100%" height="170" />
                            </div>
                            <div class="three">
                                <p>Magic 8Ball</p>
                            </div>
                        </a>
                    </li><br>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 9)" src="../../album_img/never.png"
                                     style="width:100%" height="170" />
                            </div>
                            <div class="three">
                                <p>Never Enough</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 6)" src="../../album_img/choiyuri.jpg"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>유영</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 7)" src="../../album_img/thirsty.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>THIRSTY</p>
                            </div>
                        </a>
                    </li>

                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 8)" src="../../album_img/proverb.jpg"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>Proverb</p>
                            </div>
                        </a>
                    </li>



                    <li class="one">
                        <a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 2)" src="../../album_img/omg.jpg"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>OMG</p>
                            </div>
                        </a>
                    </li>


                </ul>
            </div>
        </article>

        <article class="box">
            <div id="vinylBox">
                <h1 class="week">Vinyl</h1>
                <h1 class="more"><a href="https://www.melon.com/chart/index.htm" target="_blank"> ❯</a></h1>

                <ul>
                    <li class="one"><a href="#">
                        <div>
                            <img class="albumAll" onclick="moveAll(event, 10)" src="../../album_img/dawnfm.jpg"
                                 style="width:100%" height="170" />
                        </div>

                        <div class="three">
                            <p>Dawn FM</p>
                        </div></a>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 11)" src="../../album_img/teambaby.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>TEAM BABY</p>
                            </div>
                        </a></li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 12)" src="../../album_img/love.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>사랑으로</p>
                            </div>
                        </a></li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 13)" src="../../album_img/choiyuri.jpg"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>유영</p>
                            </div>
                        </a></li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 14)" src="../../album_img/teen.jpeg"
                                     style="width:100%" height="170" />
                            </div>
                            <div class="three">
                                <p>TEEN TROUBLES</p>
                            </div>
                        </a></li><br>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 15)" src="../../album_img/thirsty.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>THIRSTY</p>
                            </div>
                        </a>
                        </li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 16)" src="../../album_img/starboy.webp"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>Starboy</p>
                            </div>
                        </a>
                        </li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 17)" src="../../album_img/formyangel.jpg"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>For My Angel</p>
                            </div>
                        </a>
                        </li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 18)"
                                     src="../../album_img/flaws%20and%20all.png" style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>Flaws and All</p>
                            </div>
                        </a>
                        </li>

                        <li class="one"><a href="#">
                            <div>
                                <img class="albumAll" onclick="moveAll(event, 19)" src="../../album_img/nwjns.jpg"
                                     style="width:100%" height="170" />
                            </div>

                            <div class="three">
                                <p>New Jeans</p>
                            </div>
                        </a>
                        </li>
                </ul>
            </div>

            <div id="god">
                <div class="row2">
                    <h1 class="week">Performance</h1><br>
                    <div class="column2">
                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/silPerformance.gif" style="width:100%" height="320px">
                                <div class="container3">
                                    <br>
                                    <h4>실리카겔</h4><br>
                                    <p class="sub">POWER ANDRE 99</p><br>
                                    <p class="small">2023.11.10 ~2023.11.12</p>
                                    <p class="small">블루스퀘어 마스터카드홀</p><br>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="column2">
                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/caesarPerformance.jpg" style="width:100%" height="320px">
                                <div class="container3">
                                    <br>
                                    <h4>Daniel Caesar</h4><br>
                                    <p class="sub">Superpowers World Tour</p><br>
                                    <p class="small">2023.12.11 ~ 2023.12.12</p>
                                    <p class="small">올림픽공원 올림픽홀</p><br>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="column2">
                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/blackPerformance.jpg" style="width:100%" height="320px">
                                <div class="container3">
                                    <br>
                                    <h4>검정치마</h4><br>
                                    <p class="sub">201 DAYS OF HOLIDAY</p><br>
                                    <p class="small">2023.12.23 ~ 2023.12.31</p>
                                    <p class="small">예스24 라이브홀</p><br>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="column2">
                        <div class="card2">
                            <a href="#" onclick="stop()">
                                <img src="../../album_img/newboyPerformance.gif" style="width:100%" height="320px">
                                <div class="container3">
                                    <br>
                                    <h4>새소년</h4><br>
                                    <p class="sub">SE SO NEON 2023</p><br>
                                    <p class="small">2023.11.04</p>
                                    <p class="small">올림픽공원 올림픽홀</p><br>
                                </div>
                            </a>
                        </div>
                    </div>

                </div>

                <div class="cartBox">
                    <ul>
                        <h4><a class="week" href="#">History</a></h4><br>
                        <li class="new">
                            <a href="#"></a>
                        </li>

                        <li class="new">
                            <a href="#"></a>
                        </li>

                        <li class="new">
                            <a href="#"></a>
                        </li>

                        <li class="new">
                            <a href="#"></a>
                        </li>
                    </ul>
                </div>
            </div>

        </article>
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

    // 히스토리
    const albumAll = document.getElementsByClassName("albumAll");
    const boxMore = document.getElementsByClassName("new");

    let newAlbumAll = [];

    // 페이지 로드 시 로컬 스토리지에서 앨범 불러오기
    document.addEventListener("DOMContentLoaded", () => {
        const storedData = JSON.parse(localStorage.getItem("albums"));
        if (storedData) {
            const currentTime = new Date().getTime();
            const oneDay = 24 * 60 * 60 * 1000; // 24시간

            newAlbumAll = storedData.filter(album => currentTime - album.timestamp < oneDay)
                .map(album => {
                    const img = document.createElement("img");
                    img.src = album.src;
                    img.style.width = "130px";
                    img.style.height = "100px";
                    return { element: img, timestamp: album.timestamp };
                });
            updateAlbumsDisplay();
            saveAlbumsToLocalStorage();
        }
    });

    function moveAll(event, index) {
        event.preventDefault();
        const albumPick = albumAll[index].cloneNode();
        albumPick.style.width = "130px";
        albumPick.style.height = "100px";

        // 선택한 앨범이 이미 박스에 있는지 확인
        const tamjung = newAlbumAll.findIndex((event) => event.element.src === albumPick.src);
        // 중복 선택일 경우 기존 박스에서 제거
        if (tamjung !== -1) {
            newAlbumAll.splice(tamjung, 1);
        }
        // 클릭한 앨범을 배열의 첫 번째에 추가
        newAlbumAll.unshift({ element: albumPick, timestamp: new Date().getTime() });

        // 디스플레이 업데이트 및 로컬 스토리지에 저장
        updateAlbumsDisplay();
        saveAlbumsToLocalStorage();
    }

    function updateAlbumsDisplay() {
        // boxMore 요소(앨범을 표시할 박스들)의 개수만큼 반복
        for (let i = 0; i < boxMore.length; i++) {
            // 박스 초기화
            boxMore[i].innerHTML = "";

            // 현재 인덱스가 newAlbumAll 배열의 길이보다 작으면
            if (i < newAlbumAll.length) {
                // 박스에 newAlbumAll 배열의 앨범 이미지를 추가
                boxMore[i].appendChild(newAlbumAll[i].element);
            }
        }
    }

    function saveAlbumsToLocalStorage() {
        // newAlbumAll 배열의 각 앨범 객체를 순회하면서
        // 앨범의 src(이미지 소스)와 timestamp(타임스탬프)를 추출하여
        // 새로운 객체 배열(albumDataArray)을 생성
        const albumDataArray = newAlbumAll.map(album => ({
            src: album.element.src,
            timestamp: album.timestamp
        }));

        // albumDataArray 배열을 JSON 문자열로 변환하여
        // 로컬 스토리지에 albums 키로 저장
        localStorage.setItem("albums", JSON.stringify(albumDataArray));
    }

    // 검색창 엔터키 호환
    function handleKeyPress(event) {
        // Enter 키의 키코드는 13
        if (event.keyCode === 13) {
            // 엔터 키를 눌렀을 때 검색 함수 호출
            var searchValue = document.getElementById('search').value;
            alert('검색어: ' + searchValue);
        }
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