<%@ page import="edu.epam.webproject.controller.command.RequestAttribute" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.07.2021
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    request.getSession().setAttribute(RequestAttribute.LAST_PAGE, PagePath.ABOUT_PAGE);
%>
<html>
<head>
    <link href="../../static/css/about.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="custom-slider">
    <div class="custom-slider-item">
        a
    </div>
    <div class="custom-slider-item">
        b
    </div>
    <div class="custom-slider-item">
        c
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
