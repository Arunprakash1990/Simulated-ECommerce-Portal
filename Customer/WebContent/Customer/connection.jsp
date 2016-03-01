<%@page import="java.sql.Connection"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.sql.*" %> 
    <%
    Connection connection = null; 
	PreparedStatement p=null;
	PreparedStatement p1=null;
	PreparedStatement p2=null;
  String connectionURL = "jdbc:mysql://localhost:3306/customer"; 
   
// String connectionURL = "jdbc:mysql://10.7.140.14:3306/analytics"; 
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	connection = DriverManager.getConnection(connectionURL, "root","aaum");
	
//	out.println("connected");
	
    %>
    
