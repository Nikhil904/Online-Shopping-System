package icsd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Auth() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DBHandler objDH=new DBHandler();
    String strunm,strpwd;
    strunm=request.getParameter("txtunm");
    strpwd=request.getParameter("txtpwd");
    boolean x=objDH.isValiduser(strunm,strpwd);
    		if(x=true)
    		{
    			HttpSession session=request.getSession();
    			session.setAttribute("unm", strunm);
    			response.sendRedirect(request.getContextPath()+"/categorypage");
    		
    
    		}
    		else
    		{
    			response.sendRedirect(request.getContextPath()+"/errorpage");
    		}
	}
	

}
