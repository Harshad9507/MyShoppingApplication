package servletshoppingapp;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.User;

import java.util.Date;
import java.sql.Timestamp;

@WebServlet("/ConfirmOrder")
public class ConfirmOrderSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("setdb");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw =response.getWriter();
		HttpSession session =request.getSession();
		pw.print("<br/>Your order is confirmed");
		pw.print("<br/>Thanks for shopping with us !!!");
		User user = (User)session.getAttribute("loggedinuser");
		pw.print("<br/>Your E-Bill will be mailed on your Email : "+user.getEmail() );
		pw.print("<br/>You will receive msg before delivery on "+user.getContact());
		
		pw.print("</br><a href='logout'>LOGOUT</a>");
		
		
		String uid = user.getUid();
		Timestamp date = new Timestamp(new Date().getTime());
		
		float total = (Float)session.getAttribute("tprice");
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into shopping(user_id,shoppingDate,totalprice) values(?,?,?)");
			
			
			ps.setString(1, uid );
			ps.setTimestamp(2,date);
			ps.setFloat(3, total );
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		
	}



}
