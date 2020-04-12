package icsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/listcart")
public class listcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public listcart() {
        super();
        // TODO Auto-generated constructor stub
    }

     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	double ttlbill=0.0;
	try {
		ICSDCheckAuth.ICSDCheckAuthofuser(request);
		// valid user
		HttpSession session=request.getSession(false);
		LinkedList<clsproduct> lst=(LinkedList<clsproduct>) session.getAttribute("CART");
		out.println("<html><head>");
		out.println("<title>shopping cart</title><link rel='stylesheet' href='css/bootstrap.min.css'> <script src=\\'js/bootstrap.min.js\\'></script></head><body>");
		out.println("<table class=' table table-hover table-striped table-bordered");
		out.println("<tr class='bg-secondary'>");
	    out.println("<th>Product ID</th>");
	    out.println("<th>Product Name</th>");
	    out.println("<th>Product Image</th>");
	    out.println("<th>Product desc</th>");
	    out.println("<th>Quantity</th>");
	    out.println("<th>Price</th>");
        out.println("</tr>");
        
        for (clsproduct objProd : lst) {
			out.println("<tr>");
			out.println("<td>"+ objProd.getStrprodid()+ "</td>");
			out.println("<td>"+ objProd.getStrprodname()+ "</td>");
			out.println("<td><img  style='height:300px; width:300px;' src='images/"+objProd.getStrprodimg()+"'></td>");
			out.println("<td>"+ objProd.getStrproddesc()+ "</td>");
			out.println("<td>"+ objProd.getQty()+ "</td>");
			out.println("<td>"+ objProd.getDblprice()+ "</td>");
			out.println("</tr>");
			ttlbill=ttlbill+objProd.getDblprice();
		}
		out.println("</table>");
		out.println("Your total bill is Rs. "+ ttlbill);
		out.println("<a href='categorypage'> extra product</a>");
	} catch (ICSDAuthException e) {
		out.println("<html><head<title>ErrorMessage</title></head><body>");
		out.println("<a href='LoginPage.html' class='btn btn-warning'> "+ e.getMessage()+ "</a>");
		out.println("</body></html>");
      
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
