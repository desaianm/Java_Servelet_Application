package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreateAccount() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession sess = request.getSession(false);
		
		String msg = "";
		if(request.getParameter("msg") != null) msg = request.getParameter("msg");
		
		String password= "", username = "";
		String loginname = "";
		
		

		  out.println("<h3 style='color:red;'>"+msg+"</h3>");

		  out.println("<br>");
		  out.println("<h3 style='color:red;'> New  User Sign Up </h3>");
		  out.println("<br>");

		  out.println("<hr>");
		  out.println("<br>");

		  
		  out.println("<form method=post action=CreateAccount>");
		  
		  
		out.println("Login Name <input type=text name=loginname value=\""+loginname+"\"><br>"
				  ); 
		out.println("<br>");
		
		  out.println(" User Name: <input type=text name=username value=\""+username+"\"><br>"
		  ); 
		  out.println("<br>");
			
		  out.println(" Password : <input type=text name=password value=\""+password+
		  "\"><br>"); 
		  out.println("<br>");
			
		  out.println("<input type=submit value='Sign Up'>");
		  
		  out.println("</form>");
	

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession();
		if(Helper.isItemNameValid(request.getParameter("username")) &&
				Helper.isItemNameValid(request.getParameter("password"))&&
				Helper.isItemNameValid(request.getParameter("loginname"))) 
		{
			
			String username = request.getParameter("username");
			String loginname = request.getParameter("loginname");
			String password  = request.getParameter("password");
			
			
			
			
			DB_Access db = new DB_Access();
			User u = new User();
			u.setLoginname(loginname);
			u.setUsername(username);
			u.setPassword(password);
			
			db.insertUser(u);
			
			response.sendRedirect("Home?msg= Account Created Succesfully");
		}
		else {
			sess.setAttribute("username", request.getParameter("username"));
			sess.setAttribute("loginname", request.getParameter("loginname"));
			sess.setAttribute("password", request.getParameter("password"));
			
			response.sendRedirect("CreateAccount?msg=Invalid Value Entered");
		}	
		
		
	}

	
	}


