package servletshoppingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetProductServlet")
public class GetProductServlet extends HttpServlet {
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
		PrintWriter pw=response.getWriter();
		try
		{
			int cid=Integer.parseInt(request.getParameter("c_id"));
			ps=con.prepareStatement("select * from product where cat_id=?");
			ps.setInt(1, cid);
			rs=ps.executeQuery();
			
			pw.print("<form action='addtocart'>");
			pw.print("Select product : ");
			pw.print("<select name='prod'>");
			pw.print("<option> Select products </option>");

			while(rs.next())
			{
				pw.print("<option value='"+ rs.getInt(1)+ "'>"+rs.getString(2)+"</option>");
			}
			pw.print("</select> <br/>");
			pw.print("<input type='submit' value='Add to cart' />");
			pw.print("</form>");
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
