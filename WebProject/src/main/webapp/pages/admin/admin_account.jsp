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
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="last_page" value="${PagePath.ADMIN_ACCOUNT_PAGE}" scope="session"/>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.login" var="account_login"/>
<fmt:message key="account.email" var="account_email"/>
<fmt:message key="account.role" var="account_role"/>
<fmt:message key="account.status" var="account_status"/>
<fmt:message key="header.offersList" var="header_offersList"/>
<fmt:message key="header.usersList" var="header_usersList"/>
<fmt:message key="header.reservationList" var="header_reservationList"/>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/static/css/users_styles.css"/> ">
</head>
<body>
<jsp:include page="../../header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<div class="data">
    <div class="user-info">
        <div class="user-photo">
            <c:if test="${user.icon == null}">
                <img src="../../static/img/users/default.jpg" class="user-photo-img" alt="Default image">
            </c:if>
            <c:if test="${user.icon != null}">
                <img src="${user.icon}" alt="User image">
            </c:if>
            <form action="upload" enctype="multipart/form-data" method="post">
                <input type="hidden" name="command" value="upload_user_icon_command">
                <div class="user-photo-wrapper">
                    <div class="user-photo-upload">
                        <input type="file" id="content" name="content" height="130">
                        <br>
                        <input type="submit" value="Upload file">
                    </div>
                </div>
            </form>
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
    <div class="admin-options">
        <a href="../../Controller?command=go_to_all_users_page_command">
            <button class="admin-option">
                ${header_usersList}
            </button>
        </a>
        <a href="../../Controller?command=go_to_all_offers_page_command">
            <button class="admin-option">
                ${header_offersList}
            </button>
        </a>
        <a href="../../Controller?command=log_out_command">
            <button class="admin-option">
                ${header_reservationList}
            </button>
        </a>
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
