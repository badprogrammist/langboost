<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="fileId" required="true" %>
<%@attribute name="style" %>
<%@attribute name="styleClass" %>
<img style="${style}" class="${styleClass}" src="${pageContext.request.contextPath}/file/${fileId}"/>
