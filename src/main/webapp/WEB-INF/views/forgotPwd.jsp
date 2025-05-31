
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
        font-size: 20px;
        cursor: pointer;

    }

    #title {
        text-align: center;
        font-size: 12px;
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
<div id="forgotPwdForm">
    <form action="/forgotPwd2" method="POST">
 <h1 id="home" onclick="window.location.href='/'">비밀번호 찾기</h1>

    <h2 id="title">비밀번호를 찾고자 하는 이메일을 적어주세요</h2>

        <div id="container">
        <p id="check-result"></p>
        <label id="email" >이메일</label>
        <input id="verify" type="button" value="인증번호 받기" disabled>
        <input class="special-class" type="text" id="cEmail" name="cEmail" maxlength="30" onblur="emailCheck()" placeholder="green@salt.com">
        <p id="mail-check-warn"></p>
        <label>인증번호</label>
        <input class="special-class" type="text" id="emailCode" name="emailCode" maxlength="10" disabled>
      <button id="next" disabled>다음</button>
        </div>
    </form>
</div>

<script>
    $('#cEmail').on('input', function() {
        emailCheck();
    });

    $('#emailCode').on('input', function() {
        verifyNumber();
    });

    /*이메일 중복체크*/

    let debounceTimer;
    function emailCheck() {
        const email = document.getElementById("cEmail").value;
        const checkResult = document.getElementById("check-result");
        const verifyButton = $('#verify');

        clearTimeout(debounceTimer); // 기존 타이머 초기화
        debounceTimer = setTimeout(() => {
            if (!email.trim()) {
                checkResult.style.color = "red";
                checkResult.innerHTML = "이메일을 입력해주세요.";
                verifyButton.prop('disabled', true); // 버튼 비활성화
                return;
            }

            var emailPattern = /^((?![가-힣]).)*([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            if (!emailPattern.test(email)) {
                checkResult.style.color = "red";
                checkResult.innerHTML = "이메일 형식을 다시 확인해주세요.";
                verifyButton.prop('disabled', true); // 버튼 비활성화
                return;
            }

            $.ajax({
                type: "post",
                url: "/emailCheck",
                data: {
                    "cEmail": email
                },
                success: function (emailCode) {
                    console.log("요청성공", emailCode);
                    if (emailCode == "ok") {
                        checkResult.style.color = "green";
                        checkResult.innerHTML = "인증번호를 받기를 진행해주세요.";
                        verifyButton.prop('disabled', false); // 버튼 활성화
                    }else if (emailCode == "deleted"){
                        checkResult.style.color = "red";
                        checkResult.innerHTML = "탈퇴 처리된 이메일입니다.";
                        verifyButton.prop('disabled', true); // 버튼 비활성화
                    }else {
                        checkResult.style.color = "red";
                        checkResult.innerHTML = "이미 사용중인 이메일입니다.";
                        verifyButton.prop('disabled', true); // 버튼 비활성화
                    }
                },
                error: function (err) {
                    console.log("에러발생", err);
                }
            });
        }, 300); // 300ms 대기 후 실행
    }



    $('#verify').click(function() {
        const $this = $(this); // 클릭된 버튼을 jQuery 객체로 저장
        $this.prop('disabled', true); // 버튼을 비활성화

        alert('인증번호가 전송되었습니다.'); // 인증번호 전송 알림

        const email = $('#cEmail').val(); // 이메일 주소값 얻어오기
        console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
        const checkInput = $('#emailCode') // 인증번호 입력하는곳
        const url = '/verifyEmail?email=' + email; // URL 생성
        $.ajax({
            type: 'GET',  // 클라이언트에서 서버로 인증번호 요청
            url: url, // 생성한 URL 사용
            success: function(data) {
                console.log("data : " + data);
                checkInput.attr('disabled', false);
                code = data;
                setTimeout(function() {
                    $this.prop('disabled', false);
                }, 7000);
            }
        });
    });

    function verifyNumber() {
        const inputCode = $('#emailCode').val();
        const $resultMsg = $('#mail-check-warn');

        if (inputCode === code) {
            $resultMsg.html('인증번호가 일치합니다.');
            $resultMsg.css('color', 'green');
            $('#verify').attr('disabled', true);
            $('#cEmail').attr('readonly', true);
            $('#next').attr('disabled', false);
            return true;
        } else {
            $resultMsg.html('인증번호를 다시 확인해주세요');
            $resultMsg.css('color', 'red');
            $('#next').attr('disabled', true);
            return false;
        }
    }

</script>
</body>

