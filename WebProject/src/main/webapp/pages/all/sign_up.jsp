<%@ page import="edu.epam.webproject.controller.command.RequestAttribute" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 26.07.2021
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="last_page" value="${PagePath.SIGN_UP_PAGE}" scope="session"/>
<jsp:include page="../../header.jsp"/>
<div class="form-div">
    <div class="form-wrapper">
        <form action="../../Controller" method="post" class="form">
            <input type="hidden" name="command" value="sign_up_command">
            <div class="form-item">
                <label for="login-input">Login</label>
                <br>
                <input type="text" id="login-input" name="login" />
            </div>
            <div class="form-item">
                <label for="email-input">Email</label>
                <br>
                <input type="text" id="email-input" name="email"/>
            </div>
            <div class="form-item">
                <label for="password-input">Password</label>
                <br>
                <input type="password" id="password-input" name="password" />
            </div>
            <div class="submit-button-wrapper">
                <button type="submit">Enter</button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../../footer.jsp"/>
<c:if test="${sessionScope.incorrectData}">
    <script>
        alert("Login should contains only small letters, -, _ and digits. Password should contains at least one capital letter, one small letter and digits.");
    </script>
</c:if>
</body>
</html>
