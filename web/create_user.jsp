<%-- 
    Document   : create_user
    Created on : May 12, 2014, 10:47:29 AM
    Author     : farshid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Frankenstore: Create new user</title>
    </head>
    <body>
        <h1>Please create your Frankenstore profile. 
      </h1>
      <table border=0>
	<form action=shop?action=usercreate method=post>
      <tr>
	<td>Username:</td>
      <td> <input type="text" name="user" value="${profile.username}" ></td>
      </tr>
	<tr>
	<td>Select roles:</td>
        <td><input type="checkbox" name="role" value="${profile.role}"> </td>
      </table> 
	<input type="submit" value="Go">
      </form>
        
        <%-- <h1>Hello World!</h1>
        Luke, I am your <strong>${ profile.getUsername() }</strong> (ID: ${ profile.getId() }).
        --%>
    </body>
</html>
