<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
       <%@ page  import="java.sql.Connection" %>
       <%@ page  import="java.sql.DriverManager" %>
       <%@ page  import="java.sql.ResultSet" %>
       <%@ page  import="java.sql.Statement" %>
       <%@ page  import="java.io.*" %>
         <%@ page import="java.util.ArrayList" %>
       <%@ page import="java.util.Random"%>
       <%   int cat_id=0;
    %>
   <%

String sessionId =null;

sessionId = session.getId();

String categ = request.getParameter("categ");

System.out.println("**"+categ);
  
String na=null;
String addr1=null;
String prod_id=null;
na=request.getParameter("na");

String address=null;


System.out.println("**"+na);
String sname = null; 
String saddr = null;

%>
    <%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		System.out.println("into try");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("connected");
		String connectionUrl = "jdbc:mysql://localhost:3306/customer";
		System.out.println("into connect");
		String username = "root";
        String password = "aaum";
        System.out.println("verified");
		conn = DriverManager.getConnection(connectionUrl,username,password);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME='"+categ+"'");
		System.out.println("table exists");%>
		<%
	    while (rs.next()) {
	 	   
			cat_id=Integer.parseInt(rs.getString("CATEGORY_ID"));	
			
	    }
		
} catch (Exception e) {
	e.printStackTrace();
} 

	finally {
		try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
	}
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inventory Management System</title>
</head>
<script type="text/javascript" src="../Customer/jquery-1.2.6.min.js"></script>

<script type="text/javascript">
$(document).ready(function()
		{
	

	
	
	
	$("#submit").click(function()
			{
		

		var addr1=$('#addr1').val();
		var city=$('#city').val();
		var state=$('#state').val();
		var zip=$('#zip').val();
		var prod_id=$('prod_id').val();
	 	var sname=$('pass1').val();
	 	var saddr=$('pass').val();
	 	if(addr1==""||city=="" || state=="" || zip=="")
		{alert("Please enter the required fields");}
	else
		{
		  $.post("./geocode_cust.jsp" ,{'addr1': addr1,'prod_id':prod_id,'sname':sname,'saddr':saddr});}
		 
			});
			});
</script>
<body>
<form action="geocode_cust.jsp" method="post">
<table>
 
 <tr>
   <th>PRODUCT ID</th>
   <th>PRODUCT NAME</th>
   <th>QUANTITY</th>
   <th>PRICE</th>
 </tr>
    <%
    Connection conn1 = null;
	Statement stmt1 = null;
	ResultSet rs1 = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	String connectionUrl = "jdbc:mysql://localhost:3306/customer";
	String username = "root";
    String password = "aaum";
    System.out.println("verified");
	conn1 = DriverManager.getConnection(connectionUrl,username,password);
	stmt1 = conn1.createStatement();
	System.out.println("**"+cat_id);
		rs1 = stmt1.executeQuery("SELECT * FROM PRODUCT WHERE CATEGORY_ID='"+cat_id+"'");
		System.out.println("table product exists");%>
		<%
		
	    while (rs1.next()) {%>
	     <tr>
	    	 <td><input type="radio" id="prod_id" name="prod_id" value="<%=rs1.getString(1)%>"><%=rs1.getString(1)%></td>
	    	 <td><%=rs1.getString(2)%></td>
	    	 <td><%=rs1.getString(4)%></td>
	    	  <td>$<%=rs1.getString(5)%></td>
	    	 
 </tr>
			<%	    }
			
	
   
		}
		catch (Exception e) {
			e.printStackTrace();
		} 

			finally {
				try { if (rs != null) rs1.close(); } catch (Exception e) { e.printStackTrace(); }
				try { if (stmt != null) stmt1.close(); } catch (Exception e) { e.printStackTrace(); }
			}
   
   
   %>
   
   
</table> 
<h5>Enter address</h5>
Street Address1<font color="red">*</font>
<INPUT TYPE="TEXT" NAME="addr1" id="addr1" >
<BR>
Street Address2
<INPUT TYPE="TEXT" NAME="addr2" id="addr2">
<BR>
City<font color="red">*</font>
<INPUT TYPE="TEXT" NAME="city" id="city" >
<BR>
State<font color="red">*</font>
<INPUT TYPE="TEXT" NAME="state" id="state"  >
<BR>
Zip code<font color="red">*</font>
<INPUT TYPE="TEXT" NAME="zip" id="zip" >
<BR>
   <%
   prod_id=request.getParameter("prod_id");
	Connection conn2 = null;
	Statement stmt2= null;
	ResultSet rs2 = null;
	try {
		//System.out.println("into try");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		//System.out.println("connected");
		String connectionUrl = "jdbc:mysql://localhost:3306/customer";
		//System.out.println("into connect");
		String username = "root";
        String password = "aaum";
        //System.out.println("verified");
		conn2 = DriverManager.getConnection(connectionUrl,username,password);
		stmt2 = conn2.createStatement();
		rs2 = stmt2.executeQuery("SELECT name from RetailStore AS R WHERE StoreID IN(SELECT StoreID from RetailStoreStock as S WHERE PRODUCT_ID='"+prod_id+"')");
		System.out.println("table exists");%>
		<%
	    while (rs2.next()) {
	   
	    	sname=rs2.getString("name");
	    	%>	
	    	<%	
	    		    }
		
} catch (Exception e) {
	e.printStackTrace();
} 

	finally {
		try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
	}
%>
<input type="hidden" id="pass1" value="<%= sname%>" >
 <%
	Connection conn3 = null;
	Statement stmt3 = null;
	ResultSet rs3 = null;
	try {
		//System.out.println("into try");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		//System.out.println("connected");
		String connectionUrl = "jdbc:mysql://localhost:3306/customer";
		//System.out.println("into connect");
		String username = "root";
        String password = "aaum";
        //System.out.println("verified");
		conn3 = DriverManager.getConnection(connectionUrl,username,password);
		stmt3 = conn3.createStatement();
		rs3 = stmt1.executeQuery("SELECT addr1 from markers where name='"+sname+"'");
		System.out.println("table exists");%>
	  
    
	   <% while (rs3.next()) {
	    	saddr=rs3.getString("addr1");
	    	//out.println("T "+saddr);%>	
	    	<%
	    }
		
} catch (Exception e) {
	e.printStackTrace();
} 

	finally {
		try { if (rs1 != null) rs1.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (stmt1 != null) stmt1.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (conn1 != null) conn1.close(); } catch (Exception e) { e.printStackTrace(); }
	}
%>   
<input type="hidden" id="pass" value="<%= saddr%>" >
<INPUT TYPE="SUBMIT" value="Select Stores" id="submit" >
</form>
</body>
</html>