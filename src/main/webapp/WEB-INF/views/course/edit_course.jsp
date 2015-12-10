<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Новый курс">


    <form action="${pageContext.request.contextPath}/course/update" method="post">
        <input type="text" placeholder="Введите название" name="title" value="${course.title}">
        <textarea placeholder="Введите описание" name="description">${course.description}</textarea>
        <input type="hidden" name="id" value="${course.id}">
        <button type="submit">Сохранить</button>
    </form>


</layout:default>