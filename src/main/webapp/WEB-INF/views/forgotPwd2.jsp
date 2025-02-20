
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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

    #title {
        text-align: center;
        font-size: 20px;
        margin-top: 50px;
        margin-bottom: 60px;
    }
    #pwdLabel, #newPwd{
        display: inline;
    }

    #pwdCheck, #newPwd2{
        display: inline;
    }

    #change{
        margin-top: 30px;
        margin-bottom: 30px;
    }

</style>

<body>

<div id="forgotPwdForm">
    <form action="forgotPwd2" method="post" onsubmit="return">
        <h1 id="title">비밀번호 변경</h1>

        <div id="container">
            <label id="pwdLabel">신규 비밀번호</label>
            <input id="newPwd" class="special-class" type="password" name="cPwd" maxlength="15" placeholder="영문/숫자 조합 4~15자">
            <p id="check-newPwd"></p>

            <label id="pwdCheck">신규 비밀번호 확인</label>
            <input id="newPwd2" class="special-class" type="password" maxlength="15" oninput="newPwdCheck(this.form)">
            <button id="change">변경</button>
        </div>
    </form>
</div>

</body>