<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="partial" tagdir="/WEB-INF/tags/partials" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@attribute name="title" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/picedit/picedit.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dropzone/dropzone.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>${title}</title>
    </head>
    <body>
        <jsp:directive.include  file="/WEB-INF/tags/partials/default-header.tag"/>
        <common:message/>
        <jsp:doBody/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/intercooler.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/picedit/picedit.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/dropzone/dropzone.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
    </body>
</html>