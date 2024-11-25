<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>Green Salt</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<%--    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">--%>
<%--    <link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>">--%>


    <link rel="stylesheet" href="<c:url value='/css/index.css'/>">
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <style>
        #loginform {
            max-width: 500px;
            max-height: 500px;
            text-align: center;
            border: 3px solid #f1f1f1;
            border-radius: 50px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            padding-top: 2%;
            padding-bottom: 2%;
            margin: 0 auto;
            margin-top: 250px;
        }

        .container {
            width: 400px;
            margin: 0 auto;
            text-align: left;
        }


        .special-class {
            width: 100%;
            height: 50px;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }


        .subBtn {
            width: 49%;
            margin-bottom: 10px;
        }

        label {
            display: block;
            margin-bottom: 15px;
            font-weight: bold;
            color: gray;
            font-size: 13px;
        }

        #check {
            margin-bottom: 15px;
            font-size: 13px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        #emailRemember {
            display: flex;
            justify-content: center;
            font-size: 12px;
            float: right;
            margin-top: -15px;
        }

        .buttonContainer {
            display: flex;
            justify-content: space-between;
            max-width: 400px;
            margin: auto;
        }

        .subBtn {
            display: flex;
            align-items: center;
            justify-content: center;
            color: black;
            border: none;
            cursor: pointer;
            padding: 5px 10px;
        }

        #forgot{
            cursor: pointer;
        }

        #regi{
            cursor: pointer;
            text-decoration: none;
            color: #000;
        }

        #loginDiv {
            position: relative;
        }

        #loginImg {
            position: absolute;
            left: 8px;
            top: 40%;
            transform: translateY(-50%);
            height: 22px;
            width: 22px;
            pointer-events: none;
        }


        #pwdDiv {
            position: relative;
        }

        #pwdImg {
            position: absolute;
            left: 8px;
            top: 35%;
            transform: translateY(-50%);
            height: 23px;
            width: 23px;
            pointer-events: none;
        }

        #cEmail, #cPwd {
            padding-left: 45px;
        }

        #login {
            position: relative;
            width: 100%;
            height: 50px;
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            background-color: darkgreen;
            color: whitesmoke;
            border: none;
            font-size: 15px;
            text-align: center;
            -webkit-transition-duration: 0.2s;
            transition-duration: 0.2s;
            text-decoration: none;
            overflow: hidden;
            cursor: pointer;

        }

        #login:hover {
            background-color: seagreen; /* 마우스를 갖다 대면 배경색 변경 */
        }

        #login:after {
            content: "";
            background: darkgreen;
            display: block;
            position: absolute;
            padding-top: 300%;
            padding-left: 350%;
            margin-left: -20px !important;
            margin-top: -120%;
            opacity: 0;
            transition: all 1.5s
        }

        #login:active:after {
            padding: 0;
            margin: 0;
            opacity: 1;
            transition: 0s
        }

        #logo{
            margin-top: 30px;
        }

        #emailLabel {
            margin-top: 15px;
            font-weight: 500;
            color: black;
        }
    </style>
</head>

<body>

<header>
    <jsp:include page="header.jsp"/>
</header>

<div id="loginform">
    <form action="<c:url value='/login'/>" method="post" onsubmit="return">
        <h1 id="logo">Green Salt</h1><br><br><br>

        <div class="container">

            <div id="loginDiv">
                <input value="${cookie.cEmail.value}" id="cEmail" name="cEmail" class="special-class" type="text" maxlength="30"
                       placeholder="green@salt.com" required>
                <img id="loginImg" src="https://cdn-icons-png.flaticon.com/128/4663/4663997.png">
            </div>

            <div id="pwdDiv">
                <input id="cPwd" class="special-class" type="password" name="cPwd" maxlength="15" placeholder="비밀번호" required>
                <img id="pwdImg" src="https://cdn-icons-png.flaticon.com/128/747/747305.png">
            </div>

            <input type="hidden" name="toURL" value="${param.toURL}">

            <div id="emailRemember">
                <input type="checkbox" id="remember" name="rememberEmail"  ${empty cookie.cEmail.value ? "":"checked"}>
                <label for="remember" id="emailLabel">이메일 저장</label></div><br>

            <button type="submit" id="login">로그인</button>

            <div id="check">
                <a id="forgot" onclick="test()" >비밀번호 찾기</a> <a id="regi" href="/register/add">회원가입 </a>
            </div><br>

            <div class="buttonContainer">
                <div class="subBtn" id="googleBtn" onclick="test()"> <img id="google" src="https://cdn-icons-png.flaticon.com/128/300/300221.png" width="40" height="40"></div>
                <div class="subBtn" id="kakaoBtn" onclick="test()"> <img id="kakao" src="https://cdn-icons-png.flaticon.com/128/3669/3669973.png" width="40" height="40"></div>
                <div class="subBtn" id="appleBtn" onclick="test()"> <img id="apple" src="https://cdn-icons-png.flaticon.com/128/0/747.png" width="40" height="40"></div>
            </div>

        </div>

    </form>
</div><br><br><br>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

<script>
    let signUpClear = "${signUpClear}"
    if(signUpClear==="msg") {
        alert("회원가입이 되셨습니다.");
    }

    let loginFail = "${loginFail}"
    if(loginFail==="msg") {
        alert("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
    }

    let pwdClear = "${pwdClear}"
    if(pwdClear==="pwdMsg2") {
        alert("비밀번호 변경이 완료됐습니다. 변경된 비밀번호로 다시 로그인해주세요.")
    }

    function test(){
        alert("테스트중입니다!")
    }
</script>

</body>
