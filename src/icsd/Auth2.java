package icsd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Auth2")
public class Auth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con=null;
       ResultSet rset=null;
       PreparedStatement stmt=null;
       
    public Auth2() {
        super();
    }
     
	public void init(ServletConfig config) throws ServletException {
	DBHandler objDH=new DBHandler();
	con=objDH.getDBcon();
	try {
		stmt=con.prepareStatement("insert into tbluser values(?,?)");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void destroy() {
		try {
			if(con!=null) {
				con.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(rset!=null) {
				rset.close();
			}
		}
			catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		try {
            HttpSession session =request.getSession();  
			stmt.setString(1, request.getParameter("txtunm"));
			stmt.setString(2, request.getParameter("txtpwd"));
       		rset=stmt.executeQuery();
       		response.sendRedirect(request.getContextPath()+"/categorypage");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
