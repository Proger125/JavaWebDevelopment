<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.RequestAttribute" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.07.2021
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="last_page" value="${PagePath.ABOUT_PAGE}" scope="session"/>
<!doctype html>
<html>
<body>
<jsp:include page="../../header.jsp"/>

<div class="custom-slider autoplay">
    <div class="custom-slider-item">
        <img src="../../static/img/util/carousel_img_1.jpg" alt="First slider item">
    </div>
    <div class="custom-slider-item">
        <img src="../../static/img/util/carousel_img_2.jpg" alt="Second slider item">
    </div>
    <div class="custom-slider-item">
        <img src="../../static/img/util/carousel_img_3.jpg" alt="Third slider item">
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
