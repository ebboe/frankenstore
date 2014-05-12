<%-- 
    Document   : frankenlist
    Created on : 2014-maj-12, 11:05:20
    Author     : Ebbe
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="beans.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Frankenstore</title>
    </head>
    <body>
        <h1>Frankenstore</h1>
        <jsp:useBean id="frankenList" class="beans.FrankenListBean" scope="application">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
        <p Welcome 
            
    <c:set var="frankenlist_xslt">
        <c:import url=""frankenlist_xslt.xsl"/>
    </c:set>
    
    <x:transform xslt=""${frankenlist_xslt}">
        <jsp:getProperty name="frankenList" property="xml"/>
    </x:transform>
    </body>
</html>
