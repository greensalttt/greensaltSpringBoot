<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title>오류페이지입니다.</title>
</head>
<body>
<h1>잘못된 접근입니다.</h1>


</body>

<c:if test="${not empty msg}">
    <script>
        alert("${msg}")
    </script>
</c:if>
