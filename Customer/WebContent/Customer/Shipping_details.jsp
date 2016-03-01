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

       ResultSet rs=null; 
       ResultSet rs1=null;
       ResultSet rs2=null;
       int cat_id=0;
       int locid=0;
       String prod_id=null;
       String sname = null; 
       String saddr = null;
       locid=(int) (Math.random() * 347);
      String rand=request.getParameter("text1");
       String categ = request.getParameter("categ");
      int price=0;
       System.out.println(""+rand+""+categ);
    %>
      <%

String sessionId =null;

sessionId = session.getId();

%>
<%
try {
	
	p=connection.prepareStatement("SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME='"+categ+"'");
	   		rs = p.executeQuery();
	   

		while (rs.next()) 
	{

			cat_id=Integer.parseInt(rs.getString("CATEGORY_ID"));	
			
	}
	
	

} catch (Exception e) {
	System.out.println(e);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Customer/jquery-1.2.6.min.js"></script>
<title>Inventory Management System</title>
</head>
<script src="https://maps.googleapis.com/maps/api/js"
            type="text/javascript"></script>

<script type="text/javascript">
function search(add){
		var address=add;
	alert(address);		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({address: address}, function(results, status) {

	if (status == google.maps.GeocoderStatus.OK) {
	 	var centre = results[0].geometry.location;
	var lat= centre.lat();
	var lng = centre.lng();

	var e = document.getElementById('testForm'); e.action='geocode_cust.jsp?lat='+lat+'&lng='+lng; e.submit();				
} else {
  alert(address + ' not found');
	}
		});
	}
function generateAddress()
{
    document.getElementById('address').innerText = 
        document.getElementById('addr1').value + ' ' + 
        document.getElementById('city').value+ ' ' + 
        document.getElementById('state').value+ ' ' + 
        document.getElementById('zip').value;
    alert(document.getElementById('address').innerText);
}

$(document).ready(function()
		{
	
	//alert("into jquery");
	
	
	
	$("#prod_id").click(function()
			{

		$("#qty_h").show();
				$("#qty").show();
			});
	
	
	
		});
</script>
<body>
<form action="geocode_cust.jsp" method="post" id="testForm">
<table>
 
 <tr>
   <th>PRODUCT ID</th>
   <th>PRODUCT NAME</th>
   <th>PRICE</th>
   <th id="qty_h">QUANTITY</th>
 </tr>
<%
try {
	
	p=connection.prepareStatement("SELECT * FROM PRODUCT WHERE CATEGORY_ID='"+cat_id+"'");
	   		rs1 = p.executeQuery();
	   

		while (rs1.next()) 
	{
			%>
		     <tr>
		    	 <td><input type="radio" id="prod_id" name="prod_id" value="<%=rs1.getString(1)%>"><%=rs1.getString(1)%></td>
		    	 <td><%=rs1.getString(2)%></td>
		    	
		    	  <td>$<%=rs1.getString(5)%></td>
		    	  <% price=Integer.parseInt(rs1.getString(5));%>
<td><center><select name="qty" id="qty" value="qty">
				<option  selected="qty" >qty</option>
		    	  <option>1</option>
		    	  <option>2</option>
		    	  <option>3</option>
		    	  <option>4</option>
		    	  <option>5</option>
		    	  </center></td>
 </tr>		    	  
		    	 
	
				<%	 
	}
	
	

} catch (Exception e) {
	System.out.println(e);
}
%>

</table> 
<h5>Enter Shipping Details</h5>
Name
<INPUT TYPE="TEXT" id="na" NAME="text2">
<BR>
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
<INPUT TYPE="TEXT" NAME="zip" id="zip" onblur="generateAddress()" >
<BR>

<INPUT TYPE="HIDDEN" NAME="locid" value=<%=locid%> >
<input type="hidden" id="address" name="address" >
<input type="hidden" id="price" name="price" value=<%=price %>>
<input type="hidden" id="rand" name="rand" value=<%=rand%> >
 	<input type="hidden" id="lng" name="lng" value="${lng}">
<input type="hidden" id="lat" name="lat" value="${lat}">
	<input type="button" name="btn" value="Select Stores" onclick="search(document.getElementById('address').innerText)">
</form>

</body>
</html>