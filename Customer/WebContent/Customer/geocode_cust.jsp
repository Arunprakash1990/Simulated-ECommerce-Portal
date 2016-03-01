<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="agg" class="mongodb.Request" scope="session"></jsp:useBean>
       <%@ page  import="java.sql.Connection" %>
       <%@ page  import="java.sql.DriverManager" %>
       <%@ page  import="java.sql.ResultSet" %>
       <%@ page  import="java.sql.Statement" %>
       <%@ page  import="java.io.*" %>
       <%@ page import="java.util.ArrayList" %>
       <%@ page import="java.util.Date" %>
     <%@include file="connection.jsp" %>
 

        <%  
        String sessionId =null;

        sessionId = session.getId();
    
      
        ResultSet rs=null; 
        ResultSet rs1=null;
        ResultSet rs2=null; 
        ResultSet rs3=null;
     
 
int n=0;
double PIx = 3.141592653589793;
double RADIUS = 6378.16;
int qty=0;
int price=0;
        int[] prodStoreId=new int[10];
        Double[] storelat = new Double[20];
        Double[] storelong =new Double[20];
        String prodStore[]=new String[60];
        double dist=0;
        int d=0;
        String sid=null;
        String saddr=null;
        String addr1=null;
        String prod_id=null;
        String rand=null;
        String na=null;
        int fid=0;
        String fname=null;
        String loc=null;
        String city=null;
        String state=null;
        String zip=null;
        int locid=0;
        String odate=null;
double lat=0;
double lng=0;

city=request.getParameter("city");
state=request.getParameter("state");
zip=request.getParameter("zip");
qty=Integer.parseInt(request.getParameter("qty"));
        na=request.getParameter("text2");
       saddr=request.getParameter("saddr");
       addr1=request.getParameter("addr1");
       rand=request.getParameter("rand");
       na=request.getParameter("text2");
       prod_id=request.getParameter("prod_id");
       price=Integer.parseInt(request.getParameter("price"));
  lat=Double.parseDouble(request.getParameter("lat"));
     lng=Double.parseDouble(request.getParameter("lng"));

     locid=Integer.parseInt(request.getParameter("locid")); 
  
     Date date = new Date();
     odate=date.toString();
      %>

<%

try {
	 
	p=connection.prepareStatement("INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME,LOCATIONID,STREET_ADDRESS1,CITY,STATE,ZIP_CODE) VALUES ('"+rand+"','"+na+"','"+locid+"','"+addr1+"','"+city+"','"+state+"','"+zip+"')");
	   		p.executeUpdate();
	   

		System.out.println("inserted into customer");
	

} catch (Exception e) {
	System.out.println(e);
}
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
  <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script type="text/javascript">

    </script>


  </head>
<body>
<form action="place_order.jsp" method="post">
<%

try {
	
	p=connection.prepareStatement("SELECT LAT,LNG FROM LOCATION WHERE LOCATIONID IN (SELECT LOCATIONID from RetailStore AS R WHERE StoreID IN(SELECT StoreID from RetailStoreStock as S WHERE PRODUCT_ID='"+prod_id+"'))");
	System.out.println("into1");
	rs1 = p.executeQuery();
	int i =0;
	while (rs1.next()) 	
	{
		double x = new Double(rs1.getDouble(1));
		storelat[i]= new Double(x);
		double y = new Double(rs1.getDouble(2));
		storelong[i]= new Double(y);
		System.out.println(""+storelat[i]+""+storelong[i]);
		System.out.println("into");
		i=i+1;
	}

	
	p=connection.prepareStatement("SELECT StoreID,STORE FROM RetailStore WHERE LOCATIONID IN (SELECT LOCATIONID from RetailStore AS R WHERE StoreID IN(SELECT StoreID from RetailStoreStock as S WHERE PRODUCT_ID='"+prod_id+"'))");
	rs2 = p.executeQuery();
	i =0;
	while (rs2.next()) 	
	{
		prodStoreId[i]=rs2.getInt(1);
		prodStore[i]=rs2.getString(2);
		System.out.println(""+prodStoreId[i]+""+prodStore[i]);
	
   

	System.out.println("inserted into order");
		i++;
		
	}
	n=i;
	System.out.println(n);
} 
catch (Exception e) {
	e.printStackTrace();
}
%>

<p>The product is found in</p>
<%






	for(int i=0;i<n;i++){
	
	
	double dlon = (lng - storelong[i])* PIx / 180;
	double dlat = (lat - storelat[i])* PIx / 180;
	double a = (Math.sin(dlat / 2) * Math.sin(dlat / 2)) + Math.cos(lat* PIx / 180)*Math.cos(storelat[i]* PIx / 180) * (Math.sin(dlon / 2) * Math.sin(dlon / 2));
	double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	 dist= angle * RADIUS;
	
System.out.println(dist);	
	d=(int)dist;
	System.out.println(d);%>
	
    <tr>
    
  <% 	System.out.println("into this");
  if(d<25){%>
		    	 <td><input type="radio" id="store" name="store" value="<%=prodStoreId[i]%>"><%=prodStore[i]%> at distance <%=d %> miles from you</td>
		   	<br/>
 <% 	System.out.println("into this");}%>		    	 
	 </tr>


<%}
    agg.getconn();
%>
<INPUT TYPE="SUBMIT" value="Place Order" id="submit" >
</form>
</body>
 
</html>
