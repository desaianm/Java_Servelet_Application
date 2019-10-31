package item_manager;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteItem() {
        super();

    	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DB_Access db = new DB_Access();
		
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("Form?msg=must login first");
		}
		
		  Item i = new Item();
		  String iid = request.getParameter("iid");
		  int ied = Integer.parseInt(iid);
		  
		  out.println("<h3 style='color:black;'> Item Deleted of ID "+ied+"</h3>");
		  db.DeleteItem(ied);
		  
		  out.println("<br>");
		  out.println("<hr style = 'color:black'>");
		  
		  out.println("<h4> Item is Succesfully Deleted From the List.</h4>");
		  
		  out.println("<a href =Home>Back</a>");
		  
		  out.println("<br>");
		  
		 
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
