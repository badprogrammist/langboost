<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Упражнение для юнита - ${exercise.unit.title}">

    <h2>${exercise.unit.title}</h2>
    <p>${exercise.type.title}</p>

    <form action="${pageContext.request.contextPath}/exercise/update/${exercise.type.code}" method="post">
        <textarea name="target" placeholder="Английский текст">${exercise.target}</textarea>
        <textarea name="translate" placeholder="Русский текст">${exercise.translate}</textarea>
        <input type="hidden" name="exerciseId" value="${exercise.id}">
        <button type="submit">Сохранить</button>
    </form>

</layout:default>