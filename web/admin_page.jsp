<%-- 
    Document   : admin_page
    Created on : May 23, 2014, 10:45:01 AM
    Author     : farshid
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new Franken</title>
    </head>
    <body>
        <h1>Create a new Franken here!</h1>
        <strong>Select the body parts that should be part of this Franken.</strong>
        <form action="store?action=add_new_part" method="POST">
            <table>
                <tr>
                <input type="text" name="frankenName" value="${franken.name}">
                </tr>
                <tr>
                    <td>
                        <select name="bodypart">
                            ${bodyPartList.html}
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="Add to Franken">
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <br>
        <form action="store?action=save_franken" method="POST"><input type="submit" value="Save Franken"></form>
    </body>
</html>
