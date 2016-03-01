
import java.util.Date;
import java.io.Serializable;
import java.sql.*;

/**
 * @author Smi
 
 */
interface QueryConstants {
	/**
	 * 
	 */
	String LOGIN_USER = "select * from WAREHOUSE_ADMIN where USERNAME =? and PASSWORD=?";
	String GET_SUPPLIER_ORDERS = "select * from ORDER_TO_WAREHOUSE where WAREHOUSE_ID = ? AND STATUS = ?";
	String INSERT_ORDER = "insert into ORDER_TO_WAREHOUSE(ORDER_ID,WAREHOUSE_ID,STORE_ID,PRODUCT_ID,QUANTITY,PRICE,SUPPLYDATE,SHIPMENT_ID,STATUS) VALUES(?,?,?,?,?,?,'',1,'P')";
}

class Product implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

class StoreOrder {

	/**
	 * 
	 */
	public StoreOrder() {
		// TODO Auto-generated constructor stub
	}
	private int orderId;
	private int warehouseId;
	private int storeId;
	private Product[] products;
	private Date date;
	private int shipmentId;
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the warehouseId
	 */
	public int getWarehouseId() {
		return warehouseId;
	}
	/**
	 * @param warehouseId the warehouseId to set
	 */
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the products
	 */
	public Product[] getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(Product[] products) {
		this.products = products;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the shipmentId
	 */
	public int getShipmentId() {
		return shipmentId;
	}
	/**
	 * @param shipmentId the shipmentId to set
	 */
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	

}

public class SetStoreOrder{
	
	
public void insertStoreOrder(StoreOrder s)  {
		Connection con = null;
		PreparedStatement stmt = null;
		Product[] prods = new Product[200];
		prods = s.getProducts();
		for (Product product : prods) {
			
			if(product!=null){
			try {
				 Class.forName("com.mysql.jdbc.Driver");
			      
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");

				stmt = con.prepareStatement(QueryConstants.INSERT_ORDER);
				stmt.setInt(1, s.hashCode());
				stmt.setInt(2,s.getWarehouseId());
				stmt.setInt(3,s.getStoreId());
				stmt.setString(4, product.getProduct_ID());
				stmt.setInt(5, product.getQuantity());
				stmt.setInt(5, product.getPrice());
				stmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			} 
			}
		}
		}		
	}

