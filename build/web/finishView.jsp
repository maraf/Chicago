<%-- 
    Document   : finishView
    Created on : 23.3.2009, 12:34:11
    Author     : Marek SMM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ci" tagdir="/WEB-INF/tags/chicago/" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<% if(request.getAttribute("controllerSet") == null) response.sendRedirect(application.getContextPath() + "/game"); %>

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
            <p>
               <fmt:message key="finish.title" />
            </p>
            <div>
                <ci:playerInfo2 />
            </div>
            <div>
                <ci:topN n="${sessionScope.howMany}" />
            </div>
        </div>
    </body>
</html>
</fmt:bundle>