<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="exercise" required="true" type="ru.langboost.domain.course.exercise.TextMatchingExercise" %>

<textarea name="target" placeholder="Английский текст">${exercise.target}</textarea>
<textarea name="translate" placeholder="Русский текст">${exercise.translate}</textarea>
