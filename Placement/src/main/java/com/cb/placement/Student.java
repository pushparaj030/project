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

@WebServlet("/Student")
public class Student extends HttpServlet {
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
	
		PrintWriter out= resp.getWriter();
		try {
			out.println("<h3>Welcome "+res.getString(2)+"!;</h3>");
		
		    String s="select*from students";
		    Statement stmt = con.createStatement();
		    res= stmt.executeQuery(s);
		    out.println("<table border=1>\r\n"
		    		+"<tr>\r\n"
		    		+"<th>id</th>\r\n"
		    		+"<th>name</th>\r\n"
		    		+"<th>10th%</th>\r\n"
		    		+"<th>12th%</th>\r\n"
		    		+"<th>cgpa</th>\r\n"
		    		+"</tr>\r\n");
		    while(res.next()) {
		    	int id=res.getInt(1);
		    	int nm=res.getInt(2);
		    	int tn=res.getInt(5);
		    	int tl=res.getInt(6);
		    	int cg=res.getInt(7);
		    	out.println("<tr>\r\n"
		    			+"<td>"+id+"</td>\r\n"
		    			+"<td>"+nm+"</td>\r\n"
		    			+"<td>"+tn+"</td>\r\n"
		    			+"<td>"+tl+"</td>\r\n"
		    			+"<td>"+cg+"</td>\r\n"
		    			+"</tr>/r/n");
		    }
		    out.println("</table>");
		    req.getRequestDispatcher("/COmpany").include(req,resp);
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

}
