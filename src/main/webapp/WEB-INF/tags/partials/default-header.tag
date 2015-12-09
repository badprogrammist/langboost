<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<sec:authorize  access="hasRole('ROLE_ADMIN')">

</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="principal" property="principal" />
    <common:image style="height:30px; width:30px;" styleClass="img-circle" fileId="${principal.userData.icon.id}"/> ${principal.username}

    <form action="${pageContext.request.contextPath}/logout">
        <button type="submit">Выйти</button>
    </form>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <ul>
        <li><a href="${pageContext.request.contextPath}/login">Вход</a></li>
        <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
    </ul>
</sec:authorize>

