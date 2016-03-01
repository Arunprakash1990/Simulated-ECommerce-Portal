<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.TrackingStub" %>
<%@ page import="org.apache.ws.axis2.TrackingStub.TrackProducts" %> 


<!--  JSP Page to display store manager client profile and order low stock products. -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<title>Welcome Store Manager</title>
</head>

<body>
<%  String usn=(String) request.getAttribute("user"); 
	int sID=(Integer)request.getAttribute("sID");
	out.println("@"+sID);
	out.println("<html><body><h3>Welcome "+usn+"</h3></body></html>");
	out.println("<h4>Tracking product stock..</h4>");
	//Tracking products under low stock in Retail Store
	TrackingStub obj1=new TrackingStub();
	TrackProducts trackProducts0=new TrackProducts();
    trackProducts0.setStoreID(sID);
    String[] ar= obj1.trackProducts(trackProducts0).get_return();
    if(ar.length>0)
    {
    out.println("The following products are low on stock:");
    for (int i=0;i<ar.length;i++)
    {
    	if(ar[i]!=null)
    	{
    		String x=ar[i];
    		out.println("ProductID: "+x);
    		//out.println("<html><body><form name='form1' action='orderProduct.jsp' method='post'><a href='orderProduct.jsp'>Click here to order product this product</a></body></html>");
    		out.println("<html><body><a href='orderProduct.jsp?param="+x+"&param1="+sID+"'>click</a></body></html>");
    	}
    }
    }
    else
    out.println("No product is low on stock :)");	
    
%>

</body>
</html>