<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 13.08.2021
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="customtag" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.GO_TO_OFFER_PAGE}" scope="session"/>
<c:if test="${empty requestScope.offer}">
    <c:redirect url="user_account.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="offer.address" var="offer_address"/>
<fmt:message key="offer.price" var="offer_price"/>
<fmt:message key="offer.description" var="offer_description"/>
<fmt:message key="offer.status" var="offer_status"/>
<fmt:message key="address.street" var="address_street"/>
<fmt:message key="address.flat" var="address_flat"/>
<fmt:message key="account.offer" var="account_offer"/>
<fmt:message key="account.dates" var="account_dates"/>
<fmt:message key="account.clear" var="account_clear"/>
<fmt:message key="account.book" var="account_book"/>
<fmt:message key="offer.address" var="offer_address"/>
<fmt:message key="offer.price" var="offer_price"/>
<fmt:message key="reservation.arrivalDate" var="arrivalDate"/>
<fmt:message key="reservation.departureDate" var="departureDate"/>
<fmt:message key="reservation.totalPrice" var="totalPrice"/>
<fmt:message key="reservation.priceChar" var="priceChar"/>
<fmt:message key="user.emptyList" var="emptyList"/>
<fmt:message key="header.reservationList" var="reservationList"/>
<fmt:message key="reservation.inactiveList" var="inactiveList"/>
<fmt:message key="admin.confirm" var="confirm"/>
<fmt:message key="admin.ban" var="ban"/>
<c:set var="offer" value="${requestScope.offer}"/>
<html>
<head>
    <title>Offer</title>
    <link rel="stylesheet" href="<c:url value="/static/css/offer.css"/> ">
    <link rel="stylesheet" href="<c:url value="/static/css/date_cell.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/users_style.css"/>">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
    <jsp:include page="../../header.jsp"/>
    <div class="custom-slider autoplay">
        <c:forEach items="${offer.photos}" var="photo">
            <div class="custom-slider-item">
                <img src="<c:url value="${photo}"/> " alt="Offer photo">
            </div>
        </c:forEach>
    </div>
    <div class="offer-info-helper">
        ${account_offer}
    </div>
    <div class="offer-info">
        <div class="offer-property">
            <span class="info-helper">${offer_address}:</span>
            <br>
            ${offer.address.country}, ${offer.address.city}, ${offer.address.street} ${address_street}, ${offer.address.houseNumber}, ${address_flat} ${offer.address.apartmentNumber}
        </div>
        <div class="offer-property">
            <span class="info-helper">${offer_price}:</span>
            <br>
            ${offer.pricePerDay} ${priceChar}
        </div>
        <div class="offer-property">
            <span class="info-helper">${offer_description}:</span>
            <br>
            ${offer.description}
        </div>
    </div>
    <c:if test="${offer.owner.id != sessionScope.user.id}">
        <div class="offer-info-helper">
                ${account_dates}
        </div>
        <div class="offer-dates">
            <c:forEach items="${requestScope.all_days_list}" var="day">
                <c:if test="${requestScope.booked_days_list.contains(day)}">
                    <mytag:datecell date="${day}" enable="false"/>
                </c:if>
                <c:if test="${!requestScope.booked_days_list.contains(day)}">
                    <mytag:datecell date="${day}" enable="true"/>
                </c:if>
            </c:forEach>
        </div>
        <div class="offer-functions">
            <button class="offer-functions-button" id="clear-button" type="submit" onclick="clearDate()">
                    ${account_clear}
            </button>
            <button class="offer-functions-button" id="book-button" type="submit" onclick="book()">
                    ${account_book}
            </button>
        </div>
        <div id="hidden-offer-id">
                ${offer.id}
        </div>
        <div id="hidden-offer-price">
                ${offer.pricePerDay}
        </div>
    </c:if>
    <c:if test="${offer.owner.id == sessionScope.user.id}">
        <div class="user-option">
            <div class="reservation-list">
                <span class="info-helper">${inactiveList}</span>
            </div>
            <c:if test="${requestScope.in_active_reservations_list.size() != 0}">
                <c:forEach items="${requestScope.in_active_reservations_list}" var="reservation">
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
                        <div class="user-functions">
                            <form action="<c:url value="/Controller"/>" method="post" >
                                <input type="hidden" name="command" value="change_reservation_status_command">
                                <input type="hidden" name="offer_id" value="${offer.id}">
                                <input type="hidden" name="reservation_id" value="${reservation.id}">
                                <c:if test="${reservation.status == 'IN_PROCESSING'}">
                                    <button type="submit" value="CONFIRMED" name="status">${confirm}</button>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.in_active_reservations_list.size() == 0}">
                <div class="empty-list">
                        ${emptyList}
                </div>
            </c:if>
        </div>
    </c:if>
    <jsp:include page="../../footer.jsp"/>
<script src="<c:url value="/static/js/offer.js"/> "></script>
</body>
</html>
