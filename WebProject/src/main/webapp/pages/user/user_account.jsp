<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.07.2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="last_page" value="${PagePath.GO_TO_USER_ACCOUNT_PAGE}" scope="session"/>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/Controller?command=go_to_about_page_command"/>
</c:if>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.login" var="account_login"/>
<fmt:message key="account.email" var="account_email"/>
<fmt:message key="account.role" var="account_role"/>
<fmt:message key="account.status" var="account_status"/>
<fmt:message key="header.book" var="header_book"/>
<fmt:message key="header.addOffer" var="header_addOffer"/>
<fmt:message key="message.emailVerification" var="emailVerification"/>
<fmt:message key="offer.address" var="offer_address"/>
<fmt:message key="offer.price" var="offer_price"/>
<fmt:message key="offer.description" var="offer_description"/>
<fmt:message key="address.street" var="address_street"/>
<fmt:message key="address.flat" var="address_flat"/>
<fmt:message key="reservation.arrivalDate" var="arrivalDate"/>
<fmt:message key="reservation.departureDate" var="departureDate"/>
<fmt:message key="reservation.totalPrice" var="totalPrice"/>
<fmt:message key="user.emptyList" var="emptyList"/>
<fmt:message key="header.offersList" var="offersList"/>
<fmt:message key="header.reservationList" var="reservationList"/>
<fmt:message key="offer.status" var="offer_status"/>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/static/css/users_style.css"/>">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
    <title>User Account</title>
</head>
<body>

<jsp:include page="../../header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<c:if test="${sessionScope.email_confirm == false}">
    <div class="email-not-confirm">
        <span>
            ${emailVerification}: ${requestScope.email}.
        </span>
    </div>
</c:if>
<c:if test="${sessionScope.email_confirm == true}">
    <div class="data">
        <div class="user-info">
            <div class="user-photo">
                <c:if test="${user.icon == null}">
                    <label for="content">
                        <img src="<c:url value="/static/img/users/default.jpg"/>" class="user-photo-img" alt="Default image">
                    </label>
                </c:if>
                <c:if test="${user.icon != null}">
                    <label for="content">
                        <img src="<c:url value="${user.icon}" />" class="user-photo-img" alt="User image">
                    </label>
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
        <div class="user-options-wrapper">
            <div class="user-options">
                <div class="user-option">
                    <div class="offers-list">
                       <span class="info-helper">${offersList}</span>
                    </div>
                    <c:if test="${requestScope.offers_list.size() != 0}">
                        <c:forEach items="${requestScope.offers_list}" var="offer">
                            <div class="user-offer">
                                <div class="offer-photo">
                                    <c:if test="${offer.photos.size() == 0}">
                                        <img src="<c:url value="/static/img/users/default.jpg"/> " class="user-photo-img" alt="Default image">
                                    </c:if>
                                    <c:if test="${offer.photos.size() != 0}">
                                        <img src="<c:url value="${offer.photos.get(0)}"/>" alt="User image">
                                    </c:if>
                                </div>
                                <div class="offer-info">
                                    <div class="offer-property">
                                        <span class="info-helper">${offer_address}:</span>
                                        <a href="<c:url value="/Controller?command=go_to_offer_page_command&offer_id=${offer.id}"/> ">${offer.address.country}, ${offer.address.city}, ${offer.address.street} ${address_street}, ${offer.address.houseNumber}, ${address_flat} ${offer.address.apartmentNumber}</a>
                                    </div>
                                    <div class="offer-property">
                                        <span class="info-helper">${offer_description}:</span>
                                            ${offer.description}
                                    </div>
                                    <div class="offer-property">
                                        <span class="info-helper">${offer_price}:</span>
                                            ${offer.pricePerDay}
                                    </div>
                                    <div class="offer-property">
                                        <span class="info-helper">${offer_status}:</span>
                                        ${offer.status}
                                    </div>
                                </div>
                                <div class="user-functions">
                                    <form action="<c:url value="/Controller"/>" method="post" >
                                        <input type="hidden" name="command" value="change_offer_status_command">
                                        <input type="hidden" name="offer_id" value="${offer.id}">
                                        <c:if test="${offer.status == 'ACTIVE'}">
                                            <button type="submit" value="INACTIVE" name="status">InActive</button>
                                        </c:if>
                                        <c:if test="${offer.status == 'INACTIVE'}">
                                            <button type="submit" value="ACTIVE" name="status">Active</button>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.offers_list.size() == 0}">
                        <div class="empty-list">
                            ${emptyList}
                        </div>
                    </c:if>
                    <a href="<c:url value="/Controller?command=go_to_add_new_offer_page_command"/>">
                        <button>
                                ${header_addOffer}
                        </button>
                    </a>
                </div>
                <hr>
                <div class="user-option">
                    <div class="reservation-list">
                        <span class="info-helper">${reservationList}</span>
                    </div>
                    <c:if test="${requestScope.reservations_list.size() != 0}">
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
                                            ${reservation.totalPrice}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.reservations_list.size() == 0}">
                        <div class="empty-list">
                                ${emptyList}
                        </div>
                    </c:if>
                    <a href="<c:url value="/Controller?command=go_to_all_offers_page_user_command"/>">
                        <button>
                                ${header_book}
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
