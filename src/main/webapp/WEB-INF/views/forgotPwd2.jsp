
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
        margin-bottom: 10px;
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

    #title2{
        margin-bottom: 50px;
    }

</style>

<body>

<div id="forgotPwdForm">
    <form action="forgotPwdClear" method="post" onsubmit="return forgotPwdCheck()">
        <h1 id="title">비밀번호 변경</h1>
        <p id="title2">개인정보 보호를 위해 새로운 비밀번호로 변경한 다음 로그인 해주세요.</p>

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

<script>

    let pwdFail = "${pwdFail}"
    if(pwdFail==="pwdMsg") {
        alert("기존 비밀번호와 같습니다.")
    }

    function forgotPwdCheck() {
        var newPwd = document.getElementById("newPwd").value;
        var newPwd2 = document.getElementById("newPwd2").value;
        var newPwdPattern = /^(?=.*\d)(?=.*[a-z])[a-z0-9]{4,15}$/;


        if (!newPwdPattern.test(newPwd)) {
            alert("비밀번호는 소문자 영문/숫자 조합으로 4자 이상 15자 이하로 작성하셔야합니다.")
            return false;
        }

        if (newPwd !== newPwd2) {
            alert("신규 비밀번호와 신규 비밀번호 확인이 일치하지 않습니다.")
            return false;
        } else if (newPwd === newPwd2) {
            return true;
        }
    }

        function newPwdCheck() {
            var newPwdResult = document.getElementById("check-newPwd");
            var newPwd = document.getElementById("newPwd").value
            var newPwd2 = document.getElementById("newPwd2").value

            if (!newPwd) {
                newPwdResult.style.color = "red";
                newPwdResult.innerHTML = "비밀번호를 입력해주세요.";
                return false;
            } else if (!newPwd2) {
                newPwdResult.style.color = "red";
                newPwdResult.innerHTML = "비밀번호 확인을 입력해주세요.";
                return false;
            } else if ((newPwd !== newPwd2) || (newPwd2 !== newPwd)) {
                newPwdResult.style.color = "red";
                newPwdResult.innerHTML = "입력하신 비밀번호와 비밀번호 확인이 일치하지 않습니다.";
                return false;
            } else
                newPwdResult.style.color = "green";
            newPwdResult.innerHTML = "비밀번호가 동일합니다.";
            return true;
        }

</script>

</body>