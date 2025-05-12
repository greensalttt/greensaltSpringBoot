<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">


    <title>albumList</title>

    <style>
        #albumListContainer{;
            margin: 200px auto 150px;
        }

        #wrapper {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
    </style>

</head>
<body>
<div id="wrapper">
<header>
    <jsp:include page="header.jsp"/>
</header>

<div id="albumListContainer">
    <h1>안녕하세요</h1>
</div>

<footer>
<jsp:include page="footer.jsp"/>
</footer>
    </div>

</body>
