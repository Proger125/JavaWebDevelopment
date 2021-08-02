<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<%

%>
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
<head>
    <title>Title</title>
    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link href="../../static/bootstrap/css/bootstrap-reboot.css" rel="stylesheet">
    <link href="../../static/bootstrap/css/bootstrap-grid.css" rel="stylesheet">
    <link href="../../static/bootstrap/css/bootstrap-utilities.css" rel="stylesheet">
    <link href="../../static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="../../static/css/slick-theme.css">

    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arvo:ital,wght@0,400;1,700&display=swap" rel="stylesheet">

    <link href="../../static/css/footer.css" rel="stylesheet">
    <link href="../../static/css/style.css" rel="stylesheet">

    <script src="../../static/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script type="text/javascript" src="../../static/js/script.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <c:set var="user" value="${sessionScope.user}"/>
        <a class="navbar-brand" href="../../Controller?command=go_to_about_page_command">WorldFlat</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="../../Controller?command=go_to_sign_in_page_command">${header_enter}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../../Controller?command=go_to_sign_up_page_command">${header_register}</a>
                    </li>
                </c:if>
                <c:if test="${user.role == 'USER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">${header_addOffer}</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">${header_book}</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">${header_logout}</a>
                    </li>
                </c:if>
                <c:if test="${user.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a href="#" class="nav-link">${header_usersList}</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">${header_offersList}</a>
                    </li>
                </c:if>
                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a href="../../Controller?command=log_out_command" class="nav-link">${header_logout}</a>
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
<form id="localeForm" action="../../Controller" method="post">
    <input type="hidden" name="command" value="change_locale_command">
</form>
</body>
</html>
