<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.07.2021
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="last_page" value="${PagePath.ABOUT_PAGE}" scope="session"/>
<!doctype html>
<html>
<head>

    <title>About</title>
    <link rel="stylesheet" href="<c:url value="/static/css/logo.css"/>">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<c:if test="${requestScope.ban_message}">
    <script>
        alert("Your account was banned. Please wait until the admin unlocks your account, or create new");
    </script>
</c:if>
<div class="logo">
    <img src="<c:url value="/static/img/util/logo.png"/> " alt="Logo">
</div>
<div class="custom-slider autoplay">
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_1.jpg"/>" alt="First slider item">
    </div>
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_2.jpg"/>" alt="Second slider item">
    </div>
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_3.jpg"/>" alt="Third slider item">
    </div>
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_4.jpg"/>" alt="Third slider item">
    </div>
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_5.jpg"/>" alt="Third slider item">
    </div>
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_6.jpg"/>" alt="Third slider item">
    </div>
    <div class="custom-slider-item">
        <img src="<c:url value="/static/img/util/carousel_7.jpg"/>" alt="Third slider item">
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
