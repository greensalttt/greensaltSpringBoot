<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<head>
    <title>Green Salt</title>
    <link rel="icon" type="image/x-icon" href="https://cdn-icons-png.flaticon.com/128/15439/15439306.png">
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

        .writing-header {
            position: relative;
            margin: 20px 0 0 0;
            padding-bottom: 10px;
            border-bottom: 1px solid #323232;
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

        .frm {
            width: 100%;
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

        /* 댓글 목록 스타일링 */
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

        #modBtn {
            background-color: saddlebrown;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        #modBtn:hover {
            background-color: brown;
        }

        .deleted {
            color: #999;
            text-decoration: line-through;
            background-color: #f0f0f0;
        }

        #top{
            margin-bottom: 150px;
        }
    </style>
</head>

<body>

<header id="top">
    <jsp:include page="header.jsp"/>
</header><br><br>

<script>
    let msg = "${msg}";
    if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>

<div class="container">
    <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form id="form" class="frm" action="" method="post">
        <input class="boardTitle" type="hidden" name="bno" value="${boardDto.bno}">

        <input class="boardTitle" name="title" type="text" value="<c:out value = '${boardDto.title}'/>" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><br>
        <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><c:out value ="${boardDto.content}"/></textarea><br>

        <c:if test="${mode eq 'new'}">
            <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
        </c:if>
        <c:if test="${mode ne 'new'}">
            <button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 글쓰기</button>
        </c:if>

        <c:if test="${boardDto.cId == cId}">
            <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
            <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
    </form>

    <!-- 댓글 기능 추가, 읽기 모드일 때만 보이도록 수정 -->
    <c:if test="${mode ne 'new'}">
        <div class="comment-container" id="commentSection">

            <h2>댓글</h2>
            <div id="commentList"></div>
            <div id="replyForm" style="display:none">
                <input type="text" name="replyComment">
                <button id="wrtRepBtn" type="button">등록</button>
            </div>

            <div id="commenter">
                <input type="text" name="comment" placeholder="댓글을 남겨보세요.">
                <div id="buttonContainer">
                    <button id="sendBtn" type="button">작성</button>
                    <button id="modBtn" type="button" style="display: none;">수정</button>
                </div>
        </div>
    </c:if>
</div><br><br>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

<script>
    // 게시글 관련 스크립트
    $(document).ready(function(){
        let formCheck = function() {
            let form = document.getElementById("form");
            if(form.title.value=="") {
                alert("제목을 입력해 주세요.");
                form.title.focus();
                return false;
            }

            if(form.content.value=="") {
                alert("내용을 입력해 주세요.");
                form.content.focus();
                return false;
            }
            return true;
        }

        $("#writeNewBtn").on("click", function(){
            location.href="<c:url value='/board/write'/>";
        });

        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");

            if(formCheck())
                form.submit();
        });

        $("#modifyBtn").on("click", function(){
            $("#commentSection").hide();
            let form = $("#form");
            let isReadonly = $("input[name=title]").attr('readonly');

            if(isReadonly=='readonly') {
                $(".writing-header").html("게시판 수정");
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
                return;
            }

            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });

        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;

            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list${searchCondition.queryString}'/>";
        });
    });

</script>

<%--    댓글--%>
<script>
        $(document).ready(function(){
        if ("${mode}" !== "new") {
        let bno = ${boardDto.bno};
        let showList = function (bno){

<%--    제이쿼리 방식으로 Ajax를 활용한 콜백 함수로 비동기통신 처리 방식--%>
        $.ajax({
        type:'GET',
        url: '/comments?bno=' + bno,
        // result: JSON 형식으로 응답된 데이터
        success: function(result){
        if (result.length === 0) {
        $("#commentList").html("<p>등록된 댓글이 없습니다</p>");
    } else {
        $("#commentList").html(toHtml(result));
    }
    },
        error: function(){
        alert("error");
    }
    });
    }

        showList(bno);

        $("#sendBtn").click(function(){
        let comment = $("input[name=comment]").val();
        if(comment.trim() == ''){
        alert("댓글을 입력해주세요.");
        $("input[name=comment]").focus();
        return;
    }
        $.ajax({
        type: 'POST',
        url: '/comments?bno=' + bno,
        headers: {"content-type": "application/json"},
        data: JSON.stringify({bno: bno, comment: comment}),
        success: function(){
        alert("댓글이 등록되었습니다.");
        showList(bno);
    },
        error: function(){
        alert("error");
    }
    });
    });

        $("#commentList").on("click", ".modBtn", function () {
        let cno = $(this).parent().attr("data-cno");
            let comment = $("span.comment", $(this).parent()).text();
        $("input[name=comment]").val(comment);
            $("#modBtn").show();
            $("#modBtn").attr("data-cno", cno);
    });

        $("#modBtn").click(function(){
        let cno = $(this).attr("data-cno");
        let comment = $("input[name=comment]").val();

        if(comment.trim() == ''){
        alert("댓글을 입력해주세요.");
        $("input[name=comment]").focus();
        return;
    }
        $.ajax({
        type: 'PATCH',
        url: '/comments/' + cno,
        headers: {"content-type": "application/json"},
        data: JSON.stringify({cno: cno, comment: comment}),
        success: function(){
        alert("댓글이 수정됐습니다.");
        showList(bno);
    },
        error: function(){
        alert("error");
    }
    });
    });

        $("#wrtRepBtn").click(function(){
        let comment = $("input[name=replyComment]").val();
        let pcno = $("#replyForm").parent().attr("data-pcno");

        if(comment.trim() == ''){
        alert("답글을 입력해 주세요.");
        $("input[name=replyComment]").focus();
        return;
    }
        $.ajax({
        type: 'POST',
        url: '/comments?bno=' + bno,
        headers: {"content-type": "application/json"},
        data: JSON.stringify({pcno: pcno, bno: bno, comment: comment}),
        success: function(){
        alert("답글이 등록되었습니다");
        showList(bno);
    },
        error: function(){
        alert("error");
    }
    });

        $("#replyForm").css("display", "none");
        $("input[name=replyComment]").val('');
        $("#replyForm").appendTo("body");
    });

        $("#commentList").on("click", ".replyBtn", function () {
        $("#replyForm").appendTo($(this).parent());
        $("#replyForm").css("display", "block");
    });

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

                    // 댓글 내용만 "삭제된 댓글입니다"로 변경
                    commentElement.find(".commenter").text("삭제된 댓글입니다");
                    commentElement.find(".comment").text(""); // 댓글 내용 제거
                    commentElement.find(".modBtn, .replyBtn, .delBtn").hide(); // 수정, 답글, 삭제 버튼 숨김

                    // 삭제된 상태를 시각적으로 표시하기 위한 클래스 추가 (CSS 적용 가능)
                    commentElement.addClass("deleted");
                },
                error: function() {
                    alert("댓글 삭제 중 오류가 발생했습니다.");
                }
            });
        });

// 댓글을 HTML로 변환하는 함수
            let toHtml = function (comments) {
                let tmp = "<ul>";

                comments.forEach(function(comment){
                    tmp += '<li data-cno="' + comment.cno
                    tmp += '" data-pcno="' + comment.pcno
                    tmp += '" data-bno="' + comment.bno + '">'
                    if(comment.cno != comment.pcno)
                        tmp += 'ㄴ'

                    tmp += '<span class="commenter">' + comment.commenter + "=" + '</span>'
                    tmp += '<span class="comment">' + comment.comment + " " + '</span>'
                    tmp += '<button class="replyBtn">답글</button>'
                    tmp += '<button class="modBtn">수정</button>'
                    tmp += '<button class="delBtn">삭제</button>'
                    tmp += '</li>'
                })
                return tmp + "</ul>";
            }
        }
        });
</script>
</body>
