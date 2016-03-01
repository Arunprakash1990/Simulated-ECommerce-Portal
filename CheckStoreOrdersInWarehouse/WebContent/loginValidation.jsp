<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page  import="java.sql.Connection" %>
       <%@ page  import="java.sql.DriverManager" %>
       <%@ page  import="java.sql.ResultSet" %>
       <%@ page  import="java.sql.Statement" %>
       <%@ page  import="java.io.*" %>
       <%@ page import="java.util.Random"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		String usn=request.getParameter("usn");
		String pwd=request.getParameter("pwd");
		String pwd1=null;
		System.out.println("into try");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("connected");
		String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
		System.out.println("into connect");
		String username = "root";
        String password = "ashwin92";
        con = DriverManager.getConnection(connectionUrl,username,password);
        System.out.println("verified");
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT PASSWORD FROM WAREHOUSE_ADMIN WHERE USERNAME='"+usn+"'");
		System.out.println("table exists");
			while (rs.next()) {
				System.out.println(rs.first());
	    	pwd1=rs.getString(1);
	    	}
	   if(pwd.equals(pwd1))
	   {
		   System.out.println("login successful"); 
		   out.println("Login Successful");
	   }
	   else
		   System.out.println("Failed");
} catch (Exception e) {
	e.printStackTrace();
} 

	finally {
		try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
	}
%>
</body>
</html>