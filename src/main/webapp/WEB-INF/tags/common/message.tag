<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${messages != null and !messages.isEmpty()}">
    <c:forEach items="${messages}" var="message">
        <c:if test="${message.content != null and !message.content.isEmpty()}">
            <div class="alert alert-${message.type.toLowerCase()}" role="${message.type.toLowerCase()}">${message.content}</div>
        </c:if>
    </c:forEach>
</c:if>
