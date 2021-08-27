<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 10.08.2021
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%@ page import="edu.epam.webproject.validator.ValidatorRegExp" %>
<c:set var="last_page" value="${PagePath.ADD_NEW_OFFER_PAGE}" scope="session"/>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/Controller?command=go_to_about_page_command"/>
</c:if>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="offer.country" var="country"/>
<fmt:message key="offer.city" var="city"/>
<fmt:message key="offer.street" var="street"/>
<fmt:message key="offer.houseNumber" var="houseNumber"/>
<fmt:message key="offer.apartmentNumber" var="apartmentNumber"/>
<fmt:message key="offer.description" var="description"/>
<fmt:message key="offer.price" var="price"/>
<fmt:message key="offer.photos" var="photos"/>
<fmt:message key="offer.create" var="create_offer"/>
<fmt:message key="error.duplicateAddress" var="duplicateAddress"/>
<fmt:message key="error.incorrectOfferData" var="incorrectOfferData"/>
<html>
<head>
    <title>Add new offer</title>
    <link rel="stylesheet" href="<c:url value="/static/css/util.css"/>">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<div class="data">
    <div class="add-new-offer-form">
        <form action="<c:url value="/Controller"/>" method="post">
            <input type="hidden" name="command" value="add_new_offer_command">
            <div class="form-block">
                <div class="form-item">
                    <label for="country-input">${country}:</label>
                    <br>
                    <input type="text" id="country-input" name="country" pattern="${ValidatorRegExp.ADDRESS_STRING_REGEXP}">
                </div>
                <div class="form-item">
                    <label for="city-input">${city}:</label>
                    <br>
                    <input type="text" id="city-input" name="city" pattern="${ValidatorRegExp.ADDRESS_STRING_REGEXP}">
                </div>
                <div class="form-item">
                    <label for="street-input">${street}:</label>
                    <br>
                    <input type="text" id="street-input" name="street" pattern="${ValidatorRegExp.ADDRESS_STRING_REGEXP}">
                </div>
                <div class="form-item">
                    <label for="houseNumber-input">${houseNumber}:</label>
                    <br>
                    <input type="text" id="houseNumber-input" name="house_number" ${ValidatorRegExp.ADDRESS_NUMBER_REGEXP}>
                </div>
                <div class="form-item">
                    <label for="apartmentNumber-input">${apartmentNumber}:</label>
                    <br>
                    <input type="text" id="apartmentNumber-input" name="apartment_number" pattern="${ValidatorRegExp.ADDRESS_NUMBER_REGEXP}">
                </div>
            </div>
            <div class="form-block">
                <div class="form-item">
                    <label for="description-input">${description}:</label>
                    <br>
                    <textarea id="description-input" name="description" rows="10" cols="30" ></textarea>
                </div>
                <div class="form-item">
                    <label for="price-input">${price}</label>
                    <br>
                    <input type="text" id="price-input" name="price_per_day" pattern="${ValidatorRegExp.ADDRESS_NUMBER_REGEXP}">
                </div>
            </div>
            <button type="submit">${create_offer}</button>
        </form>
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
<c:if test="${sessionScope.duplicate_address == true}">
    <script>
        alert("${duplicateAddress}");
    </script>
</c:if>
<c:if test="${sessionScope.incorrectData == true}">
    <script>
        alert("${incorrectOfferData}");
    </script>
</c:if>
</body>
</html>
