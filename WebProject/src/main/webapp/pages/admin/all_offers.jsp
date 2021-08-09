<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.08.2021
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<c:set var="last_page" value="${PagePath.GO_TO_ALL_OFFERS_PAGE}" scope="session"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
