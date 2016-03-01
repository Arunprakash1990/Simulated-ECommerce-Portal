import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Web Service to place customer order to Retail Store.
public class PlaceOrderToStore {
	public String placeOrder(int customerID, int storeID, String productID, int requiredQty){
		int quantity=0;
		Connection con=null;
		Statement st=null;
		Statement stmt=null;
		ResultSet rs = null;
		DateFormat df=new SimpleDateFormat("YYYY/MM/DD");
		java.util.Date d=new java.util.Date();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
			String username = "root";
	        String password = "ashwin92";
	        con = DriverManager.getConnection(connectionUrl,username,password);
	        st = con.createStatement();
	        stmt=con.createStatement();
	        con.setAutoCommit(false);
	        //Order of product will be checked against the existing stock before placing the order.
			rs = st.executeQuery("SELECT STOCK_LEFT FROM RETAIL_STORE_STOCK WHERE STORE_ID="+storeID+" AND PRODUCT_ID='"+productID+"';");
				while (rs.next()) {
					quantity=rs.getInt("STOCK_LEFT");
		    	}
				if(quantity>requiredQty)
				{	
					//Placing of customer order into appropriate order table
					String sql1="INSERT INTO PLACEORDER(CUSTOMER_ID,STORE_ID,PRODUCT_ID,QUANTITY,ORDER_DATE,STATUS) VALUES("+customerID+", "+storeID+" ,'"+productID+"', "+requiredQty+", '"+df.format(d)+"', 'PROCESSED');";
					int stockLeft=quantity-requiredQty;
					String sql2="UPDATE RETAIL_STORE_STOCK SET STOCK_LEFT="+stockLeft+" WHERE STORE_ID="+storeID+" AND PRODUCT_ID='"+productID+"';";
					stmt.addBatch(sql1);
					stmt.addBatch(sql2);
					int[] var=stmt.executeBatch();
					con.commit();
					return "Your Order has been placed successfully!";
				}
				else
				{
					String sql1="INSERT INTO PLACEORDER(CUSTOMER_ID,STORE_ID,PRODUCT_ID,QUANTITY,ORDER_DATE,STATUS) VALUES("+customerID+", "+storeID+" ,'"+productID+"', "+requiredQty+", '"+df.format(d)+"', 'PENDING');";
					stmt.execute(sql1);
					return "Sorry..Product found to be unavailable! please try later"; 
				}
			} 
		
		catch (Exception e) {
			e.printStackTrace();
			} 
	
		finally {
			try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (st != null) st.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
		}
	return "";
}
	
}
