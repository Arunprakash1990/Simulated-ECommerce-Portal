package org.apache.ws.axis2;

import static org.junit.Assert.fail;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import org.apache.ws.axis2.CheckOrdersStub.GetOrders;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestCase1 {

	

	
	
	@Test
	public void CheckTest()
	{
		assertTrue(CheckOrder()==true);
	}
	
	public boolean CheckOrder()
	{
		CheckOrdersStub.WarehouseOrder[] ar=null;
		try{
		String usn="test"; 
		System.out.println("\n inside welcome");
		CheckOrdersStub obj1=new CheckOrdersStub();
		GetOrders getOrders0=new GetOrders();
			
		//Calling Webservice to get order reuqests from warehouses.
		ar= obj1.getOrders(getOrders0).get_return();
		System.out.println("Display Array");
		
		
	  //  out.println("The following are the requests from Warehouses <br/>");
	    java.sql.Connection con = null;
	    Statement st = null;
	    ResultSet rs2 = null;

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
	    	st = con.createStatement();
	     rs2 = st.executeQuery("SELECT COUNT(DISTINCT(ORDER_ID)) from ordertosupplier where status='P'");
		if(rs2!=null){
			rs2.next();
	System.out.println("\n You have " + rs2.getString(1) + " Requests for supplies");
				
		 Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
		 Statement st2 = con2.createStatement();
		 
		for( int i=0; i<10; i++)
		{ 
			
			if(ar[i].getOrder_ID()!=0){
	
			ar[i].getWarehouse_ID();
			
		
		
			
			CheckOrdersStub.Product[] temp= ar[i].getPro();
			
			for( int j=0; temp[j]!=null; j++)
			{
				
				temp[j].getProduct_ID(); 
						
				ResultSet rs = st2.executeQuery("select product_name from product where product_ID='"+temp[j].getProduct_ID()+"'"); 
				rs.next();
				rs.getString(1);
				temp[j].getQuantity();
			}
		
		}
		
		}
		}
		
		else
			System.out.println("No Requests");
		}finally {}
		}catch(Exception e)
		{ e.printStackTrace(); }
		
		if(ar!=null)
			return true;
		else
			return false;
		
	}
}
