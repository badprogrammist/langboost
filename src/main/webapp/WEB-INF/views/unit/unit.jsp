<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="exercise" tagdir="/WEB-INF/tags/exercise" %>


<layout:default title="${unit.title}">

    <h2>${unit.title}</h2>

    <p>
        ${unit.description}
    </p>

    <c:if test="${isEditable}">
        <form action="${pageContext.request.contextPath}/exercise/new" method="post">
            <select name="type">
                <c:forEach var="exerciseTypeItem" items="${exerciseTypes}">
                    <option value="${exerciseTypeItem.name()}">${exerciseTypeItem.title}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="unitId" value="${unit.id}">
            <button type="submit">Добавить</button>
        </form>
    </c:if>

    <c:if test="${isEditable}">
        <exercise:exercises_list_edit exercisesList="${exercises}"/>
    </c:if>
    <c:if test="${!isEditable}">
        <exercise:exercises_list_view exercisesList="${exercises}"/>
    </c:if>


</layout:default>