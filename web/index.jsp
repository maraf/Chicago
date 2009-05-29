<%-- 
    Document   : index
    Created on : 30.3.2009, 22:23:10
    Author     : Marek SMM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if(request.getAttribute("controllerSet") == null) response.sendRedirect(application.getContextPath() + "/game"); %>
