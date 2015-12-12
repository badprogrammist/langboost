<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="course" tagdir="/WEB-INF/tags/course" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<layout:default title="Курсы">

    <p>
        <sec:authorize access="hasRole('ROLE_AUTHOR')">
            <a href="${pageContext.request.contextPath}/course/new">Создать курс</a>
        </sec:authorize>
    </p>

    <p>
        <course:course_list coursesList="${courses}"/>
    </p>


</layout:default>