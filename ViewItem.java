package item_manager;


import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewItem")
public class ViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewItem() {
        super();

    	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DB_Access db = new DB_Access();
		String iname = "";
		
		int iqty = 0;
		
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("Form?msg=must login first");
		}
		
		else {
			
			
			  String iid = request.getParameter("iid");
			  int ied = Integer.parseInt(iid);
			  Item i = DB_Access.ViewItems(ied);
		  	
			out.println("<h2> Item Displayed Below</h2>");
			out.println("<table>");
			out.println("<tr><th>NAME</th><th>QTY</th></tr>");
			
			out.println("<br>");
			i.setIid(ied);
			
			out.println("<tr>");
			out.println("	<td style='font size=70%'>"+i.getItemName()+"</td>");
			out.println("	<td style='font size=70%'>"+i.getQty()+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
			}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

		
	}


