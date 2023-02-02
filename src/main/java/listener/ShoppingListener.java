package listener;

import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ShoppingListener implements ServletContextListener {
	
	Connection con;
	
    public void contextDestroyed(ServletContextEvent sce)  { 
        try
        {
        	con.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx=sce.getServletContext();
    	
    	String driver=ctx.getInitParameter("driverclass");
    	String url=ctx.getInitParameter("jdbcurl");
    	String username=ctx.getInitParameter("username");
    	String password=ctx.getInitParameter("password");
    	
    	
    	try
    	{
    		Class.forName(driver);
    		con=DriverManager.getConnection(url,username,password);
    		System.out.println("Connection Established");
    		ctx.setAttribute("setdb", con);
    		System.out.println("set Connection-con at context level");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }
	
}
