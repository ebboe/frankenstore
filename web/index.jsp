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
        
        <%
            if (request.getParameterMap().containsKey("iaintgotnoaccount") && !request.getParameter("iaintgotnoaccount").isEmpty()) {
        %>

        <h2>New account</h2>
        Enter new account details below:
        
        <form method="POST" action="">
            <table>
                <tr>
                    <td align="right">
                        Username:
                    </td>
                    <td>
                        <input type="text">
                    </td>
                    <td>
                        <input type="submit" value="Create account!">
                    </td>
                </tr>
            </table>
        </form>
        
                
        <%
            }
            else {
        %>
        
        <h2>Login</h2>
        
        <form method="POST" action="">
            <table>
                <tr>
                    <td align="right">
                        Username:
                    </td>
                    <td>
                        <input type="text">
                    </td>
                    <td>
                        <input type="submit" value="Go shop!">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <a href="?iaintgotnoaccount=1">I ain't got no account!</a>
                    </td>
                </tr>
            </table>
        </form>
        
        <%
            }
        %>
    </body>
</html>
