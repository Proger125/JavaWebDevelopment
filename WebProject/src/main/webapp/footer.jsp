<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 22.07.2021
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="footer.copyright" var="footer_copyright"/>
<footer class="main-footer">
    <div class="container copyright-info">
        © WordFlat Inc., 2021 ${footer_copyright}
    </div>
    <div class=" social-networks">
        <div class="social-network-item">
            <a href="">
                <img src="<c:url value="/static/img/util/icons8-instagram-48.png"/>" alt="Instagram">
            </a>
        </div>
        <div class="social-network-item">
            <a href="">
                <img src="<c:url value="/static/img/util/icons8-vk-в-круге-48.png"/>" alt="VK">
            </a>
        </div>
        <div class="social-network-item">
            <a href="">
                <img src="<c:url value="/static/img/util/icons8-whatsapp-48.png"/>" alt="WhatsApp">
            </a>
        </div>
        <div class="social-network-item">
            <a href="">
                <img src="<c:url value="/static/img/util/icons8-телеграмма-app-48.png"/>" alt="Telegram">
            </a>
        </div>
    </div>
</footer>
