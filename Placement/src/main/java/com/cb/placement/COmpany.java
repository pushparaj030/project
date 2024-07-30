package com.cb.placement;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/COmpany")
public class COmpany extends HttpServlet {
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
        	String query="select *from student where username=? and password=?";
        	psmt= con.prepareStatement(query);
        	psmt.setString(1, uname);
        	psmt.setString(2, password);
        	res = psmt.executeQuery();
        	res.next();
        	int tn= res.getInt(5);
        	int tl= res.getInt(6);
        	int cg= res.getInt(7);

        	String q="select*from company where 10th% <=? and 12th% <=? and cgpa <= ?";
        	PreparedStatement psmt2 = con.prepareStatement(q);
        	psmt2.setInt(1, tn);
        	psmt2.setInt(2, tl);
        	psmt2.setInt(3, cg);
        	ResultSet res =psmt2.executeQuery();
        	
        	resp.setContentType("text/html");
        	PrintWriter out= resp.getWriter();
			out.println("<h3>Hi "+ res.getString(2)+" your eligible companies !;</h3>");
		
		    String s="select company,salary from company";
		    Statement stmt = con.createStatement();
		    res= stmt.executeQuery(s);
		    out.println("<table border=1>\r\n"
		    		+"<tr>\r\n"
		    		+"<th>company</th>\r\n"
		    		+"<th>salary</th>\r\n"
		    		+"</tr>\r\n");
		    while(res.next()) {
		    	int com=res.getInt(1);
		    	int sal=res.getInt(5);
		    	out.println("<tr>\r\n"
		    			+"<td>"+com+"</td>\r\n"
		    			+"<td>"+sal+"</td>\r\n"
		    			+"</tr>/r/n");
		    }
		    out.println("</table>");	
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

}
