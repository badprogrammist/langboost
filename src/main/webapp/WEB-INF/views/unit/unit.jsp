<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="${unit.title}">

    <h2>${unit.title}</h2>

    <p>
        ${unit.description}
    </p>

    <c:if test="${isEditable}">
        <form action="${pageContext.request.contextPath}/exercise/new" method="post">
            <select name="type">
                <c:forEach var="exerciseTypeItem" items="${exerciseTypes}">
                    <option value="${exerciseTypeItem.name()}">${exerciseTypeItem.title}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="unitId" value="${unit.id}">
            <button type="submit">Добавить</button>
        </form>
    </c:if>

    <ul>
        <form action="${pageContext.request.contextPath}/exercise/update/order" method="post">
            <c:forEach items="${exercises}" var="exercise">
                <li>
                    ${exercise.type.title}
                    <c:if test="${isEditable}">
                        <select name="order" >
                            <c:forEach var="exerciseIndex" begin="1" end="${exercises.size()}">
                                <option ${exerciseIndex == exercise.orderNumber ? ' selected ':''} value="${exercise.id}#${exerciseIndex}">${exerciseIndex}</option>
                            </c:forEach>
                        </select>
                        <a href="${pageContext.request.contextPath}/exercise/edit/${exercise.type.code}/${exercise.id}">Редактировать</a>
                    </c:if>
                    <ul>
                        <c:forEach items="${exercise.rules}" var="exerciseRule">
                            <li>
                                ${exerciseRule.rule.title}
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
            <input type="hidden" name="unitId" value="${unit.id}">
            <button type="submit">Сохранить сортировку</button>
        </form>
    </ul>



</layout:default>