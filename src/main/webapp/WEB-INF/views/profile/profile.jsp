<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="course" tagdir="/WEB-INF/tags/course" %>
<layout:default title="Мой профиль">

    <sec:authorize access="isAuthenticated()">
        <h3>Мои курсы</h3>
        <p>
            <c:if test="${courses != null and !courses.isEmpty()}">
                <course:course_list coursesList="${courses}"/>
            </c:if>
            <c:if test="${courses == null or courses.isEmpty()}">
                У Вас нет курсов
            </c:if>
        </p>
    </sec:authorize>


</layout:default>

