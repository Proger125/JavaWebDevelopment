<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.08.2021
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="list-container-wrapper">
        <div class="list-container">
            <c:forEach items="${requestScope.users_list}" var="user">
                <div class="custom-user">
                    <div class="custom-user-photo">
                        <c:if test="${user.icon == null}">
                            <img src="<c:url value="static/img/users/default.jpg"/> " class="user-photo-img" alt="Default image">
                        </c:if>
                        <c:if test="${user.icon != null}">
                            <img src="${user.icon}" alt="User image">
                        </c:if>
                    </div>
                    <div class="custom-user-info">

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
