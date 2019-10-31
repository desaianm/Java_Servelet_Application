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
 * Servlet implementation class ModifyAccount
 */
@WebServlet("/ModifyAccount")
public class ModifyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ModifyAccount() {
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
		int uid = 0;
		
		if(sess.getAttribute("username") != null) {
			username = (String) sess.getAttribute("username");
			sess.removeAttribute("username");
		}

		if(sess.getAttribute("loginname") != null) {
			loginname = (String) sess.getAttribute("loginname");
			sess.removeAttribute("loginname");
		}
		if(sess.getAttribute("password") != null) {
			password = (String) sess.getAttribute("password");
			sess.removeAttribute("password");
		}
		if(sess.getAttribute("uid") != null) {
			uid = (Integer) sess.getAttribute("uid");
			
			
		}
		
		  out.println("<h3 style='color:red;'>"+msg+"</h3>");
            
		  out.println("<br>");
		  out.println("<hr>");
		  out.println("<br>");
		  
		  out.println("<form method=post action=ModifyAccount>");
		  
		  
		out.println("LoginName <input type=text name=loginname value=\""+loginname+"\"><br>"
				  );

		  out.println("<br>");
		out.println(" User Name: <input type=text name=username value=\""+username+"\"><br>"
		  ); 

		  out.println("<br>");
		out.println(" Password : <input type=text name=password value=\""+password+"\"><br>"); 

		  out.println("<br>");
		out.println("<input type=submit value='Modify Account'>");
		  
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
			
			int uid = (Integer) sess.getAttribute("uid");
			
			
			DB_Access db = new DB_Access();
			User u = new User();
			u.setUid(uid);
			u.setLoginname(loginname);
			u.setUsername(username);
			u.setPassword(password);
			
			db.ModifyAccount(u);
			
			response.sendRedirect("Home?msg= Account Modified Succesfully");
		}
		else {
			sess.setAttribute("username", request.getParameter("username"));
			sess.setAttribute("loginname", request.getParameter("loginname"));
			sess.setAttribute("password", request.getParameter("password"));
			
			
			
			response.sendRedirect("ModifyAccount?msg=Invalid value entered ");
			
		}	
		
		
	}

	}

