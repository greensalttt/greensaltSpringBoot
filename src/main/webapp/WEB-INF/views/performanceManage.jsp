<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <title>performanceManage</title>

    <style>
        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #performanceListContainer {
            width: 70%;
            margin: 100px auto 100px auto;
            flex: 1;
        }

        .list {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-top: 30px;
        }

        .performance-card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            display: flex;
            flex-direction: column;
            text-align: left;
        }

        .performance-card img {
            width: 100%;
            height: 400px;
            object-fit: cover;
        }

        .performance-card .info {
            padding: 10px;
        }

        .artist {
            font-size: 16px;
            font-weight: bold;
            color: black;
        }

        .title {
            color: grey;
            font-size: 14px;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .date, .venue {
            font-size: 12px;
            color: #666;
        }

        .performance-actions {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border-top: 1px solid #eee;
        }

        .performance-actions button {
            padding: 5px 10px;
            font-size: 12px;
            cursor: pointer;
            background-color: #f4f4f4;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .performance-actions form {
            display: inline;
            margin: 0;
        }

        #performanceListContainer .domestic {
            display: flex;
            align-items: center;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        #performanceListContainer .domestic .cate a {
            color: #333;
            text-decoration: none;
            font-weight: bold;
            margin: 0 5px;
        }

        #noPerformance{
            margin-top: 100px;
            margin-bottom: 120px;
        }


    </style>

</head>
<body>
<div id="wrapper">
    <div id="performanceListContainer">
        <div class="domestic">
     <span class="cate">
			<a href="/admin/page">관리자 메인 페이지</a>
		</span>
        </div>
        <div class="list">
            <c:if test="${empty performanceDtos}">
                <div id="noPerformance"> 등록된 공연이 없습니다. </div>
            </c:if>
            <c:forEach var="performanceDto" items="${performanceDtos}">
                <div class="performance-card">
                        <img src="${performanceDto.img}" alt="${performanceDto.title}" />
                        <div class="info">
                            <p class="artist">${performanceDto.artist}</p>
                            <p class="title">${performanceDto.title}</p>
                            <p class="date">Date: ${performanceDto.date}</p>
                            <p class="venue">${performanceDto.venue}</p>
                        </div>
                    <div class="performance-actions">
                        <button onclick="location.href='/admin/performance_edit?pno=${performanceDto.pno}'">수정</button>
                        <form action="/admin/performance_remove" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                            <input type="hidden" name="pno" value="${performanceDto.pno}" />
                            <button type="submit">삭제</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>

<script>

    let performanceRemoveFail = "${performanceRemoveFail}"
    if(performanceRemoveFail==="msg") {
        alert("공연 삭제가 실패했습니다.")
    }

    let performanceRemove = "${performanceRemove}"
    if(performanceRemove==="msg") {
        alert("공연 삭제가 완료되었습니다.")
    }

    let performanceModify = "${performanceModify}"
    if(performanceModify==="msg") {
        alert("공연 수정에 성공했습니다.")
    }

    let testAid = "${testAid}";
    if (testAid === "msg") {
        alert("테스트 아이디는 삭제할 수 없습니다.");
    }


</script>

