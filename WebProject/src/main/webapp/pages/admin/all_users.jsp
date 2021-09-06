<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.08.2021
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="last_page" value="${PagePath.GO_TO_ALL_USERS_PAGE}" scope="session"/>
<c:if test="${empty requestScope.users_list}">
    <c:redirect url="admin_account.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.login" var="account_login"/>
<fmt:message key="account.email" var="account_email"/>
<fmt:message key="account.role" var="account_role"/>
<fmt:message key="account.status" var="account_status"/>
<fmt:message key="admin.ban" var="admin_ban"/>
<fmt:message key="admin.unban" var="admin_unban"/>
<fmt:message key="admin.confirm" var="admin_confirm"/>
<html>
<head>
    <title>All users</title>
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="list-container-wrapper">
        <div class="list-container">
            <c:forEach items="${requestScope.users_list}" var="user">
                <div class="custom-user">
                    <div class="custom-user-photo">
                        <c:if test="${user.icon == null}">
                            <img src="<c:url value="/static/img/users/default.jpg"/> " class="user-photo-img" alt="Default image">
                        </c:if>
                        <c:if test="${user.icon != null}">
                            <img src="<c:url value="${user.icon}"/>" alt="User image">
                        </c:if>
                    </div>
                    <div class="custom-user-info">
                        <div class="user-property">
                            <span class="info-helper">${account_login}:</span>
                            ${user.login}
                        </div>
                        <div class="user-property">
                            <span class="info-helper">${account_email}:</span>
                            ${user.email}
                        </div>
                        <div class="user-property">
                            <span class="info-helper">${account_role}:</span>
                            ${user.role}
                        </div>
                        <div class="user-property">
                            <span class="info-helper">${account_status}:</span>
                            ${user.status}
                        </div>
                    </div>
                    <div class="admin-functions">
                        <form action="<c:url value="/Controller"/>" method="post" >
                            <input type="hidden" name="command" value="change_user_status_command" />
                            <input type="hidden" name="user_id" value="${user.id}"/>
                            <c:if test="${user.status == 'APPROVED'}">
                                <button type="submit" value="REJECTED" name="status" class="admin-functions-button">${admin_ban}</button>
                            </c:if>
                            <c:if test="${user.status == 'REJECTED'}">
                                <button type="submit" value="APPROVED" name="status" class="admin-functions-button">${admin_unban}</button>
                            </c:if>
                            <c:if test="${user.status == 'IN_PROCESS'}">
                                <button type="submit" value="APPROVED" name="status" class="admin-functions-button">${admin_confirm}</button>
                            </c:if>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
