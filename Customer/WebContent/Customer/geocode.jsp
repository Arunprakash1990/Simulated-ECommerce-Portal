<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page  import="java.sql.Connection" %>
       <%@ page  import="java.sql.DriverManager" %>
       <%@ page  import="java.sql.ResultSet" %>
       <%@ page  import="java.sql.Statement" %>
       <%@ page  import="java.io.*" %>
       <%@ page import="java.util.ArrayList" %>
     <%@include file="connection.jsp" %>
      <%   
        ResultSet rs=null; 
      int locid=0;
double lat=0;
double lng=0;
          lat=Double.parseDouble(request.getParameter("lat"));
     lng=Double.parseDouble(request.getParameter("lng"));
     out.println(lat);
     out.println(lng);
     locid=Integer.parseInt(request.getParameter("locid")); 
  