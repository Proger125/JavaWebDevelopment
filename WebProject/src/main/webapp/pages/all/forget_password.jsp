<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17.08.2021
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%@ page import="edu.epam.webproject.validator.ValidatorRegExp"%>
<c:set var="last_page" value="${PagePath.FORGET_PASSWORD_PAGE}" scope="session"/>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.email" var="email"/>
<fmt:message key="account.enter" var="enter"/>
<fmt:message key="message.emailChangePassword" var="message"/>
<html>
<head>
    <title>Forget password</title>
    <link rel="stylesheet" href="<c:url value="/static/css/users_style.css"/> ">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<c:if test="${not empty requestScope.email}">
    <div class="email-sent">
        ${message}: ${requestScope.email}
    </div>
</c:if>
<c:if test="${empty requestScope.email}">
    <div class="form-div">
        <div class="form-wrapper">
            <form action="<c:url value="/Controller"/>" method="post" class="form">
                <input type="hidden" name="command" value="forget_password_command">
                <div class="form-item">
                    <label for="email-input">${email}</label>
                    <br>
                    <input type="text" id="email-input" name="email" pattern="${ValidatorRegExp.EMAIL_REGEXP}"/>
                </div>
                <div class="submit-button-wrapper">
                    <button type="submit">${enter}</button>
                </div>
            </form>
        </div>
    </div>
</c:if>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
