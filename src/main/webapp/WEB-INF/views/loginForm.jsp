<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>Green Salt</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

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
            margin: 200px auto 150px;
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

        #forgot{
            cursor: pointer;
        }

        #regi{
            cursor: pointer;
            text-decoration: none;
            color: #000;
        }

        #loginDiv, #adminLoginDiv, #pwdDiv, #adminPwdDiv {
            position: relative;
        }

        #loginImg, #adminLoginImg {
            position: absolute;
            left: 8px;
            top: 40%;
            transform: translateY(-50%);
            height: 22px;
            width: 22px;
            pointer-events: none;
        }


        #pwdImg, #adminPwdImg {
            position: absolute;
            left: 8px;
            top: 35%;
            transform: translateY(-50%);
            height: 23px;
            width: 23px;
            pointer-events: none;
        }

        #cEmail, #cPwd, #adminId, #adminPwd {
            padding-left: 45px;
        }

        #login, #adminLogin {
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

        #title, #adminTitle{
            margin-top: 30px;
            margin-bottom: 30px;
        }

        #emailLabel {
            margin-top: 15px;
            font-weight: 500;
            color: black;
        }

        #tabContainer{
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .tabButton {
            padding: 10px 20px;
            border-radius: 20px;
            border: 1px solid darkgreen;
            background-color: white;
            cursor: pointer;
            font-weight: bold;
            color: darkgreen;
            transition: 0.3s;
        }

        .tabButton.active {
            background-color: darkgreen;
            color: white;
        }
    </style>
</head>

<body>

<header>
    <jsp:include page="header.jsp"/>
</header>

<div id="loginform">

    <div id="tabContainer">
        <button type="button" id="custTab" class="tabButton active" onclick="switchTab('cust')">회원 로그인</button>
        <button type="button" id="adminTab" class="tabButton" onclick="switchTab('admin')">관리자 로그인</button>
    </div>

<%--    회원 로그인폼--%>
    <form id="custLoginForm" action="/login" method="post" onsubmit="return">
        <h1 id="title" >Green Salt</h1>

        <div class="container">
            <div id="loginDiv">
                <input value="${decryptedEmail}" id="cEmail" name="cEmail" class="special-class" type="text" maxlength="30"
                       placeholder="green@salt.com" required>

    <img id="loginImg" src="https://cdn-icons-png.flaticon.com/128/4663/4663997.png">
            </div>

            <div id="pwdDiv">
                <input id="cPwd" class="special-class" type="password" name="cPwd" maxlength="15" placeholder="비밀번호" required>
                <img id="pwdImg" src="https://cdn-icons-png.flaticon.com/128/747/747305.png">
            </div>

            <input type="hidden" name="toURL" value="${param.toURL}">

            <div id="emailRemember">
             <input type="checkbox" id="remember" name="rememberEmail" ${decryptedEmail != null ? "checked" : ""}>
    <label for="remember" id="emailLabel">이메일 저장</label></div><br>

            <button type="submit" id="login">로그인</button>

            <div id="check">
                <a id="forgot" href="/forgotPwd">비밀번호 찾기</a> <a id="regi" href="/register/add">회원가입 </a>
            </div><br>
        </div>
    </form>

    <!-- 관리자 로그인 폼 -->
    <form id="adminLoginForm" action="/adminLogin" method="post" style="display: none;">
        <h1 id="adminTitle">관리자 로그인</h1>

        <div class="container">
            <div id="adminLoginDiv">
                <input id="adminId" name="adminId" class="special-class" type="text" maxlength="30"
                       placeholder="admin" required>
                <img id="adminLoginImg" src="https://cdn-icons-png.flaticon.com/128/4663/4663997.png">
            </div>

            <div id="adminPwdDiv">
                <input id="adminPwd" class="special-class" type="password" name="adminPwd" maxlength="15" placeholder="비밀번호" required>
                <img id="adminPwdImg" src="https://cdn-icons-png.flaticon.com/128/747/747305.png">
            </div><br>

            <button type="submit" id="adminLogin">로그인</button><br>
        </div>
    </form>
</div>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

<script>

    function switchTab(tab) {
        const custForm = document.getElementById("custLoginForm");
        const adminForm = document.getElementById("adminLoginForm");
        const custTab = document.getElementById("custTab");
        const adminTab = document.getElementById("adminTab");

        if (tab === "cust") {
            custForm.style.display = "block";
            adminForm.style.display = "none";
            custTab.classList.add("active");
            adminTab.classList.remove("active");
        } else {
            custForm.style.display = "none";
            adminForm.style.display = "block";
            custTab.classList.remove("active");
            adminTab.classList.add("active");
        }
    }

    let signUpClear = "${signUpClear}"
    if(signUpClear==="msg") {
        alert("회원가입이 되셨습니다.");
    }

    let loginFail = "${loginFail}"
    if(loginFail==="msg") {
        alert("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
    }

</script>

</body>

