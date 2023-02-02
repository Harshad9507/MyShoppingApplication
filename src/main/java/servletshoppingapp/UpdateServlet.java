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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		con=(Connection) config.getServletContext().getAttribute("setdb");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("em");
		String contact=request.getParameter("ct");
		System.out.println(email);
		System.out.println(contact);
		HttpSession session=request.getSession();
		User u=(User) session.getAttribute("loggedinuser");
		u.setEmail(email);
		u.setContact(contact);
		
		PreparedStatement ps=null;
		PrintWriter pw=response.getWriter();
		try
		{
			
			
			ps=con.prepareStatement("update users set email=?,contact=? where u_id=?");
			
			ps.setString(1,email);
			ps.setString(2,contact);
			ps.setString(3,u.getUid());
			
			int n=ps.executeUpdate();
			pw.print(n+"records updated sucessfully");
			
			response.sendRedirect("home");
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
