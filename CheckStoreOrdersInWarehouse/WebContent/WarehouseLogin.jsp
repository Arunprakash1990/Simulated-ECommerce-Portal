<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- JSP Page to  -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Store Manager Login </title>
</head>
<body>
 <h3>Warehouse Manager Login Page</h3>
        <form action="loginValidation.jsp" method="post">
            Username<input type="text" name="usn"/>
            <br>
            Password<input type="password" name="pwd"/>
            <input type="submit" value="Login"/>
            <input type="reset" value="Cancel"/>
        </form>      
</body>
</html>