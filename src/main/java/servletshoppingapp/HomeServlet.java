package servletshoppingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		con= (Connection) config.getServletContext().getAttribute("setdb");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Statement s=null;
		ResultSet rs=null;
		PrintWriter pw=response.getWriter();
		try
		{
			RequestDispatcher rd=request.getRequestDispatcher("/header");
			rd.include(request, response);
			
						
			s=con.createStatement();
			rs=s.executeQuery("select * from category");
			
			while(rs.next())
			{
				
				pw.print("<a href='GetProductServlet?c_id="+rs.getInt(1)+"'>"+rs.getString(2)+"</a><br/>");
			}
			
			RequestDispatcher rd1=request.getRequestDispatcher("/footer");
			rd1.include(request, response);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				s.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
