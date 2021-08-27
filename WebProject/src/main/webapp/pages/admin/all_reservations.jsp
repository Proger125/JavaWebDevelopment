<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.08.2021
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.GO_TO_ALL_RESERVATIONS_PAGE}" scope="session"/>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/Controller?command=go_to_about_page_command"/>
</c:if>
<c:if test="${empty requestScope.reservations_list}">
    <c:redirect url="admin_account.jsp"/>
</c:if>
<html>
<head>
    <title>Reservations</title>
    <link rel="stylesheet" href="<c:url value="/static/css/users_style.css"/> ">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="offer.address" var="offer_address"/>
<fmt:message key="offer.price" var="offer_price"/>
<fmt:message key="offer.description" var="offer_description"/>
<fmt:message key="address.street" var="address_street"/>
<fmt:message key="address.flat" var="address_flat"/>
<fmt:message key="offer.status" var="offer_status"/>
<fmt:message key="reservation.arrivalDate" var="arrivalDate"/>
<fmt:message key="reservation.departureDate" var="departureDate"/>
<fmt:message key="reservation.totalPrice" var="totalPrice"/>
<fmt:message key="reservation.priceChar" var="priceChar"/>
<jsp:include page="../../header.jsp"/>
<div class="list-container-wrapper">
    <div class="list-container">
        <c:forEach items="${requestScope.reservations_list}" var="reservation">
            <div class="user-reservation">
                <div class="reservation-photo">
                    <c:if test="${reservation.offer.photos.size() == 0}">
                        <img src="<c:url value="/static/img/users/default.jpg"/> " class="user-photo-img" alt="Default image">
                    </c:if>
                    <c:if test="${reservation.offer.photos.size() != 0}">
                        <img src="<c:url value="${reservation.offer.photos.get(0)}"/>" alt="User image">
                    </c:if>
                </div>
                <div class="reservation-info">
                    <div class="reservation-property">
                        <span class="info-helper">${offer_address}:</span>
                            ${reservation.offer.address.country}, ${reservation.offer.address.city}, ${reservation.offer.address.street} ${address_street}, ${reservation.offer.address.houseNumber}, ${reservation.offer.address.apartmentNumber} ${address_flat}
                    </div>
                    <div class="reservation-property">
                        <span class="info-helper">${arrivalDate}:</span>
                            ${reservation.arrivalDate}
                    </div>
                    <div class="reservation-property">
                        <span class="info-helper">${departureDate}:</span>
                            ${reservation.departureDate}
                    </div>
                    <div class="reservation-property">
                        <span class="info-helper">${totalPrice}:</span>
                            ${reservation.totalPrice} ${priceChar}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
