<%@ page import="edu.epam.webproject.controller.command.RequestAttribute" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.07.2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="last_page" value="${PagePath.USER_ACCOUNT_PAGE}" scope="session"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../header.jsp"/>
user_account
<jsp:include page="../../footer.jsp"/>
</body>
</html>
