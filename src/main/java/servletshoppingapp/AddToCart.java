package servletshoppingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		int selectedP =Integer.parseInt(request.getParameter("prod"));
		HttpSession session = request.getSession();
		List<Integer> products = null;		
		products = (List<Integer>)session.getAttribute("stock");
		
		if(products == null)
			products = new ArrayList<>();
		products.add(selectedP);
		session.setAttribute("cart", products);
		pw.print("Select product id : "+selectedP+" is added in the cart <br/>");
		pw.print("No of selected products : "+products.size());
		
		pw.print("<form action='ShowCart'>");
		pw.print("<input type='submit' value='@ShowCart'/>");
		pw.print("</form>");

		pw.print("<form action='home'>");
		pw.print("<input type='submit' value='Select another items'/>");
		pw.print("</form>");
	
	}

}
