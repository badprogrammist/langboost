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

    <p>Статистика по юниту</p>

    <c:if test="${hasNextUnit}">
        <form action="${pageContext.request.contextPath}/course/play/unit/next" method="post">
            <button type="submit">Следующий юнит</button>
        </form>
    </c:if>

</layout:default>