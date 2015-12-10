<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="${dictionary.title}">

    <h2>${dictionary.title}</h2>

    <form action="${pageContext.request.contextPath}/word/save" method="post">
        <input type="text" placeholder="Введите слово" name="content">
        <input type="hidden" name="dictionaryId" value="${dictionary.id}">
        <button type="submit">Добавить слово</button>
    </form>

    <p>
        <c:forEach var="word" items="${words}">
            ${word.content}
        </c:forEach>
    </p>


</layout:default>