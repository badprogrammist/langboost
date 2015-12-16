<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="exercise" tagdir="/WEB-INF/tags/exercise" %>

<%@attribute name="formType" required="true" type="java.lang.String" %>
<%@attribute name="exerciseType" required="true" type="ru.langboost.domain.course.exercise.ExerciseType" %>

<%@attribute name="performingResult" type="ru.langboost.domain.course.exercise.AbstractPerformingResult" %>

<c:if test="${formType eq 'input'}">
    <c:if test="${exerciseType.code eq 'tm'}">
        <exercise:tm_player_input performingResult="${performingResult}" />
    </c:if>
</c:if>

