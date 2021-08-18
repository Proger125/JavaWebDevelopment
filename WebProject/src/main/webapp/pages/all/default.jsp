<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.07.2021
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.DEFAULT_PAGE}" scope="session"/>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.home" var="home"/>
<html>
<head>
    <title>Default</title>
    <link rel="stylesheet" href="<c:url value="/static/css/util.css"/>">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>

<jsp:include page="../../header.jsp"/>
<c:if test="${requestScope.role_message}">
    <script>
        alert("Users with your role cannot access this page");
    </script>
    <div class="go-home-block">
        <a href="<c:url value="/Controller?command=go_to_about_page_command"/>">
            <button class="go-home">
                ${home}
            </button>
        </a>
    </div>
</c:if>
<div class="exception-block">
    <h1>${sessionScope.exception.message}</h1>
    <c:forEach items="${sessionScope.exception.stackTrace}" var="item">
        <p>${item}</p>
    </c:forEach>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
