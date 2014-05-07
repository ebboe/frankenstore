<%-- 
    Document   : index
    Created on : 2014-maj-07, 13:56:41
    Author     : Ebbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Frankenstore</title>
    </head>
    <body>
        <h1>Frankenstore</h1>
        <jsp:useBean id="User" class="beans.ProfileBean" scope="request">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
    </body>
</html>
