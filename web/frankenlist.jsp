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
        <title>FrankenStore: List of Frankens</title>
    </head>
    <body>
        <jsp:useBean id="frankenList" class="beans.FrankenListBean" scope="application" />
        <c:set var="frankenlist_xslt">
            <c:import url="frankenlist_xslt.xsl"/>
        </c:set>

        <x:transform xslt="${frankenlist_xslt}">
            <jsp:getProperty name="frankenList" property="xml"/>
        </x:transform>
        <br><br>
        
        <c:set var="shoppingcart_xslt">
            <c:import url="shoppingcart_xslt.xsl"/>
        </c:set>

        <x:transform xslt="${shoppingcart_xslt}">
            <jsp:getProperty name="shoppingCart" property="xml"/>
        </x:transform>
    </body>
</html>
