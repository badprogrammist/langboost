<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<sec:authorize  access="hasRole('ROLE_ADMIN')">

</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <sec:authentication var="principal" property="principal" />
    <%--<common:image style="height:30px; width:30px;" styleClass="img-circle" fileId="${principal.userData.icon.id}"/>--%>
    ${principal.username}
</sec:authorize>
<ul>
    <sec:authorize access="hasRole('ROLE_USER')">
        <li><a href="${pageContext.request.contextPath}/">Профиль</a></li>
        <li><a href="${pageContext.request.contextPath}/dictionary">Словарь</a></li>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_AUTHOR','ROLE_ADMIN')">
        <li><a href="${pageContext.request.contextPath}/">Главная</a></li>
        <li><a href="${pageContext.request.contextPath}/rules">Правила</a></li>
    </sec:authorize>

    <li><a href="${pageContext.request.contextPath}/courses">Курсы</a></li>

    <sec:authorize access="isAuthenticated()">
        <li>
            <form action="${pageContext.request.contextPath}/logout">
                <button type="submit">Выйти</button>
            </form>
        </li>
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
            <li><a href="${pageContext.request.contextPath}/login">Вход</a></li>
            <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
    </sec:authorize>
</ul>


