<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
    
    
<!--  JSP page to request stock to Supplier from warehouse -->    
<% 
Connection con = null;
Statement stmt = null;
ResultSet rs = null;

try {
	String pwd1=null;
	System.out.println("into try");
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("connected");
	String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
	System.out.println("into connect");
	String username = "root";
    String password = "ashwin92";
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
    System.out.println("verified");
	Statement st = con.createStatement();
		String s = (String)session.getAttribute("warehouseID");
			int s1 = Integer.parseInt(s);
		Statement st2 = con.createStatement();
			String query = "select * from warehousestock where warehouse_id="+s1+" AND STOCK_LEFT<200";
			rs = st.executeQuery(query);
			%>
			<html>
			<body>
			<table border=1>
			<tr><td> Product ID </td><td> Product Name </td> <td> Stock Left </td></table></body></html>
			</tr>
		<%
			while(rs.next())
			{%> <tr> <%
			%> <td><%=rs.getString(2)%> </td>  <%
			
			String query2 = "select product_name from product where product_id='"+rs.getString(2)+"'";
			
			ResultSet rs2 = st2.executeQuery(query2);
			rs2.next();
			%>
			<td><%=rs2.getString(1)%> <% 
			//System.out.println("Product_Name="+rs2.getString(1));
			%> </td> <td> <%=rs.getString(3) %><td/> </tr><br/> <br/>
			<% 
		
			}
} catch(Exception e)
{	e.printStackTrace();
}

    
    %>
    <!--  Form to request stock of products under low stock -->
    <html>
    <body>
    <form name="form1" action="order3.jsp" method="get">
    <input type="submit" name="submit" value="Request Stock to Supplier" ></form>
    </body></html>