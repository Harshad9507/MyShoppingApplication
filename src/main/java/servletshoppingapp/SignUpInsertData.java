package servletshoppingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpInsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		con=(Connection) config.getServletContext().getAttribute("setdb");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		String fname=request.getParameter("fn");
		String mname=request.getParameter("mn");
		String lname=request.getParameter("ln");
		String email=request.getParameter("em");
		String contact=request.getParameter("ct");
		
		PreparedStatement ps=null;
		
		
		try
		{
			ps=con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
			ps.setString(1,uid);
			ps.setString(2,pwd);
			ps.setString(3,fname);
			ps.setString(4,mname);
			ps.setString(5,lname);
			ps.setString(6,email);
			ps.setString(7,contact);
			
			int n=ps.executeUpdate();
			pw.print(n+"records inserted");
			pw.print("</br><a href='login.jsp'>LOGIN</a>");
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
