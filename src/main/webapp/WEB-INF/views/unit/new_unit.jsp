<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Новый юнит">

    <h2>${course.title}</h2>

    <form action="${pageContext.request.contextPath}/unit/save" method="post">
        <input type="text" placeholder="Введите название" name="title">
        <textarea placeholder="Введите описание" name="description"></textarea>
        <input type="hidden" name="courseId" value="${courseId}">
        <button type="submit">Добавить юнит</button>
    </form>


</layout:default>