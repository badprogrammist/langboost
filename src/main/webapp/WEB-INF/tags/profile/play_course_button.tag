<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="courseId" required="true" %>

<form action="${pageContext.request.contextPath}/course/play" method="post">
    <input type="hidden" name="courseId" value="${course.id}">
    <button type="submit">Пройти курс</button>
</form>

