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
import java.sql.*;
import java.io.PrintWriter;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class LoginVerification
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
		PrintWriter out = response.getWriter();
		try {
			String usn=request.getParameter("usn");
			String pwd=request.getParameter("pwd");
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
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT password FROM supplier WHERE username='"+usn+"'");
			System.out.println("table exists");
				while (rs.next()) {
				   	pwd1=rs.getString(1);
				   	System.out.println(pwd1);
		    	}
		   if(pwd.equalsIgnoreCase(pwd1))
		   {
			   System.out.println("login successful");
			   request.setAttribute("user",usn);
			   RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
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
