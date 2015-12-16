<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="exercise" tagdir="/WEB-INF/tags/exercise" %>


<layout:default title="${course.title} -${unit.title}">

    <h1>${course.title}</h1>
    <h2>${unit.title}</h2>

    <form action="${pageContext.request.contextPath}/course/play/exercise/${exercise.type.code}/check" method="post">
        <exercise:exercise_type_chooser formType="input" exerciseType="${exercise.type}" performingResult="${performingResult}"/>
        <button type="submit">Подтвердить</button>
    </form>


</layout:default>