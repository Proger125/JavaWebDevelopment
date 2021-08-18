<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%@ page import="edu.epam.webproject.validator.ValidatorRegExp"%>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17.08.2021
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="last_page" value="${PagePath.SIGN_IN_PAGE}" scope="session"/>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.password" var="password"/>
<fmt:message key="account.repeatPassword" var="repeatPassword"/>
<fmt:message key="account.enter" var="enter"/>
<c:if test="${empty requestScope.email}">
    <c:redirect url="forget_password.jsp"/>
</c:if>
<html>
<head>
    <title>Change password</title>
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<c:if test="${not empty requestScope.email}">
    <div class="form-div">
        <div class="form-wrapper">
            <form action="<c:url value="/Controller"/> " method="post" class="form">
                <input type="hidden" name="command" value="change_user_password_command">
                <input type="hidden" name="email" value="${requestScope.email}">
                <div class="form-item">
                    <label for="password-input">${password}</label>
                    <br>
                    <input type="password" id="password-input" name="password" pattern="${ValidatorRegExp.PASSWORD_REGEXP}"/>
                </div>
                <div class="form-item">
                    <label for="repeat-password-input">${repeatPassword}</label>
                    <br>
                    <input type="password" id="repeat-password-input" name="repeat_password" pattern="${ValidatorRegExp.PASSWORD_REGEXP}"/>
                </div>
                <div class="submit-button-wrapper">
                    <button type="submit">${enter}</button>
                </div>
            </form>
        </div>
    </div>
</c:if>
<c:if test="${empty requestScope.email}">
    <c:redirect url="Controller?command=go_to_sign_in_page_command"/>
</c:if>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
