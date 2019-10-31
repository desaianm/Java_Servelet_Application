package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("Form?msg=must login first");
		}
		String msg = "";
		if(request.getParameter("msg") != null) msg = request.getParameter("msg");
		
		String iqty= "", iname = "";
		if(sess.getAttribute("iname") != null) {
			iname = (String) sess.getAttribute("iname");
			sess.removeAttribute("iname");
		}
		if(sess.getAttribute("iqty") != null) {
			iqty = (String) sess.getAttribute("iqty");
			sess.removeAttribute("iqty");
		}
		

		  out.println("<h3 style='color:red;'>"+msg+"</h3>");

		  out.println("<br>");
		  out.println("<hr>");
		  out.println("<br>");
		  
		  out.println("<form method=post action=AddItem>");
		  out.println("<br>");
		  out.println("Item Name: <input type=text name=iname value=\""+iname+"\"><br>"
		  ); 
		  out.println("<br>");
		  out.println("Item Quantity: <input type=text name=iqty value=\""+iqty+
				  
		  "\"><br>"); 
		  out.println("<br>");
		  
		  out.println("<input type=submit value='Add Item'>");
		  out.println("</form>");
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if(Helper.isItemNameValid(request.getParameter("iname")) &&
				Helper.isItemQtyValid(request.getParameter("iqty"))) {
			String iname = request.getParameter("iname");
			int iqty = Integer.parseInt(request.getParameter("iqty"));
			
			int uid = (Integer) sess.getAttribute("uid");
			
			Item i = new Item(-1, iname, iqty, uid);
			
			DB_Access db = new DB_Access();
			db.insertItem(i);
			response.sendRedirect("Home?msg=item is successfully inserted");
		}
		else {
			sess.setAttribute("iname", request.getParameter("iname"));
			sess.setAttribute("iqty", request.getParameter("iqty"));
			response.sendRedirect("AddItem?msg=either item name or qty or both are incorrect");
		}
	}

}




