<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Store Manager Login </title>
</head>
<body>
 <h3>Warehouse Manager Login Page</h3>
 <!--  Warehouse manager login page -->
        <form name="smLogin" action="LoginVerification" method="get">
            Username<input type="text" name="usn"/>
            <br/>
            Password<input type="password" name="pwd"/>
            <br/>
            Warehouse ID : <input type="text" name="warehouseID"/>
            <br/>
            <br/>
            <input type="submit" value="Login"/>
            <input type="reset" value="Cancel"/>
            <!--  Form submitted and sent to Action page -->
        </form>      
</body>
</html>

<% 

		
	
		%>