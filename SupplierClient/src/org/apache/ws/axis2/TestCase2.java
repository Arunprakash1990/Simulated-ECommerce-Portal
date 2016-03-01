package org.apache.ws.axis2;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCase2 {

	

	@Test
	public void TestCase()
	{
		assertTrue(TestStockCase()==true);
	}
	public boolean TestStockCase()
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs2 = null;
		try{
		CheckOrdersStub obj1=new CheckOrdersStub();
		CheckOrdersStub.UpdateStock updateStock2 = new CheckOrdersStub.UpdateStock();
		obj1.updateStock(updateStock2);
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
	    rs2 = st.executeQuery("SELECT DISTINCT(warehouse_id) from ordertosupplier");
	    if(rs2!=null)
	    {
	    	while(rs2.next())
	    	{ 
	    	// Acknowledgement that Warehousestock is updated.
	    	System.out.println(" Warehouse Stock updated for Warehouse ID : " + rs2.getString(1));
	    	}
	    }
	    
		}catch(Exception e)
		{}
		if(rs2!=null)
			return true;
		else
			return false;
	}
}
