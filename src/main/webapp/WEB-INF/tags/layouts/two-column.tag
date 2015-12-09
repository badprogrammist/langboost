<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@attribute name="title" %>
<%@attribute name="left" fragment="true" %>
<layout:default title="${title}">

    <jsp:invoke fragment="left"/>

    <jsp:doBody/>

</layout:default>