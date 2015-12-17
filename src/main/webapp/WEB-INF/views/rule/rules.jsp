<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default title="Правила">

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <form action="${pageContext.request.contextPath}/rule/save" method="post">
            <input type="text" placeholder="Введите название правила" name="title">
            <textarea name="content" placeholder="Введите формулировку"></textarea>
            <button type="submit">Создать правило</button>
        </form>
    </sec:authorize>


    <c:forEach var="rule" items="${rules}">
        <a href="${pageContext.request.contextPath}/rule/${rule.id}">${rule.title}</a>
    </c:forEach>




</layout:default>