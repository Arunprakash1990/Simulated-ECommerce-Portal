package org.apache.ws.axis2;

import static org.junit.Assert.*;
import org.apache.ws.axis2.CheckOrdersStub.GetOrders;
import java.sql.*;
 import org.apache.ws.axis2.CheckOrdersStub;
 import org.apache.ws.axis2.CheckOrdersStub.GetOrders;
 import org.apache.ws.axis2.StoreToWarehouseOrder;

import org.junit.After;
import org.junit.Test;

public class TestCase4 {
	
	

	@Test
	public void StockTest()
	{
		assertTrue(UpdateStockTest()==true);
	}
	public boolean UpdateStockTest(){
		
		CheckOrdersStub.StoreToWarehouseOrder[] ar=null;
		try {
	CheckOrdersStub obj1=new CheckOrdersStub();
	GetOrders getOrders0=new GetOrders();
	getOrders0.setWarehouseId(1);
ar= obj1.getOrders(getOrders0).get_return();

Connection con = null;
Statement stmt = null;
ResultSet rs = null;


	String pwd1=null;
	System.out.println("into try");
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("connected");
	String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
	System.out.println("into connect");
	String username = "root";
    String password = "ashwin92";
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
			Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory","root","ashwin92");
			Statement st2 = con2.createStatement();
			Statement st3 = con.createStatement();
    System.out.println("verified");
	stmt = con.createStatement();
			//SQL Query to update retail store stock for specific Retail Stores based on orders in warehouse.
			for( int i=0; ar[i]!=null; i++)
			{	
				String query ="update retail_store_stock set stock_left=stock_left + "+ar[i].getQty()+ " AND STORE_ID="+ar[i].getStoreId();
				String query2="update warehousestock set stock_left=stock_left - "+ar[i].getQty() + " AND WAREHOUSE_ID="+ar[i].getWarehouseId()+" AND PRODUCT_ID='"+ar[i].getProductId()+"'";
				String query3="update order_to_warehouse set status='X' AND STORE_ID="+ar[i].getStoreId();
				stmt.executeUpdate(query);
				st2.executeUpdate(query2);
				st3.executeUpdate(query3);
				System.out.print("Stock updated for Store with StoreID :" + ar[i].getStoreId());
			}
			
			
}catch(Exception e) {
	e.printStackTrace();

}
	if(ar!=null)
		return true;
	else
		return false;
	}
}
