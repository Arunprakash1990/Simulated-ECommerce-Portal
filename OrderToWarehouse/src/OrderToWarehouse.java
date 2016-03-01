import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class OrderToWarehouse {
public String orderToWarehouse(int storeID, String productID, int warehouseID, int qty)
{
	Connection con=null;
	Statement st=null;
		
	try {
		System.out.println("into try");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("connected");
		String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
		System.out.println("into connect");
		String username = "root";
        String password = "ashwin92";
        con = DriverManager.getConnection(connectionUrl,username,password);
        System.out.println("verified");
		st = con.createStatement();
		st.executeUpdate("insert into order_to_warehouse(warehouse_ID,store_id,product_ID,Qty,Price,SupplyDate,status) values("+warehouseID+", "+storeID+", '"+productID+"', "+qty+", 50,'2014-10-06','P');");
        return "Order has been placed for the productID: "+productID;
	}
	catch (Exception e) {
		e.printStackTrace();
		} 

	finally {
		
		try { if (st != null) st.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
	}
	return "";	
}
}
