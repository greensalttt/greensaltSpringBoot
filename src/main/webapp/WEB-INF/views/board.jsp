<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<head>
    <title>Green Salt</title>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .container {
            width : 50%;
            margin : auto;
        }

        .boardTitle, textarea {
            width: 100%;
            background: #f8f8f8;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            padding: 8px;
            outline-color: #e6e6e6;
        }

        textarea {
            resize: none;
        }

        .btn {
            background-color: rgb(236, 236, 236);
            border: none;
            color: black;
            padding: 6px 12px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        .btn:hover {
            text-decoration: underline;
        }

        .comment-container {
            margin-top: 40px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
        }

        .comment-container h2{
            font-size: 16px;
        }

        #commentList {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #e0e0e0;
            background-color: #ffffff;
            border-radius: 8px;
        }

        .commentList ul {
            list-style-type: none;
            padding-left: 0;
        }

        .commentList li {
            margin-bottom: 10px;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .commentList li:last-child {
            border-bottom: none;
        }

        #replyForm {
            margin-top: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            background-color: #f1f1f1;
            border-radius: 8px;
        }

        #commenter {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #e0e0e0;
            background-color: #ffffff;
            border-radius: 8px;
        }

        #commenter input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        #buttonContainer {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        #sendBtn {
            background-color: darkgreen;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        #sendBtn:hover {
            background-color: darkolivegreen;
        }

        #top{
            margin-bottom: 150px;
        }


        .modal {
            display: none;
            position: fixed;
            z-index: 10;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
        }

        .modal-content {
            background-color: #fff;
            margin: 15% auto 15% auto;
            padding: 20px;
            width: 400px;
            border-radius: 8px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            position: relative;
        }

        .modal .close {
            position: absolute;
            right: 10px;
            top: 10px;
            font-size: 20px;
            cursor: pointer;
        }

        .button-group {
            display: flex;
            justify-content: flex-end;
            gap: 5px; /* 버튼 사이 간격 */
            margin-top: 20px;
        }

        .button-group .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }

        .board-meta {
            font-size: 13px;
            color: #777;
            text-align: right;
            margin-bottom: 15px;
        }

        .board-meta p {
            margin: 0;
        }

        .board-title h2 {
            font-size: 24px;
            font-weight: 700;
            margin-bottom: 12px;
            border-bottom: 2px solid #333;
            padding-bottom: 8px;
        }


        .board-content {
            min-height: 400px;
            white-space: pre-wrap;
        }

        .board-content p {
            font-size: 16px;
            line-height: 1.8;
        }

    </style>
</head>


<body>

<header id="top">
    <jsp:include page="header.jsp"/>
</header><br><br>

<script>
    let msg = "${msg}";
    if(msg=="MOD_OK")    alert("성공적으로 수정되었습니다.");
    if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>

<div class="container">
       <div class="board-title">
            <h2><c:out value="${boardDto.title}"/></h2>
        </div>

        <div class="board-meta">
            <p>
                <c:out value="${boardDto.writer}"/> |
                <fmt:formatDate value="${boardDto.createdAt}" pattern="yyyy-MM-dd HH:mm"/> |
                조회수: <c:out value="${boardDto.viewCnt}"/>
            </p>
        </div>

        <div class="board-content" >
            <p><c:out value="${boardDto.content}"/></p>
        </div>

        <div class="button-group">
            <c:if test="${boardDto.createdBy == cId}">
                <a href="<c:url value='/board/modify?bno=${boardDto.bno}'/>" class="btn">
                    <i class="fa fa-edit"></i> 수정
                </a>

                <form id="deleteForm" action="/board/remove" method="post" style="display: inline;">
                    <input type="hidden" name="bno" value="${boardDto.bno}"/>
                    <button type="submit" class="btn btn-remove" onclick="return confirm('정말로 삭제하시겠습니까?')">
                        <i class="fa fa-trash"></i> 삭제
                    </button>
                </form>
            </c:if>

            <a href="<c:url value='/board/list'/>" class="btn btn-list"><i class="fa fa-bars"></i> 목록</a>
        </div>



    <div class="comment-container" id="commentSection">

        <h2>댓글</h2>
        <div id="commentList"></div>
        <div id="replyForm" style="display:none">
        <p id="commenterText"></p>
        <input type="text" name="replyComment">
            <button id="wrtRepBtn" type="button">답글 등록</button>
        </div>

        <div id="commenter">
            <input type="text" name="comment" placeholder="댓글을 남겨보세요.">
            <div id="buttonContainer">
                <button id="sendBtn" type="button">작성</button>
            </div>
        </div>

        <c:if test="${empty sessionScope.cId}">
            <script>
                   $(document).ready(function () {
                    const currentUrl = window.location.pathname + window.location.search;
                    const messageHtml = '<a href="/login?redirect=' + encodeURIComponent(currentUrl) + '" style="color:gray;">' +
                        '로그인 후 댓글을 작성할 수 있습니다.' +
                        '</a>';

                    $("input[name='comment']").replaceWith(messageHtml);
                    $("#sendBtn").hide();

                });
            </script>
        </c:if>
    </div><br><br>

    <!-- 댓글 수정용 모달 -->
    <div id="commentModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="commentModalClose()">&times;</span>

            <h3>댓글 수정</h3>
            <input type="text" id="modalCommentInput">
            <button id="confirmModBtn">수정하기</button>
        </div>
    </div>

</div>


    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>

    <script>
        let toHtml = function (comments) {

            const cId = "${sessionScope.cId}";

            let tmp = "<ul>";
            comments.forEach(function(comment) {
                // Jackson 규칙으로 cId를 cid로 찍어야 값이 나옴

                // 부모 댓글이 있으면 해당 번호, 없으면 자기 번호
                let pcno = comment.pcno !== null ? comment.pcno : comment.cno;

                tmp += '<li data-cno="' + comment.cno + '"';
                tmp += ' data-pcno="' + pcno + '"';
                tmp += ' data-bno="' + comment.bno + '"';
                tmp += ' data-comment="' + comment.comment + '"';
                tmp += ' data-commenter="' + comment.commenter + '"';
                tmp += '>';

                if (comment.pcno != null) tmp += 'ㄴ';

                tmp += '<span class="commenter">' + comment.commenter + '=' + '</span>';
                tmp += '<span class="comment">' + comment.comment + '</span>';

                // 본인 댓글일 경우에만 수정/삭제 버튼 추가
                if (comment.createdBy == cId) {
                    tmp += '<button class="modBtn">수정</button>';
                    tmp += '<button class="delBtn">삭제</button>';
                }

                // 답글 버튼은 로그인한 사용자만 가능

                if (cId) {
                    tmp += '<button class="replyBtn">답글</button>';
                }
                tmp += '</li>';
            });
            return tmp + "</ul>";
        };

        // 답글버튼
        $(document).ready(function(){

                $("#commentList").on("click", ".replyBtn", function () {
                    $("#replyForm").appendTo($(this).parent());
                    $("#replyForm").css("display", "block");
                    let parentLi = $(this).parent();
                    // console.log("parentLi:", parentLi.attr("data-parentComment"));


                    // 데이터 속성에서 댓글자(commenter)와 댓글 내용(comment) 가져오기
                    let commenter = parentLi.attr("data-commenter"); // 예: "John Doe"
                    // 댓글자 값을 #commenterText에 표시
                    $("#commenterText").text(commenter + "님에게 답글");

                });

                // 답글 작성 버튼

                $("#wrtRepBtn").click(function () {
                    let comment = $("input[name=replyComment]").val();
                    let pcno = $("#replyForm").parent().attr("data-pcno");

                    console.log("pcno:", pcno);

                    if (comment.trim() == '') {
                        alert("답글을 입력해 주세요.");
                        $("input[name=replyComment]").focus();
                        return;
                    }
                    $.ajax({
                        type: 'POST',
                        url: '/comments?bno=' + bno,
                        headers: {"content-type": "application/json"},
                        data: JSON.stringify({bno: bno, comment: comment, pcno: pcno}),
                        success: function () {
                            alert("답글이 등록되었습니다");
                            showList(bno);
                        },
                        error: function () {
                            alert("error");
                        }
                    });

                    $("#replyForm").css("display", "none");
                    $("input[name=replyComment]").val('');
                    $("#replyForm").appendTo("body");
                });


                // 댓글 보여주기

                let bno = ${boardDto.bno};
                let showList = function (bno) {

                    <%--    제이쿼리 방식으로 Ajax를 활용한 콜백 함수로 비동기통신 처리 방식--%>
                    $.ajax({
                        type: 'GET',
                        url: '/comments?bno=' + bno,
                        success: function (result) {
                            if (result.length === 0) {
                                $("#commentList").html("<p>등록된 댓글이 없습니다</p>");
                            } else {
                                $("#commentList").html(toHtml(result));
                            }
                        },
                        error: function () {
                            alert("error");
                        }
                    });
                }
                showList(bno);


                // 댓글 작성

                $("#sendBtn").click(function () {
                    let comment = $("input[name=comment]").val();
                    if (comment.trim() == '') {
                        alert("댓글을 입력해주세요.");
                        $("input[name=comment]").focus();
                        return;
                    }
                    $.ajax({
                        type: 'POST',
                        url: '/comments?bno=' + bno,
                        headers: {"content-type": "application/json"},
                        data: JSON.stringify({bno: bno, comment: comment}),
                        success: function () {
                            alert("댓글이 등록되었습니다.");
                            $("input[name=comment]").val('');  // 댓글 입력란 초기화
                            showList(bno);
                        },
                        error: function () {
                            alert("error");
                        }
                    });
                });

                // 댓글 수정
                    let selectedCno = null;


                    // 1. 댓글 수정 버튼 클릭 → 모달 열기
                    $("#commentList").on("click", ".modBtn", function () {
                    selectedCno = $(this).parent().attr("data-cno");
                    const comment = $("span.comment", $(this).parent()).text();

                    $("#modalCommentInput").val(comment);
                    $("#commentModal").show();
                });

                    // 2. 모달 내 "수정하기" 버튼 → 댓글 수정 AJAX
                    $("#confirmModBtn").click(function () {
                    const comment = $("#modalCommentInput").val().trim();

                    if (comment === "") {
                    alert("댓글을 입력해주세요.");
                    $("#modalCommentInput").focus();
                    return;
                }

                    $.ajax({
                    type: 'PATCH',
                    url: '/comments/' + selectedCno,
                    headers: {"content-type": "application/json"},
                    data: JSON.stringify({cno: selectedCno, comment: comment, bno: bno}),
                    success: function () {
                    alert("댓글이 수정됐습니다.");
                    $("#commentModal").hide();
                    showList(bno);
                },
                    error: function () {
                    alert("댓글 수정 중 오류가 발생했습니다.");
                }
                });
                });

                // 삭제
                    $("#commentList").on("click", ".delBtn", function (){
                    let commentElement = $(this).parent(); // 클릭된 버튼의 부모(li 요소)
                    let cno = commentElement.attr("data-cno"); // 댓글의 고유 번호

                    if (!confirm("정말로 삭제하시겠습니까?")) {
                    return;
                }
                    $.ajax({
                    type: 'DELETE',
                    url: '/comments/' + cno + '?bno=' + bno,
                    success: function() {
                    // 댓글 삭제 성공 시 처리
                    alert("삭제에 성공했습니다."); // 성공 메시지를 알림으로 표시

                    // // 댓글 내용만 "삭제된 댓글입니다"로 변경
                    commentElement.find(".commenter").text("삭제된 댓글입니다");
                    commentElement.find(".comment").text(""); // 댓글 내용 제거
                    commentElement.find(".modBtn, .replyBtn, .delBtn").hide(); // 수정, 답글, 삭제 버튼 숨김
                    //
                    // // 삭제된 상태를 시각적으로 표시하기 위한 클래스 추가 (CSS 적용 가능)
                    commentElement.addClass("deleted");
                },
                    error: function() {
                    alert("댓글 삭제 중 오류가 발생했습니다.");
                    }
                    });
                    });
                });
    </script>

    <script>
        function commentModalClose() {
            var modal = document.getElementById("commentModal");
            modal.style.display = "none";

            document.body.style.paddingRight = "";
            document.body.style.overflowY = "auto"; // 스크롤 복구
            document.body.style.overflowX = "";
        }


        // ESC 키 눌렀을 때 댓글 수정 모달 닫기
        document.addEventListener("keydown", function (event) {
            var modal = document.getElementById("commentModal");
            if (event.key === "Escape" && modal.style.display === "block") {
                commentModalClose();
            }
        });

    </script>

</body>
