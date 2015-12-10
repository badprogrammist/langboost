<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Курсы">

    <p>
        <a href="${pageContext.request.contextPath}/course/new">Создать курс</a>
    </p>

    <p>
        <c:forEach var="course" items="${courses}">
            <a href="${pageContext.request.contextPath}/course/${course.id}">${course.title}</a>
        </c:forEach>
    </p>


</layout:default>