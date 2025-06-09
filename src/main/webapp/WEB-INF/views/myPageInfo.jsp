<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>

    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/myPageHeader.css"/>">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <title>My Page Info</title>


    <style>
        .container {
            width: 550px;
            margin: auto;
            text-align: left;
            position: relative;
            left: -130px;
        }

        .special-class {
            width: 400px;
            padding: 10px;
            margin-bottom: 7px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        #edit {
            margin-top: -1px;
            border: none;
            cursor: pointer;
            outline: none;
            font-size: 14px;
            margin-right: 310px;
            font-weight: bold;
        }

        .infoLabel {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            font-size: 14px;
            color: gray;
        }

        #loginTitle {
            font-size: 20px;
            margin-bottom: 40px;
            margin-top: 40px;
        }

        span {
            color: gray;
        }

        #roadAddress,
        #jibunAddress,
        #detailAddress {
            width: 400px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        #zip {
            width: 75px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        #zipBtn {
            float: right;
            margin-right: 150px;
        }

        #ad {
            display: flex;
            justify-content: space-between;
        }

        #check {
            margin-bottom: 20px;
            font-size: 14px;
            margin-left: 395px;
            width: 500px;
            display: inline-block;
            font-weight: bold;
        }

        #delete {
            cursor: pointer;
        }

        .oneLine{
            display: flex;
            justify-content: space-between;
        }

        #det{
            font-size: 13px;
            margin-top: 15px;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
            font-size: 14px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 500px;
            height: 340px;
        }


        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        #dropTitle {
            font-size: 18px;
            margin-top: 13px;
        }

        #dropPwd {
            width: 250px;
            height: 30px;
            display: block;
            margin-bottom: 30px;
            font-size: 13px;
        }

        #dropBtn {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            background-color: gray;
            color: whitesmoke;
        }

        #dropOk {
            margin-top: 7px;
            font-size: 13px;
        }


        #dropDiv {
            display: flex;
            justify-content: center;
            font-size: 14px;
            float: left;
            margin-top: 5px;
        }

        #nowPwd {
            margin-top: 40px;
            margin-bottom: 5px;
        }

        #pp {
            margin-bottom: 3px;
        }

        #pp{
            font-size: 12px;
        }

        #top{
            margin-bottom: 150px;
        }

    </style>

</head>

<body>

<header id="top">
    <jsp:include page="header.jsp"/></header><br><br>

<jsp:include page="myPageHeader.jsp"/>

<div id="infoForm">
    <form action="/mypage/info" method="POST" onsubmit="return formCheck()">
        <div class="container">
            <h1 id="loginTitle">개인정보</h1>
<%--            <p id="check-result"></p>--%>
            <label class="infoLabel">이메일*</label>
            <input class="special-class" type="text" id="c_email" name="cEmail" value="${custDto.CEmail}" disabled>
            <label class="infoLabel">닉네임*</label>
            <input class="special-class" type="text" id="cNick" name="cNick" value="${custDto.CNick}" disabled >
            <label class="infoLabel">주소</label>
            <div id="ad">
                <input type="text" id="zip" name="cZip" value="${custDto.CZip}">
                <input type="button" id="zipBtn" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" ></div>
            <input type="text" id="roadAddress" name="cRoadA" value="${custDto.CRoadA}">
            <input type="text" id="jibunAddress" name="cJibunA" maxlength="30" value="${custDto.CJibunA}" placeholder="지번 주소는 선택사항입니다.">
            <span id="guide" style="color:#999;display:none"></span>
            <div class="oneLine"><input type="text" id="detailAddress" name="cDetA" maxlength="30" value="${custDto.CDetA}"><p id="det">건물명 + 상세주소</p></div><br>
        <br><br><br>

            <div id="check">
                <button id="edit">수정</button> <a id="delete" onclick="openModal()">회원탈퇴</a>
            </div>
        </div>
    </form>
</div>


<form id="dropForm" method="POST" action="/mypage/drop" onsubmit="return handleDropSubmit()">
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>

        <h4 id="dropTitle">회원 탈퇴</h4><br>

        <p id="nowPwd">비밀번호</p>
        <input id="dropPwd" name="dropPwd" type="password" placeholder="현재 사용중인 비밀번호를 적어주세요" maxlength="15">

        <p id="pp">회원 탈퇴시 계정 복구가 불가능하며 회원님의 개인 정보는 보관됩니다. 또한 탈퇴한 이메일로는 다시 회원가입이 불가합니다.</p>


        <div id="dropDiv">
            <input type="checkbox" id="agree">
            <label for="agree" id="dropOk">동의</label><br><br>
        </div>

        <button id="dropBtn">탈퇴</button>
    </div><br>
</div>
</form>

<footer>
    <jsp:include page="footer.jsp" flush="false" />
</footer>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

    function isAgreementChecked() {
        return document.getElementById("agree").checked;
    }

    function handleDropSubmit() {
        if (!isAgreementChecked()) {
            alert("회원 탈퇴에 동의하셔야 합니다.");
            return false; // 제출 막기
        }
        return true; // 제출 허용
    }

    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {

                var roadAddr = data.roadAddress;
                var extraRoadAddr = '';

                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraRoadAddr += data.bname;
                }

                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                document.getElementById('zip').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
            }
        }).open();
    }

    function formCheck() {
        if (!checkZip()) return false;
        if (!checkRoadAddress()) return false;
        if (!checkDetailAddress()) return false;

        return confirm("변경사항을 마무리하시고 적용하시겠습니까?");
    }

    // 우편번호: 숫자 1~6자리
    function checkZip() {
        var zip = document.getElementById("zip").value.trim();
        var pattern = /^[0-9]{1,6}$/;
        if (!pattern.test(zip)) {
            alert("우편번호는 숫자 1~6자리로 입력해주세요.");
            return false;
        }
        return true;
    }

    // 도로명 주소: 필수, 한글/영문/숫자/공백/-/ 까지 허용, 1~30자
    function checkRoadAddress() {
        var road = document.getElementById("roadAddress").value.trim();
        var pattern = /^[가-힣a-zA-Z0-9\s\-/]{1,30}$/;
        if (!pattern.test(road)) {
            alert("도로명 주소는 한글/영문/숫자와 공백, -, / 만 허용되며 1~30자까지 가능합니다.");
            return false;
        }
        return true;
    }

    // 상세 주소: 필수, 1~30자
    function checkDetailAddress() {
        var detail = document.getElementById("detailAddress").value.trim();
        if (detail.length < 1 || detail.length > 30) {
            alert("상세 주소는 1자 이상 30자 이하로 입력해주세요.");
            return false;
        }
        return true;
    }



    function test(){
alert("테스트중입니다!")
}

    function openModal() {
        document.getElementById("myModal").style.display = "block";
    }

    // 모달 닫기 함수
    function closeModal() {
        document.getElementById("myModal").style.display = "none";
    }


    let pwdFail = "${pwdFail}"
    if(pwdFail==="pwdMsg") {
        alert("비밀번호가 일치하지 않습니다.")
    }

</script>
</body>
