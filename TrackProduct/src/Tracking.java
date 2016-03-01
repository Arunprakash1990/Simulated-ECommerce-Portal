

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.ArrayList;

// class to track products in a retail store.
	public class Tracking {
		public String[] trackProducts(int storeID)
		{
			String[] arr = new String[3];
			
			Connection con=null;
			Statement st=null;
			ResultSet rs = null;
			try {
					int i = 0;
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
				//Query to select products which are running under low stock.
				rs = st.executeQuery("SELECT PRODUCT_ID FROM RETAIL_STORE_STOCK WHERE STOCK_LEFT<15 AND STORE_ID="+storeID+";");
				System.out.println("table exists");
					while (rs.next()) {
						//Display the list of products from database
						arr[i]=rs.getString("product_id");
						System.out.println("@@@@@"+arr[i]);
						i++;
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
			
			return arr;
		}
		
		
		public String viewProductName(int product_ID)
		{	
			String name=null;
			Connection con=null;
			Statement st=null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
				String username = "root";
		        String password = "phoenix";
		        con = DriverManager.getConnection(connectionUrl,username,password);
		        st = con.createStatement();
				rs = st.executeQuery("SELECT PRODUCT_NAME FROM PRODUCT WHERE PRODUCT_ID="+product_ID+";");
					while (rs.next()) {
						name=rs.getString(1);
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
			return name;
		}
		//method to view product price given product ID
		public float viewProductPrice(int product_ID)
		{	
			float price=(float)0.00;
			Connection con=null;
			Statement st=null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
				String username = "root";
		        String password = "phoenix";
		        con = DriverManager.getConnection(connectionUrl,username,password);
		        st = con.createStatement();
				rs = st.executeQuery("SELECT PRICE FROM PRODUCT WHERE PRODUCT_ID="+product_ID+";");
					while (rs.next()) {
						price=rs.getFloat(1);
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
			return price;
		}
		
		//method to view product price given product ID
			public String viewProductBrand(int product_ID)
			{	
				String brand_name=null;
				Connection con=null;
				Statement st=null;
				ResultSet rs = null;
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
					String username = "root";
			        String password = "phoenix";
			        con = DriverManager.getConnection(connectionUrl,username,password);
			        st = con.createStatement();
					rs = st.executeQuery("SELECT BRAND_NAME FROM BRAND, PRODUCT WHERE BRAND.BRAND_ID=PRODUCT.BRAND_ID AND PRODUCT_ID="+product_ID+";");
						while (rs.next()) {
							brand_name=rs.getString(1);
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
				return brand_name;
			}
			
		//method to view product price given product ID
			public String viewProductCategory(int product_ID)
			{	
				String category_name=null;
				Connection con=null;
				Statement st=null;
				ResultSet rs = null;
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
					String username = "root";
			        String password = "phoenix";
			        con = DriverManager.getConnection(connectionUrl,username,password);
			        st = con.createStatement();
					rs = st.executeQuery("SELECT CATEGORY_NAME FROM CATEGORY, PRODUCT WHERE CATEGORY.CATEGORY_ID=PRODUCT.CATEGORY_ID AND PRODUCT_ID="+product_ID+";");
						while (rs.next()) {
							category_name=rs.getString(1);
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
				return category_name;
			}
			
	}