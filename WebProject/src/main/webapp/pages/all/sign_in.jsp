<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 26.07.2021
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="last_page" value="${PagePath.SIGN_IN_PAGE}" scope="session"/>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="error.incorrectData" var="incorrectData"/>
<fmt:message key="account.email" var="email"/>
<fmt:message key="account.password" var="password"/>
<fmt:message key="account.enter" var="enter"/>
<jsp:include page="../../header.jsp"/>

    <div class="form-div">
        <div class="form-wrapper">
            <form action="<c:url value="/Controller"/>" method="post" class="form">
                <input type="hidden" name="command" value="sign_in_command">
                <div class="form-item">
                    <label for="email-input">${email}</label>
                    <br>
                    <input type="text" id="email-input" name="email"/>
                </div>
                <div class="form-item">
                    <label for="password-input">${password}</label>
                    <br>
                    <input type="password" id="password-input" name="password" />
                </div>
                <div class="submit-button-wrapper">
                    <button type="submit">${enter}</button>
                </div>
            </form>
        </div>
    </div>
<jsp:include page="../../footer.jsp"/>
<c:if test="${sessionScope.incorrectData}">
    <script>
        alert("${incorrectData}");
    </script>
</c:if>
</body>
</html>
