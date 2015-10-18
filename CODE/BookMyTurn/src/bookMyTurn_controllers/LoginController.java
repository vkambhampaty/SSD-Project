/**
 * 
 */
//package bMT_Controllers;

package bookMyTurn_controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import bookMyTurn_model.Authenticator;
import bookMyTurn_model.User;

//import sun.text.normalizer.ICUBinary.Authenticate;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		String frontDesk = "frontDesk";
		String admin = "admin";
		String User = "user";

		String sql = "select count(username), role from user_db where username='" + username + "'" +"and password='"+password+"'";
        java.sql.Connection conn = null;
        
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	System.out.println("Inside try of login controller");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "rootroot");
            Statement s = (Statement) conn.createStatement();

            java.sql.ResultSet rs = s.executeQuery(sql);
           
            while (rs.next())
            {
                int count = rs.getInt("count(username)");
                String role = rs.getString("role");
                System.out.println("The count is: "+count +"the role is: "+role);
                if(count == 1)
                {
                	if(role.equalsIgnoreCase(User))
                	{
                		rd = request.getRequestDispatcher("/success.jsp");
                	} else if(role.equalsIgnoreCase(frontDesk))
                	{
                		rd = request.getRequestDispatcher("/Receptionist Page.jsp");
                	} else if(role.equalsIgnoreCase(admin))
                	{
                		rd = request.getRequestDispatcher("/Admin Page.jsp");
                	}
        			User user = new User(username, password);
        			request.setAttribute("user", user);
        		} else {
        			rd = request.getRequestDispatcher("/error.jsp");
                }
            }
		rd.forward(request, response);
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
	}
}

