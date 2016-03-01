package org.apache.ws.axis2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class LoginVerification - to verify login credentials of Retail Store Manager 
 */
public class LoginVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//PrintStream out= new PrintStream(response.getOutputStream());
		try {
			String usn=request.getParameter("usn");
			String pwd=request.getParameter("pwd");
			String pwd1=null;
			int storeID=0;
			System.out.println("into try");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("connected");
			String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
			System.out.println("into connect");
			String username = "root";
	        String password = "ashwin92";
	        con = DriverManager.getConnection(connectionUrl,username,password);
	        System.out.println("verified");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT PASSWORD,STORE_ID FROM STORE_ADMIN WHERE USERNAME='"+usn+"'");
			System.out.println("table exists");
				while (rs.next()) {
				   	pwd1=rs.getString("password");
				   	storeID=rs.getInt("store_id");
				   	System.out.println(pwd1);
				   	System.out.println("#####"+storeID);
		    	}
		   if(pwd.equalsIgnoreCase(pwd1))
		   {
			   System.out.println("login successful");
			   request.setAttribute("user",usn);
			   request.setAttribute("sID", storeID);
			   RequestDispatcher rd=request.getRequestDispatcher("storeprofile.jsp");
			   System.out.println("###########");
			   rd.forward(request, response);
		   }	
		   else
			   System.out.println("Failed");
	} catch (Exception e) {
		e.printStackTrace();
	} 

		finally {
			try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
		}
	}

}
