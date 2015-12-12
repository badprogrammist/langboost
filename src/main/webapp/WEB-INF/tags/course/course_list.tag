<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="coursesList" required="true" type="java.util.List" %>

<c:forEach var="course" items="${coursesList}">
    <a href="${pageContext.request.contextPath}/course/${course.id}">${course.title}</a>
</c:forEach>


