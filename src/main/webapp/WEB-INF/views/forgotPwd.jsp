
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>비밀번호 찾기</title>
    <link rel="icon" type="image/x-icon" href="https://cdn-icons-png.flaticon.com/128/15439/15439306.png">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">


<style>

    body {
        font-size: 11px;
        font-family: "IBM Plex Sans KR", sans-serif;
    }


    #forgotPwdForm {
        max-width: 500px;
        text-align: center;
        border: 3px solid #f1f1f1;
        border-radius: 50px;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        padding-top: 1%;
        padding-bottom: 1%;
        margin: 0 auto;
        margin-top: 200px;
        margin-bottom: 50px;
    }

    #container {
        width: 300px;
        margin: 0 auto;
        text-align: left;
    }

    .special-class {
        width: 100%;
        padding: 10px;
        margin-bottom: 7px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    button {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        background-color: darkgreen;
        color: whitesmoke;
    }

    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
        font-size: 13px;
        color: gray;
    }

    #home{
        text-align: center;
        margin-top: 20px;
        font-size: 30px;
        cursor: pointer;

    }

    #title {
        text-align: center;
        font-size: 12px;
        /*margin-top: 50px;*/
        margin-bottom: 50px;
    }
    #email, #verify{
        display: inline;
    }
    #verify{
        float: right;
        margin-bottom: 5px;
        width: 100px;
    }

    #next{
        margin-top: 30px;
        margin-bottom: 30px;
    }

</style>
</head>

<body>
<%--<header id="top">--%>
<%--    <jsp:include page="header.jsp"/>--%>
<%--</header>--%>

<div id="forgotPwdForm">
    <form action="forgotPwd">
<%--        <h1 id="home"><a href="<c:url value='/'/>">Green Salt</a></h1>--%>
    <h1 id="home" onclick="window.location.href='/'">Green Salt</h1>

    <h2 id="title">비밀번호를 찾고자 하는 이메일을 적어주세요</h2>

        <div id="container">
        <p id="check-result"></p>
        <label id="email">이메일</label>
        <input id="verify" type="button" value="인증번호 받기" disabled>
        <input class="special-class" type="text" id="cEmail" name="cEmail" maxlength="30" onblur="emailCheck()" placeholder="green@salt.com">
        <p id="mail-check-warn"></p>
        <label>인증번호</label>
        <input class="special-class" type="text" id="emailCode" name="emailCode" maxlength="10" disabled>
      <button type="button" id="next" onclick="window.location.href='/forgotPwd2'">다음</button>

        </div>
    </form>
</div>
</body>

