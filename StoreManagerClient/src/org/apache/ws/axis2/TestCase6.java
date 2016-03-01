package org.apache.ws.axis2;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.After;
import org.junit.Test;
import org.apache.ws.axis2.TrackingStub;
import org.apache.ws.axis2.TrackingStub.TrackProducts;
import org.apache.ws.axis2.OrderToWarehouseStub.OrderToWarehouse;
import org.apache.ws.axis2.OrderToWarehouseStub;
public class TestCase6 {

	@Test
	public void TestStock()
	{
		assertTrue(OrderStoreStock()==true);
	}
	
		boolean OrderStoreStock()
		{
			String result=null;
		try{
			String prodID="189138922X";

			String sid="1";
			System.out.println(sid);
			int storeID=Integer.parseInt(sid);
			System.out.println("Hello!");
			System.out.println("You are placing an order to your associated warehouse for the productID: " +prodID+"..");
			System.out.println("<html><body></br></br></body></html>");
			int wID=0;
			Connection con=null;
			Statement st=null;
			Statement stmt=null;
			ResultSet rs = null;
				
			
				//Database connectivity to connect to Retail Store database.
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
				String username = "root";
		        String password = "ashwin92";
		        con = DriverManager.getConnection(connectionUrl,username,password);
		        st = con.createStatement();
		        stmt=con.createStatement();
		        con.setAutoCommit(false);
				rs = st.executeQuery("SELECT WAREHOUSE_ID FROM RETAIL_STORE WHERE STORE_ID="+storeID+";");
					while (rs.next()) {
						wID=rs.getInt("WAREHOUSE_ID");
			    	}
					
					//Call to Web Service to place order to warehouse
				OrderToWarehouseStub obj=new OrderToWarehouseStub();
				OrderToWarehouse orderToWarehouse0=new OrderToWarehouse();
				orderToWarehouse0.setProductID(prodID);
				orderToWarehouse0.setStoreID(storeID);
				orderToWarehouse0.setWarehouseID(wID);
				orderToWarehouse0.setQty(200);
				result=obj.orderToWarehouse(orderToWarehouse0).get_return();
System.out.println(result);
		}catch(Exception e ){}
		
		if(result!=null)
			return true;
		else 
			return false;
}
		
}
