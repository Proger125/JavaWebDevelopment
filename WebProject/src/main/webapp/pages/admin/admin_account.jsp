<%@ page import="edu.epam.webproject.controller.command.RequestAttribute" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.07.2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getSession().setAttribute(RequestAttribute.LAST_PAGE, PagePath.ADMIN_ACCOUNT_PAGE);
%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.login" var="account_login"/>
<fmt:message key="account.email" var="account_email"/>
<fmt:message key="account.role" var="account_role"/>
<fmt:message key="account.status" var="account_status"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../all/header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<div class="data">
    <div class="user-info">
        <div class="user-photo">
            <c:if test="${user.icon == null}">
                <img src="../../static/img/users/default.jpg" alt="Default image">
            </c:if>
            <c:if test="${user.icon != null}">
                <img src="${user.icon}" alt="User image">
            </c:if>
        </div>
        <div class="user-data">
            <div class="user-property">
        <span class="info-helper">${account_login}:</span>
                <span>${user.login}</span>
            </div>
            <div class="user-property">
                <span class="info-helper">${account_email}:</span>
                <span>${user.email}</span>
            </div>
            <div class="user-property">
                <span class="info-helper">${account_role}:</span>
                <span>${user.role}</span>
            </div>
            <div class="user-property">
                <span class="info-helper">${account_status}:</span>
                <span>${user.status}</span>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../all/footer.jsp"/>
</body>
</html>
