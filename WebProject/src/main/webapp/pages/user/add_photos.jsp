<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.08.2021
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.GO_TO_ADD_PHOTOS_TO_OFFER_BY_ID}" scope="session"/>
<c:if test="${empty requestScope.offer_id}">
    <c:redirect url="add_new_offer.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.uploadFiles" var="uploadFiles"/>
<html>
<head>
    <title>Add photos</title>
    <link rel="stylesheet" href="<c:url value="/static/css/util.css"/> ">
    <link rel="shortcut icon" href="<c:url value="/static/img/util/page_logo.jpg"/>"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<div class="data">
    <div class="add_photos_form">
        <form action="upload" enctype="multipart/form-data" method="post">
            <div class="offer-photo_adding">
                <input type="hidden" name="command" value="add_photos_to_offer_by_id">
                <input type="hidden" name="offer_id" value="${requestScope.offer_id}">
                <div class="offer-photo-adding-img">
                    <label for="content">
                        <img src="<c:url value="/static/img/util/icons8-upload-80.png"/> " alt="Загрузить">
                    </label>
                </div>
                <input type="file" id="content" name="content" height="130" multiple>
                <br>
                <input type="submit" value="${uploadFiles}">
            </div>
        </form>
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
