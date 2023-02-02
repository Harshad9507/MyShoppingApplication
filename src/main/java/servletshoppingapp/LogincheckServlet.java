package servletshoppingapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.User;

@WebServlet("/logincheck")
public class LogincheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	public void init(ServletConfig config) throws ServletException {
		con=(Connection) config.getServletContext().getAttribute("setdb");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			String uid=request.getParameter("uid");
			String pwd=request.getParameter("pwd");
			ps=con.prepareStatement("select * from users where u_id=? and password=?");
			
			ps.setString(1, uid);
			ps.setString(2, pwd);
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				HttpSession session = request.getSession();

				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				session.setAttribute("loggedinuser", user);

				
				RequestDispatcher rd=request.getRequestDispatcher("/home");
				rd.forward(request, response);
				
				Cookie[] ck=request.getCookies();
				if(ck!=null)
				{
					for(Cookie c:ck)
					{
						if(c.getName().equals("loginerror"))
						{
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
				}
					
			}
			else
			{
				
				Cookie c1=new Cookie("loginerror", "Invalid_plesase_try_again!!!!");
				response.addCookie(c1);
				
				
				response.sendRedirect("/ShoppingApplication/login.jsp");
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
