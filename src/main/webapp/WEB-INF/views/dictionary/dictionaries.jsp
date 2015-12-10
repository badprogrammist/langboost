<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Словарь">

    <form action="${pageContext.request.contextPath}/dictionary/save" method="post">
        <input type="text" placeholder="Введите название словаря" name="title">
        <button type="submit">Создать словарь</button>
    </form>

    <c:forEach var="dictionary" items="${dictionaries}">
        <a href="${pageContext.request.contextPath}/dictionary/${dictionary.id}">${dictionary.title}</a>
    </c:forEach>




</layout:default>