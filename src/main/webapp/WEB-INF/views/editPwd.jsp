<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
  <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/myPageHeader.css"/>">

  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>


  <title>pweEdit</title>

  <style>

    .container {
      width: 635px;
      margin: auto;
      text-align: left;
      position: relative;
      left: -90px;
    }

    .special-class {
      width: 400px;
      padding: 10px;
      margin-bottom: 7px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    .pwdLabel {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
      font-size: 14px;
      color: gray;
    }

    #pwdTitle {
      font-size: 20px;
      margin-bottom: 40px;
      margin-top: 40px;
    }

    span {
      color: gray;
    }

    #continue {
      border: none;
      cursor: pointer;
      background-color: white;
      color: black;
      outline: none;
      font-size: 14px;
      font-weight: bold;
      float: right;
      margin-right: 200px;
      margin-top: 60px;
      margin-bottom: 60px;
    }

    #oneLine{
        display: flex;
        justify-content: space-between;
    }

    #pp{
      font-size: 13px;
      margin-top: 15px;
    }
    #check-newPwd{
      font-size: 12px;
      height: 20px;
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

<div id="pwdForm">
    <form action="/mypage/editPwd" method="POST" onsubmit="return editPwdCheck()">
    <div class="container">
      <h1 id="pwdTitle">비밀번호 수정</h1>

        <label class="pwdLabel">현재 비밀번호</label>
        <input id="curPwd" class="special-class" type="password" name="curPwd" maxlength="15">

      <label class="pwdLabel">신규 비밀번호</label>
      <div id="oneLine"><input id="newPwd" class="special-class" type="password" name="cPwd" maxlength="15" oninput="newPwdCheck(this.form)"><p id="pp">영문/숫자 조합 (4자 이상 15자 이하)</p></div>
      <p id="check-newPwd"></p>

      <label class="pwdLabel">신규 비밀번호 확인</label>
      <input id="newPwd2" class="special-class" type="password" maxlength="15" oninput="newPwdCheck(this.form)">
      <button id="continue">변경</button>
    </div>
  </form>
</div><br>

<footer>
  <jsp:include page="footer.jsp" flush="false" />
</footer>


<script>

    let pwdFail = "${pwdFail}"
    if(pwdFail==="pwdMsg") {
      alert("현재 비밀번호가 일치하지 않습니다.")
    }

  function editPwdCheck(){
    var curPwd = document.getElementById("curPwd").value;
    var newPwd = document.getElementById("newPwd").value;
    var newPwd2 = document.getElementById("newPwd2").value;
    var newPwdPattern = /^(?=.*\d)(?=.*[a-z])[a-z0-9]{4,15}$/;


    if(!newPwdPattern.test(newPwd)) {
      alert("비밀번호는 소문자 영문/숫자 조합으로 4자 이상 15자 이하로 작성하셔야합니다.")
      return false;
    }

    if (curPwd === newPwd) {
      alert("현재 비밀번호와 새로운 비밀번호가 일치합니다. 다른 비밀번호를 입력해주세요.");
      return false;
    }

    if(newPwd !== newPwd2){
      alert("신규 비밀번호와 신규 비밀번호 확인이 일치하지 않습니다.")
      return false;
    } else if(newPwd===newPwd2){
      return true;
    }
  }

  function newPwdCheck() {
    var newPwdResult = document.getElementById("check-newPwd");
    var newPwd = document.getElementById("newPwd").value
    var newPwd2 = document.getElementById("newPwd2").value

    if(!newPwd){
      newPwdResult.style.color = "red";
      newPwdResult.innerHTML = "비밀번호를 입력해주세요.";
      return false;
    }else if(!newPwd2){
      newPwdResult.style.color = "red";
      newPwdResult.innerHTML = "비밀번호 확인을 입력해주세요.";
      return false;
    } else if ((newPwd !== newPwd2) || (newPwd2 !== newPwd)) {
      newPwdResult.style.color = "red";
      newPwdResult.innerHTML = "입력하신 비밀번호와 비밀번호 확인이 일치하지 않습니다.";
      return false;
    }else
      newPwdResult.style.color = "green";
    newPwdResult.innerHTML = "비밀번호가 동일합니다.";
    return true;
  }
</script>
</body>