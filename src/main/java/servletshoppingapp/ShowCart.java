package servletshoppingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowCart")
public class ShowCart extends HttpServlet {
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
		HttpSession session  = request.getSession();
		List<Integer> products = (List<Integer>)session.getAttribute("cart");
		if(products == null)
		{
			pw.println("<h3>No Products selected</h3>");
		}
		else
		{
			int x =0;
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			try
			{
				pw.print("<table border=1>");
				pw.print("<tr>");
				pw.print("<th>Sr No.</th>");
				pw.print("<th>Name</th>");
				pw.print("<th>Descp</th>");
				pw.print("<th>Price</th>");				
				pw.print("</tr>");
				
				ps = con.prepareStatement("select * from product where p_id = ?");
				float total = 0;
				for(int n : products)
				{
					ps.setInt(1, n);
					rs = ps.executeQuery();
					if(rs.next())
					{
						pw.print("<tr>");
						pw.print("<td>"+(++x)+"</td>");
						pw.print("<td>"+rs.getString(2)+"</td>");
						pw.print("<td>"+rs.getString(3)+"</td>");
						pw.print("<td>"+rs.getString(4)+"</td>");
						pw.print("<td> <a href='delete?pid="+n+"'> delete </a> </td>");
						total += Float.parseFloat(rs.getString(4));
						pw.print("</tr>");
					}
				}
				pw.print("<tr>");
				pw.print("<td colspan=3>Total Price</td>");
				pw.print("<td>"+total+"</td>");
				pw.print("</tr>");				
				pw.print("</table>");
				
				session.setAttribute("tprice", total);
				
				pw.print("<form action='home'>");
				pw.print("<input type='submit' value='Go For Further Selection' />");
				pw.print("</form>");
				
				pw.print("<form action='ConfirmOrder'>");
				pw.print("<input type='submit' value='ConfirmOrder' />");
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
//					rs.close();
					ps.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		

	}

}
