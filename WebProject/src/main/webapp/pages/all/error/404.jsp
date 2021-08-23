<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19.08.2021
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.ERROR_404_PAGE}" scope="session"/>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.home" var="home"/>
<fmt:message key="error.404.header" var="error_header"/>
<fmt:message key="error.404.message" var="error_message"/>
<html>
<head>
    <title>404</title>
    <link rel="stylesheet" href="<c:url value="/static/css/util.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/error.css"/>">
</head>
<body>
<jsp:include page="../../../header.jsp"/>
    <div class="error-header">
        ${error_header}
    </div>
    <div class="error-message">
        ${error_message}
    </div>
<div class="go-home-block">
    <a href="<c:url value="/Controller?command=go_to_about_page_command"/>">
        <button class="go-home">
            ${home}
        </button>
    </a>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>
