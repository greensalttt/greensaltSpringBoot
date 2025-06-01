
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>회원가입</title>
     <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">




    <style>
            body {
                font-size: 11px;
                font-family: "IBM Plex Sans KR", sans-serif;
            }

            .container {
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

            #loginTitle {
                text-align: center;
                font-size: 20px;
                margin-bottom: 40px;
            }

            #myform {
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

            span {
                color: gray;
            }

            #myModal2 {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4);
            }


            .modal-content {
                background-color: #fefefe;
                margin: 15% auto 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 50%;
                box-sizing: border-box;
            }

            #piiModal {
                color: black;
                text-decoration: underline;
                cursor: pointer;
            }


            #close2 {
                font-size: 30px;
                float: right;
                cursor: pointer;
            }


            #piiBox, #piiModal {
                display: inline;
            }

            #roadAddress,
            #jibunAddress,
            #detailAddress {
                width: 100%;
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
                width: 100px;
            }


            #email, #verify{
                display: inline;
            }
            #verify{
                float: right;
                margin-bottom: 5px;
                width: 100px;
            }

            #nickName{
                display: inline;
            }


            #piiBox, #piiLabel{
                display: inline;
            }

            #piiLabel{
                font-size: 11px;
                cursor: pointer;
            }

            #ad {
                display: flex;
                justify-content: space-between;
            }


        </style>
    </head>

    <body>
    <header id="top">
        <jsp:include page="header.jsp"/>
    </header>

    <div id="myform">
        <form action="/register/add" method="POST" onsubmit="return formCheck(this)">
            <h1 id="loginTitle">회원가입</h1>
            <div class="container">
                <p id="check-result"></p>
                <label id="email">이메일</label>
                <input id="verify" type="button" value="인증번호 받기" disabled>
                <input class="special-class" type="text" id="cEmail" name="cEmail" maxlength="30" onblur="emailCheck()" placeholder="green@salt.com">
                <p id="mail-check-warn"></p>
                <label>인증번호</label>
                <input class="special-class" type="text" id="emailCode" name="emailCode" maxlength="10" disabled>
                <label>비밀번호</label>
                <input class="special-class" type="password" id="cPwd" name="cPwd" placeholder="영문/숫자 조합 (4자 이상 15자 이하)" maxlength="15" oninput="pwd2Check(this.form)" disabled>
                <p id="check-pwd"></p>
                <label id="pwdCheck">비밀번호 확인</label>
                <input class="special-class" type="password" id="cPwd2" name="cPwd2" placeholder="비밀번호를 다시 한번 입력해주세요." maxlength="15" oninput="pwd2Check(this.form)" disabled><br>
                <p id="nickCheck-result"></p>
                <label id="nickName">닉네임 확인</label>
                <input class="special-class" type="text" id="cNick" name="cNick" placeholder="2자 이상 10자 이하" maxlength="10" onblur="nickCheck()" disabled>
                <label>주소</label>
                <div id="ad">
                    <input type="text" id="zip" name="cZip" placeholder="우편번호" maxlength="6" disabled>
                    <input type="button" id="zipBtn" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"  disabled></div>
                <input type="text" id="roadAddress" name="cRoadA" placeholder="도로명주소"  disabled>
                <input type="text" id="jibunAddress" name="cJibunA" maxlength="30" placeholder="지번주소는 선택사항입니다." disabled>
                <span id="guide" style="color:#999;display:none"></span>
                <input type="text" id="detailAddress" name="cDetA" maxlength="30" placeholder="건물명+상세주소" disabled><br><br>
                <input type="checkbox" id="piiBox" onclick="openModal2()" name="piiBox" value="Y" disabled>
                <label for="piiBox" id="piiLabel">[필수] 개인정보 및 이용</label><br><br><br>

                <button id="rBtn" disabled>가입하기</button>
            </div>
        </form>
    </div>

    <div id="myModal2">
        <div class="modal-content">
            <h3>그린솔트 개인정보 수집 및 이용</h3><br>

            <p>개인정보취급방침을 통하여 고객님께서 제공하는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.</p><br>

            <p>1. 수집하는 개인정보 항목</p>

            <p>* 회사는 회원가입, 상담, 서비스 신청 등을 위해 아래와 같은 개인정보를 수집하고 있습니다.</p>
            <p>회원가입시 : 이름, 이메일(로그인 ID), 주소, 비밀번호</p>
            <p>* 서비스 이용 과정이나 사업처리 과정에서 서비스이용기록, 접속로그, 쿠키, 접속IP이 생성되어 수집될 수 있습니다.</p><br>

            <p>2. 개인정보의 수집 및 이용목적</p>

            <p>회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.</p>
            <p>수신 동의한 SMS 혹은 이메일로 알림 발송</p>

            <p>3. 개인정보의 보유 및 방법</p>
            <p>회원님이 회원가입에서 입력하신 비밀번호는 암호화 처리된 후 DB에서 관리하고 있습니다.</p><br>

            <p>4. 이용자의 권리</p>
            <p> * 이용자는 언제든지 등록되어 있는 자신의 개인정보를 조회하거나 수정할 수 있으며, 탈퇴를 요청할 수도 있습니다. <span id="close2" onclick="closeModal2()">&times</span></p>
            <br>

        </div>
    </div>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>

    <script>
        $('#cEmail').on('input', function() {
            emailCheck();
        });

        $('#emailCode').on('input', function() {
            verifyNumber();
        });

        $('#cNick').on('input', function() {
            nickCheck();
        });
        /*이메일 중복체크*/

        function emailCheck() {
            const email = document.getElementById("cEmail").value;
            const checkResult = document.getElementById("check-result");
            const verifyButton = $('#verify');

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

            console.log("입력한 이메일", email);
            $.ajax({
                type: "post",
                url: "/register/emailCheck",
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
        }

        function verifyEmail() {
            document.getElementById('emailCode').disabled = false;
            document.getElementById('cPwd').disabled = false;
            document.getElementById('cPwd2').disabled = false;
            document.getElementById('cNick').disabled = false;
            document.getElementById('zip').disabled = false;
            document.querySelector('[onclick="sample4_execDaumPostcode()"]').disabled = false;
            document.getElementById('roadAddress').disabled = false;
            document.getElementById('jibunAddress').disabled = false;
            document.getElementById('detailAddress').disabled = false;
            document.getElementById('piiBox').disabled = false;
            document.getElementById('rBtn').disabled = false;
        }


        $('#verify').click(function() {
            const $this = $(this); // 클릭된 버튼을 jQuery 객체로 저장
            $this.prop('disabled', true); // 버튼을 비활성화

            alert('인증번호가 전송되었습니다.'); // 인증번호 전송 알림

            const email = $('#cEmail').val(); // 이메일 주소값 얻어오기
            console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
            const checkInput = $('#emailCode') // 인증번호 입력하는곳
            const url = '/register/verifyEmail?email=' + email; // URL 생성
            $.ajax({
                type: 'GET',  // 클라이언트에서 서버로 인증번호 요청
                url: url, // 생성한 URL 사용
                success: function(data) {
                    console.log("data : " + data);
                    checkInput.attr('disabled', false);
                    code = data;
                    verifyEmail();
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
                return true;
            } else {
                $resultMsg.html('인증번호를 다시 확인해주세요');
                $resultMsg.css('color', 'red');
                return false;
            }
        }

        /*3. 회원가입 유효성 검사*/
        function formCheck(frm) {
            var isEmailFormat = emailFormatCheck(frm);
            var isPwd = pwdCheck(frm);
            var isPwd2 = pwd2Check(frm);
            var isNm = nickNameCheck(frm);
            var isZip =zipCheck(frm)
            var isRoad =roadCheck(frm)
            var isDetail =detailCheck(frm)
            var isPii = piiCheck(frm)
            var isCodeVerified = vNum(frm);

            var pwd = frm.cPwd.value;
            var pwd2 = frm.cPwd2.value;
            var nm = frm.cNick.value;
            var zip = frm.cZip.value;
            var road = frm.cRoadA.value;
            var det = frm.cDetA.value;

            if (!isEmailFormat) {
                return false;
            } else if (!isCodeVerified) {
                return false;
            } else if (!pwd) {
                alert('비밀번호를 입력해주세요.');
                return false;
            } else if (!isPwd) {
                alert('비밀번호는 소문자 영문/숫자 조합으로 4자 이상 15자 이하로 설정하셔야합니다.');
                return false;
            } else if (!pwd2) {
                alert('비밀번호 확인을 입력해주세요');
                return false;
            } else if (!isPwd2) {
                alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
                return false;
            } else if (!nm) {
                alert('닉네임을을 입력해주세요.');
                return false;
            } else if (!isNm) {
                alert("닉네임은 특수문자 제외 2~10글자로 설정해주세요.");
                return false;
            } else if (!zip) {
                alert('우편번호를 입력해주세요.');
                return false;
            } else if (!isZip) {
                return false;
            }  else if (!road) {
                alert('도로명주소를 입력해주세요.');
                return false;
            } else if (!isRoad) {
                return false;
            }  else if (!det) {
                alert('상세주소를 입력해주세요.');
                return false;
            } else if (!isDetail) {
                return false;
            }
            else if (!isPii) {
                alert("회원가입을 하실려면 개인정보 동의를 하셔야합니다.");
                return false;
            }

            if (document.getElementById("check-result").innerText.includes("이미 사용중인 이메일입니다.")) {
                alert("중복된 이메일 주소입니다. 다른 이메일 주소를 입력하세요.");
                return false;
            }

            return true;
        }

        /*3-1 이메일 형식 유효성 검사*/

        function emailFormatCheck(frm) {
            var email = frm.cEmail.value.trim(); // 공백 제거한 이메일
            var emailPattern = /^((?![가-힣]).)*([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

            if (!email) {
                alert("이메일을 입력해주세요.");
                return false;
            } else if (!emailPattern.test(email)) {
                alert("이메일 양식을 다시 확인해주세요.");
                return false;
            }
            return true;
        }

        function vNum(frm){
            var vNum = frm.emailCode.value.trim();
            if(!vNum){
                alert("이메일 인증을 진행해주세요.")
                return false;
            }
            if (document.getElementById("mail-check-warn").innerText.includes("인증번호를 다시 확인해주세요")) {
                alert("인증번호가 틀렸습니다. 다시 확인해주세요.");
                return false;
            }
            return true;
        }

        /*3-2 비밀번호 유효성 검사*/

        function pwdCheck(frm) {
            var pwd = frm.cPwd.value;
            var pwdPattern = /^(?=.*\d)(?=.*[a-z])[a-z0-9]{4,15}$/;
            if (!pwdPattern.test(pwd)) {
                return false;
            }
            return true;
        }

        /* 비밀번호 확인 유효성 검사 */
        function pwd2Check(frm) {
            var pwd = frm.cPwd.value;
            var pwd2 = frm.cPwd2.value;
            var pwdResult = document.getElementById("check-pwd")

            if(!pwd){
                pwdResult.style.color = "red";
                pwdResult.innerHTML = "비밀번호를 입력해주세요.";
                return false;
            }else if(!pwd2){
                pwdResult.style.color = "red";
                pwdResult.innerHTML = "비밀번호 확인을 입력해주세요.";
                return false;
            } else if ((pwd !== pwd2) || (pwd2 !== pwd)) {
                pwdResult.style.color = "red";
                pwdResult.innerHTML = "입력하신 비밀번호와 비밀번호 확인이 일치하지 않습니다.";
                return false;
            }else
                pwdResult.style.color = "green";
            pwdResult.innerHTML = "비밀번호가 동일합니다.";
            return true;
        }


        /*닉네임 중복체크*/

        function nickCheck() {
            const nick = document.getElementById("cNick").value;
            const nickCheckResult = document.getElementById("nickCheck-result");


            if (!nick.trim()) {
                nickCheckResult.style.color = "red";
                nickCheckResult.innerHTML = "닉네임을 입력해주세요.";
                return false;
            }

            if (nick.length <= 2 || nick.length >= 10) {
                nickCheckResult.style.color = "red";
                nickCheckResult.innerHTML = "닉네임은 2~10글자 사이로 설정해주세요.";
                return false;
            }

            console.log("입력한 닉네임", nick);
            $.ajax({
                type: "post",
                url: "/register/nickCheck",
                data: {
                    "cNick": nick
                },
                success: function (nickGood) {
                    console.log("요청성공", nickGood);
                    if (nickGood == "ok") {
                        nickCheckResult.style.color = "green";
                        nickCheckResult.innerHTML = "사용 가능한 닉네임입니다.";
                    } else {
                        nickCheckResult.style.color = "red";
                        nickCheckResult.innerHTML = "이미 사용중인 닉네임입니다.";
                    }
                },
                error: function (err) {
                    console.log("에러발생", err);
                }
            });
        }

        function nickNameCheck(frm) {
            var nm = frm.cNick.value;
            if (nm.length < 2 || nm.length > 10) {
                return false;
            }
            return true;
        }


        // 우편번호: 숫자 1~6자리
        function zipCheck(frm) {
            var zip = document.getElementById("zip").value.trim();
            var pattern = /^[0-9]{1,6}$/;
            if (!pattern.test(zip)) {
                alert("우편번호는 숫자 1~6자리로 입력해주세요.");
                return false;
            }
            return true;
        }

        // 도로명 주소: 필수, 한글/영문/숫자/공백/-/ 까지 허용, 1~30자
        function roadCheck(frm) {
            var road = document.getElementById("roadAddress").value.trim();
            var pattern = /^[가-힣a-zA-Z0-9\s\-/]{1,30}$/;
            if (!pattern.test(road)) {
                alert("도로명 주소는 한글/영문/숫자와 공백, -, / 만 허용되며 1~30자까지 가능합니다.");
                return false;
            }
            return true;
        }

        // 상세 주소: 필수, 1~30자
        function detailCheck(frm) {
            var detail = document.getElementById("detailAddress").value.trim();
            if (detail.length < 1 || detail.length > 30) {
                alert("상세 주소는 1자 이상 30자 이하로 입력해주세요.");
                return false;
            }
            return true;
        }



        /* 필수 개인정보 유효성 검사*/
        function piiCheck(frm) {
            var pii = frm.querySelector('input[name="piiBox"]').checked;
            if (pii) {
                return true;
            } else {
                return false;
            }
        }

        var modalSeen2 = false;


        function enablePiiBox() {
            var piiBox = document.getElementById("piiBox");
            piiBox.disabled = false;
            // 체크박스를 체크하고 다시 체크되지 않도록 합니다.
            piiBox.checked = true;
            // 체크박스의 onchange 이벤트에 함수를 연결하여 체크박스가 변경되지 않도록 합니다.
            piiBox.onchange = function() {
                // 체크박스가 항상 체크된 상태로 유지됩니다.
                piiBox.checked = true;
            };
        }


        function openModal2() {
            var modal = document.getElementById("myModal2");
            modal.style.display = "block";

            var scrollBarWidth = window.innerWidth - document.documentElement.clientWidth;
            document.body.style.paddingRight = scrollBarWidth + "px";

            document.body.style.overflowY = "hidden"; // 스크롤 숨기기
            document.body.style.overflowX = "hidden";
        }

        function closeModal2() {
            var modal = document.getElementById("myModal2");
            modal.style.display = "none";

            document.body.style.paddingRight = "";
            document.body.style.overflowY = "auto"; // 스크롤 복구
            document.body.style.overflowX = "";
        }

        document.addEventListener("keydown", function (event) {
            var modal = document.getElementById("myModal2");
            if (event.key === "Escape" && modal.style.display === "block") {
                closeModal2();
                // modal.style.display = "none";
            }
        });

        document.addEventListener('DOMContentLoaded', function () {
            var piiModal = document.getElementById("piiModal");

            /*touModal을 클릭했을 때 모달 열기*/
            piiModal.addEventListener('click', openModal2);

            /*touBox를 클릭했을 때*/
            document.getElementById('piiBox').addEventListener('click', function () {
                /*모달을 아직 보지 않았다면*/
                if (!modalSeen2) {
                    alert("개인정보 약관을 먼저 확인해주세요.");
                    /*체크박스 다시 체크 해제*/
                    this.checked = false;
                }
            });
        });

    </script>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        /*본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.*/
        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {

                    /*팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.*/

                    /*도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.*/
                    var roadAddr = data.roadAddress;
                    var extraRoadAddr = '';

                    /*법정동명이 있을 경우 추가한다. (법정리는 제외)
                    법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.*/
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraRoadAddr += data.bname;
                    }
                    /*건물명이 있고, 공동주택일 경우 추가한다.*/
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    document.getElementById('zip').value = data.zonecode;


                    document.getElementById("roadAddress").value = roadAddr;
                    document.getElementById("jibunAddress").value = data.jibunAddress;
                }
            }).open();
        }
    </script>
    </body>
