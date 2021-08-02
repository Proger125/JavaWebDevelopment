<%@ page import="edu.epam.webproject.controller.command.RequestAttribute" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.07.2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getSession().setAttribute(RequestAttribute.LAST_PAGE, PagePath.ADMIN_ACCOUNT_PAGE);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../all/header.jsp"/>
admin_account
<jsp:include page="../all/footer.jsp"/>
</body>
</html>
