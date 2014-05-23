<%-- 
    Document   : update_user
    Created on : 23-May-2014, 10:46:48
    Author     : Lewyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FrankenStore: Update user</title>
    </head>
    <body>
        <h1>Please update your FrankenStore profile. 
      </h1>
      <table border=0>
	<form action="store?action=update_user" method="post">
      <tr>
	<td>Username:</td>
      <td> <input type="text" name="user" value="${profile.username}" ></td>
      </tr>
	<tr>
	<td>Administrator?</td>
        <td><input type="checkbox" name="role" value="${profile.role}"> </td>
      </table> 
	<input type="submit" value="Go">
      </form>
        
        <%-- <h1>Hello World!</h1>
        Luke, I am your <strong>${ profile.getUsername() }</strong> (ID: ${ profile.getId() }).
        --%>
    </body>
</html>
