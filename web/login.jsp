<%-- 
    Document   : login
    Created on : May 12, 2014, 10:47:18 AM
    Author     : farshid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FrankenStore -- We'll give you an arm and a leg</title>
    </head>
    <body>
        <h1>FrankenStore</h1>
        <p>Welcome to the FrankenStore, where we have all your needs 
            when it comes to post-apocalyptic members and limbs. We have legs, 
            arms, eyes, feet, toes, fingers, ears, hairs and last but not least,
            butts.</p>
        <p>Kindly login below, or if you do not have a user you can create one.</p>
        <form method="POST" action="store?action=login">
            <table>
                <tr>
                    <th align="right">Username:</th>
                    <td align="left"><input type="text" name="username"></td>
                    <td><input type="submit" value="Login"></td>
                </tr>
                <tr>
                    <td colspan="2"><a href="store?action=create_use©r">I ain't got no account</a>
                </tr>
            </table>
        </form>
    </body>
</html>
