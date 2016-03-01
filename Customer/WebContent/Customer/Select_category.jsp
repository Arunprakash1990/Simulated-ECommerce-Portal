<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
       <%@ page  import="java.sql.Connection" %>
       <%@ page  import="java.sql.DriverManager" %>
       <%@ page  import="java.sql.ResultSet" %>
       <%@ page  import="java.sql.Statement" %>
       <%@ page  import="java.io.*" %>
       <%@ page import="java.util.Random"%>
       <%@include file="connection.jsp" %>
       

 <%
 int n=0;
 ResultSet rs=null;
String sessionId =null;

sessionId = session.getId();
n= (int) (Math.random() * 8372);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inventory Management System</title>
</head>
<script type="text/javascript" src="../Customer/jquery-1.2.6.min.js"></script>
<script type="text/javascript">



</script>
<body>
<!--  outer wrapper Starts here -->

<div id="wrapper" style="border:solid 0px; margin-top:0px;">
<form action="Shipping_details.jsp" method="post">

<h5>Your user ID is </h5><label><%=n %></label><INPUT TYPE="hidden" id="rand" NAME="text1" value=<%=n %>>
<h4>Enter your details</h4>


Category
<%
try {
	
	p=connection.prepareStatement("SELECT CATEGORY_NAME FROM CATEGORY");
	   		rs = p.executeQuery();%>
	   		<select name="categ" id="categ" ><%

		while (rs.next()) 
	{
	%>
		     <option><%=rs.getString("CATEGORY_NAME")%></option>
		
		<%	
	}
	
	

} catch (Exception e) {
	System.out.println(e);
}

%>
</select>

<br>

<INPUT TYPE="SUBMIT" value="Submit" id="submit" >
</form>
</div>

<!--  outer wrapper Starts end -->
</body>
</html>