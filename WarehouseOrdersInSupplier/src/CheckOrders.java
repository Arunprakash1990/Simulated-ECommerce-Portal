import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/* This is the class which offers the web service to check Warehouse orders in the supplier side */ 

//Product class
class Product {

	private String product_ID;
	private int quantity;
	private int price;
	public String getProduct_ID() {
		return product_ID;
	}
	public void setProduct_ID(String product_ID) {
		this.product_ID = product_ID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String toString() {
        String display = " Product ID : " + product_ID + " \n Quantity : " + quantity + " \n Price : " + price + " \n\n\n";
        return display;
    }
	
	
}

// WarehouseOrder class to map warehouse orders to an object
class WarehouseOrder {
	
	private int order_ID;
	private int warehouse_ID;
	private Product[] pro=new Product[50];
	private String OrderDate;
	private int ShipmentID;
	public int getOrder_ID() {
		return order_ID;
	}
	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}
	public int getWarehouse_ID() {
		return warehouse_ID;
	}
	public void setWarehouse_ID(int warehouse_ID) {
		this.warehouse_ID = warehouse_ID;
	}
	public Product[] getPro() {
		return pro;
	}
	public void setPro(Product[] pro) {
		for( int i=0; pro[i]!=null; i++)
			this.pro[i] = pro[i];
	}
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public int getShipmentID() {
		return ShipmentID;
	}
	public void setShipmentID(int shipmentID) {
		ShipmentID = shipmentID;
	}
	
}


//Actual class for the web service
public class CheckOrders {
	
	
		// Array to store the list of warehouse orders from various warehouses.
		private WarehouseOrder[] ord = new WarehouseOrder[10];
		
		
		public WarehouseOrder[] getOrders()
		{
			try{
				//Conection to database to retrieve list of orders for supplier from various warehouses.
				System.out.println("\n Going to retrieve order for supplier");
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("\n Step 3 inside Process Order");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
				java.sql.Statement st = con.createStatement();
				String query = "select * from ordertosupplier where status='P'";
				ResultSet rs = st.executeQuery(query);
				Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
				Statement st2 = con2.createStatement();
				
				System.out.println("\n Step 4 inside Process Order");
				int i=0;
				for( int x = 0; x< 10; x++){
					ord[x]=new WarehouseOrder();}
				
				Product[] pr = new Product[200];
				// Operations to retrieve data from database and storing them in the object.
				rs.next(); 
				int temp = Integer.parseInt(rs.getString("order_id"));
				int j=0;	
				while(rs.next())
				{	
					if(Integer.parseInt(rs.getString("order_ID"))== temp)
					{
						temp = Integer.parseInt(rs.getString("order_ID"));
						System.out.println("iteration" + i + " OrderID " + rs.getString("Order_ID") );
						ord[i].setOrder_ID(temp);
						ord[i].setWarehouse_ID(Integer.parseInt(rs.getString("WAREHOUSE_ID")));
						Product pr2 = new Product();
						pr2.setProduct_ID(rs.getString("PRODUCT_ID"));
						
						pr2.setQuantity(Integer.parseInt(rs.getString("QUANTITY")));
						pr2.setPrice(Integer.parseInt(rs.getString("PRICE")));
						pr[j]=pr2;
						j=j+1;
						ord[i].setOrderDate(rs.getString("ORDERDATE"));
						ord[i].setShipmentID(Integer.parseInt(rs.getString("SHIPMENT_ID")));
													
					}
					
					else{
						//mapping orders of objects of different warehouses based on their warehouse ID
						j=0;
						temp = Integer.parseInt(rs.getString("order_ID"));
						ord[i].setPro(pr);
						
						for( int k =0; k<50 ; k++)
							pr[i]=null;
						
						temp = Integer.parseInt(rs.getString("ORDER_ID"));
						i=i+1;
						ord[i].setOrder_ID(temp);
						ord[i].setWarehouse_ID(Integer.parseInt(rs.getString("WAREHOUSE_ID")));
						Product pr2 = new Product();
						pr2.setProduct_ID(rs.getString("PRODUCT_ID"));
						pr2.setQuantity(Integer.parseInt(rs.getString("QUANTITY")));
						pr2.setPrice(Integer.parseInt(rs.getString("PRICE")));
						pr[j++]=pr2;
						ord[i].setOrderDate(rs.getString("ORDERDATE"));
						ord[i].setShipmentID(Integer.parseInt(rs.getString("SHIPMENT_ID")));
						System.out.println("\n Step 6 inside Process Order");
					 	
					} 
					
				}
				ord[i].setPro(pr);
			
				
				
				
				System.out.println("\n Step 7 inside Process Order");
				//System.out.println("Requests processed");
			//System.out.println("value of i is : " + i);
				
				
				//UpdateStock(ord, i);
				
				System.out.println("End of method");
				Product[] s = ord[0].getPro();
				System.out.println("\n First order first product: " + s[0].getProduct_ID());
			}catch(Exception e) {
				e.printStackTrace();
			}
			return ord;
		}

		
		public void UpdateStock(WarehouseOrder ord[],int x)
		{
			//Method to update stock for the particular warehouse.
			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("\n Step 3 inside Process Order and number of orders are :" + x);
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
				java.sql.Statement st = con.createStatement();
				Product[] pro = new Product[50];
				String query;
				String query2;
				//Connecting to Warehouse stock database.
				Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
				Statement st2 = con2.createStatement();
				for(int i=0; i<=x;i++)
				{
					pro = ord[i].getPro();
					for(Product p: pro)
					{
						// Fucntionality to update stock in database.
						//System.out.println("\n inside product loop");
						query = "update warehouseStock set stock_left= stock_left + " + p.getQuantity() + " where PRODUCT_ID = '"+p.getProduct_ID()+"' AND WAREHOUSE_ID="+ord[i].getWarehouse_ID();
						//query2 = "update ordertosupplier set status='X' where order_ID"
						st.executeUpdate(query);
					}
					System.out.println("Stock for Warehouse with ID " + ord[i].getWarehouse_ID() + " was updated");
				}
				st.executeUpdate("commit");
				//System.out.println("Updated");
			}
			catch(Exception e){
				e.printStackTrace();
			
			}
		}
	}



	

