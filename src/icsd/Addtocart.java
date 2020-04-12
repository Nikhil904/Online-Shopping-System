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


@WebServlet("/Addtocart")
public class Addtocart extends HttpServlet {

       

    public Addtocart() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String strPid=request.getParameter("prodid");
DBHandler objDH=new DBHandler();
clsproduct obj=objDH.getRowfromproductsbypid(strPid);
PrintWriter out=response.getWriter();
HttpSession session =request.getSession(false);
LinkedList<clsproduct> lst=(LinkedList<clsproduct>) session.getAttribute("CART");
if(lst==null)
{
	lst=new LinkedList<>();
	session.setAttribute("CART", lst);
}
lst.add(obj);

response.sendRedirect(request.getContextPath()+"/listcart");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
