<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="exercise" tagdir="/WEB-INF/tags/exercise" %>


<layout:default title="Упражнение для юнита - ${exercise.unit.title}">

    <h2>${exercise.unit.title}</h2>
    <p>${exercise.type.title}</p>

    <form action="${pageContext.request.contextPath}/exercise/update/${exercise.type.code}" method="post">
        <exercise:exercise_type_chooser formType="form" exerciseType="${exercise.type}" exercise="${exercise}"/>


        <c:forEach var="rule" items="${rules}">
            <c:set var="checked" value="${false}"/>
            <c:forEach var="exerciseRule" items="${exerciseRules}">
                <c:if test="${exerciseRule.rule.id eq rule.id}">
                    <c:set var="checked" value="${true}"/>
                </c:if>
            </c:forEach>
            <input type="checkbox" name="rules" value="${rule.id}" ${checked ? ' checked ':''} >
            <label>${rule.title}</label>
        </c:forEach>

        <input type="hidden" name="exerciseId" value="${exercise.id}">
        <button type="submit">Сохранить</button>
    </form>

</layout:default>