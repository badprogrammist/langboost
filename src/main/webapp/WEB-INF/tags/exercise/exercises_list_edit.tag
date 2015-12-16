<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="exercisesList" required="true" type="java.util.List" %>

<ul>
    <c:if test="${exercises != null and !exercises.isEmpty()}">
        <form action="${pageContext.request.contextPath}/exercise/update/order" method="post">
            <c:forEach var="exercise" items="${exercisesList}">
                <li>
                        ${exercise.type.title}
                    <c:if test="${isEditable}">
                        <select name="order">
                            <c:forEach var="exerciseIndex" begin="1" end="${exercises.size()}">
                                <option ${exerciseIndex == exercise.orderNumber ? ' selected ':''}
                                        value="${exercise.id}#${exerciseIndex}">${exerciseIndex}</option>
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
    </c:if>
</ul>

