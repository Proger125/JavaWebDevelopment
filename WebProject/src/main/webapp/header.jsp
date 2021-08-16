<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="header.enter" var="header_enter"/>
<fmt:message key="header.register" var="header_register"/>
<fmt:message key="header.logout" var="header_logout"/>
<fmt:message key="header.addOffer" var="header_addOffer"/>
<fmt:message key="header.book" var="header_book"/>
<fmt:message key="header.usersList" var="header_usersList"/>
<fmt:message key="header.offersList" var="header_offersList"/>
<fmt:message key="header.reservationList" var="header_reservationsList"/>
<fmt:message key="header.profile" var="header_profile"/>
<head>
    <title>Title</title>
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <jsp:include page="connections.jsp"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light header" >
    <div class="container-fluid">
        <c:set var="user" value="${sessionScope.user}"/>
        <a class="navbar-brand" href="<c:url value="/Controller?command=go_to_about_page_command"/>">WorldFlat</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_sign_in_page_command"/>">${header_enter}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_sign_up_page_command"/>">${header_register}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'USER' && user.status == 'APPROVED'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_add_new_offer_page_command"/>">${header_addOffer}</a>
                    </li>
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=go_to_all_offers_page_user_command"/>" class="nav-link">${header_book}</a>
                    </li>
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=go_to_user_account_page_command"/>" class="nav-link">${header_profile}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=go_to_all_users_page_command"/>" class="nav-link">${header_usersList}</a>
                    </li>
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=go_to_all_offers_page_command"/>" class="nav-link">${header_offersList}</a>
                    </li>
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=go_to_all_reservations_page_command"/>" class="nav-link">${header_reservationsList}</a>
                    </li>
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=go_to_admin_account_page_command"/>" class="nav-link">${header_profile}</a>
                     </li>
                </c:if>
                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=log_out_command"/>" class="nav-link">${header_logout}</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <button form="localeForm" type="submit" name="locale" value="en">
                        EN
                    </button>
                    |
                    <button form="localeForm" type="submit" name="locale" value="ru">
                        RU
                    </button>
                </li>
            </ul>
        </div>
    </div>
</nav>
<form id="localeForm" action="<c:url value="/Controller"/>" method="post">
    <input type="hidden" name="command" value="change_locale_command">
</form>
</body>
</html>
