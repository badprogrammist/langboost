<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="${course.title}">

    <c:if test="${editable}">
        <ul>
            <li><a href="${pageContext.request.contextPath}/course/edit/${course.id}">Редактировать</a></li>
            <li><a href="${pageContext.request.contextPath}/unit/new/${course.id}">Добавить юнит</a></li>
        </ul>
    </c:if>


    <h2>${course.title}</h2>

    <p>
        ${course.description}
    </p>

    <p>
        <c:forEach var="unit" items="${units}">
            <h4><a href="${pageContext.request.contextPath}/unit/${unit.id}">${unit.title}</a></h4>
        </c:forEach>
    </p>


</layout:default>