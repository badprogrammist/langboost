<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="performingResult" required="true" type="ru.langboost.domain.course.exercise.TextMatchingPerformingResult" %>

<p>${performingResult.exercise.translate}</p>
<textarea name="performingText" placeholder="Английский текст">${performingResult.performingText}</textarea>
