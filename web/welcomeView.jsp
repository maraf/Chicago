<%-- 
    Document   : welcomeView.jsp
    Created on : 22.3.2009, 20:03:07
    Author     : Marek SMM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ci" tagdir="/WEB-INF/tags/chicago/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<% if (request.getAttribute("controllerSet") == null) response.sendRedirect(application.getContextPath() + "/game"); %>

<fmt:bundle basename="chicago">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Chicago</title>
        <link rel="stylesheet" href="web.css" type="text/css" />
    </head>
    <body>
        <div class="chicago-game">
            <h1>Chicago!</h1>
            <h2><fmt:message key="welcome.title" /></h2>
            <c:if test="${(errorWelcomeMessage != null)}">
                <h4 class="error">${errorWelcomeMessage}</h4>
            </c:if>
            <div>
                <ci:playerInfoSet />
            </div>
        </div>
    </body>
</html>
</fmt:bundle>
