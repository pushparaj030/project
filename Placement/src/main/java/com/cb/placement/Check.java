package com.cb.placement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Check extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement psmt= null;
	ResultSet res = null; 
	String url=null;
	String un = null;
	String pwd= null;
	public void init(ServletConfig sc) throws ServletException{
		ServletContext scon = sc.getServletContext();
		url=scon.getInitParameter("url");
		un=scon.getInitParameter("username");
		pwd=scon.getInitParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("url","un","pwd");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=req.getParameter("username");
		String password=req.getParameter("password");
        try {
        	String query="select *from students where username=? and password=?";
        	psmt= con.prepareStatement(query);
        	psmt.setString(1, uname);
        	psmt.setString(2, password);
        	res = psmt.executeQuery();
        	if(res.next()) {
        		resp.sendRedirect("Student");
        	}
        	else {
        		resp.sendRedirect("/invalid.html");
        	}
        	
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

}
