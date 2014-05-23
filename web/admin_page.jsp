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
      <table border=0>
        <tr>
          <td>
              <p>Name of Franken: </p>
          </td>
          <td>
              <input type="text" name="name" value="${franken.name}">
          </td>
          <td>
              <input type="submit" action="store?action=save_franken" value="Create">
          </td>
        </tr>
        <tr>
            <x:transform xslt="<c:import url="create_franken_xslt.xsl"/>">
                ${bodyPartList.xml}
            </x:transform>
        </tr>
       </table>
    </body>
</html>
