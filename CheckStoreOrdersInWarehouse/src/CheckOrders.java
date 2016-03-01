
import java.sql.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 */

/**
 * @author Smi
 *
 */
interface QueryConstants {
	/**
	 * 
	 */
	String LOGIN_USER = "select * from WAREHOUSE_ADMIN where USERNAME =? and PASSWORD=?";
	String GET_SUPPLIER_ORDERS = "select * from ORDER_TO_WAREHOUSE where WAREHOUSE_ID = ? AND STATUS = ?";
	String INSERT_ORDER = "insert into ORDER_TO_WAREHOUSE(ORDER_ID,WAREHOUSE_ID,STORE_ID,PRODUCT_ID,QUANTITY,PRICE,SUPPLYDATE,SHIPMENT_ID,STATUS) VALUES(?,?,?,?,?,?,'',1,'P')";
}
class StoreWarehouseOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7309441696124244481L;
	/**
	 * 
	 */
	public StoreWarehouseOrder() {
		// TODO Auto-generated constructor stub
	}

	private int orderId;
	private int warehouseId;
	private int storeId;
	private String productId;
	private int quantity;
	private int price;
	private Date date;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
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
	
	
}

class StoreToWarehouseOrder {

	/**
	 * 
	 */
	public StoreToWarehouseOrder() {
		// TODO Auto-generated constructor stub
	}
	
	private int orderId;
	private int warehouseId;
	private int storeId;
	private String productId;
	private int qty;
	private int price;
	private Date date;
	private int shipmentId;
	private String status;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
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
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	

}

public class CheckOrders {

	
	public StoreToWarehouseOrder[] getOrders(int warehouseId){
		Connection con = null;
		PreparedStatement stmt = null;
		StoreToWarehouseOrder[] ordersList = new StoreToWarehouseOrder[10];
		try {

		       Class.forName("com.mysql.jdbc.Driver");
		      
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");

			
			stmt = con.prepareStatement(QueryConstants.GET_SUPPLIER_ORDERS);
			stmt.setInt(1, warehouseId);
			stmt.setString(2, "P");
			ResultSet rs = stmt.executeQuery();
			
			for( int i=0; i<10; i++)
				ordersList[i]=null;
		
			if(rs != null){
				for( int i=0; i<10; i++){
					ordersList[i] = new StoreToWarehouseOrder();
					while(rs.next()){
						StoreToWarehouseOrder order = new StoreToWarehouseOrder();
						order.setOrderId(rs.getInt(1));
						order.setWarehouseId(rs.getInt(2));
						order.setStoreId(rs.getInt(3));
						order.setProductId(rs.getString(4));
						order.setQty(rs.getInt(5));
						order.setPrice(rs.getInt(6));
						order.setDate(rs.getDate(7));
						//order.setShipmentId(rs.getInt(8));
						order.setStatus(rs.getString(8));
						ordersList[i++]=order;
						System.out.println("iteration" + i);
					}
					
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		} 
		
		return ordersList;
	}

}

