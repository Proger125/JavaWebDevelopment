<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.08.2021
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.GO_TO_ALL_OFFERS_PAGE}" scope="session"/>

<c:if test="${empty requestScope.offers_list && sessionScope.role == 'ADMIN'}">
    <c:redirect url="../admin/admin_account.jsp"/>
</c:if>
<c:if test="${empty requestScope.reservations_list && sessionScope.role == 'USER'}">
    <c:redirect url="../user/user_account.jsp"/>
</c:if>

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
<fmt:message key="account.follow" var="follow"/>
<html>
<head>
    <title>Offers</title>
    <link rel="stylesheet" href="<c:url value="/static/css/users_style.css"/> ">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<div class="list-container-wrapper">
    <div class="list-container">
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
                            ${offer.address.country}, ${offer.address.city}, ${offer.address.street} ${address_street}, ${offer.address.houseNumber}, ${address_flat} ${offer.address.apartmentNumber}
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
                <c:if test="${sessionScope.role == 'ADMIN'}">
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
                </c:if>
                <c:if test="${sessionScope.role == 'USER'}">
                    <div class="user-functions">
                        <form action="<c:url value="/Controller"/>" method="post" >
                            <input type="hidden" name="command" value="go_to_offer_page_command">
                            <input type="hidden" name="offer_id" value="${offer.id}">
                            <button type="submit">
                                ${follow}
                            </button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
