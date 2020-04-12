package icsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;


@WebServlet("/categorypage")
public class categorypage extends HttpServlet {
	
	
     
     
        public categorypage() {
        super();
    }
       

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
	PrintWriter out=response.getWriter();
		try
		{
			ICSDCheckAuth.ICSDCheckAuthofuser(request);
			DBHandler objDH=new DBHandler();		
			Connection con=objDH.getDBcon();
			out.println("<html><head>");
			out.println("<title>categorypage</title>");
			out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>\r\n" + 
					"<script src='https://code.jquery.com/jquery-3.3.1.slim.min.js' integrity='sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo' crossorigin='anonymous'></script>\r\n" + 
					"<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js' integrity='sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1' crossorigin='anonymous'></script>\r\n" + 
					"<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js' integrity='sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM' crossorigin='anonymous'></script>\r\n" + 
					"");
			out.println("</head><body>");

			try
			{
				PreparedStatement stmt=con.prepareStatement("select * from category");
				ResultSet rset=stmt.executeQuery();
				out.println("<table class='table table-bordered table-hover'>");
				out.println("<tr class='bg-success'>");
				out.println("<th>catid</th>");
				out.println("<th>catname</th>");
				out.println("<th>catimg</th>");
				out.println("<th>catdesc</th>");
				out.println("<th>seeproducts</th>");
				out.println("</tr>");
//				-------------- ---- ------------ 
//				CATEGORYID          VARCHAR2(20) 
//				CATEGORYNAME        VARCHAR2(20) 
//				CATEGORYDESC        VARCHAR2(50) 
//				CATEGORYIMGURL      VARCHAR2(50) 
				while(rset.next())
				{
				String strcatid,strcatname,strcatdesc,strcatimg;
					strcatid=rset.getString("categoryid");
					System.out.println(strcatid);
					strcatname=rset.getString("categoryname");
					strcatdesc=rset.getString("categorydesc");
					strcatimg=rset.getString("categoryimgurl");
					
										System.out.println(strcatid+" "+strcatname+" "+strcatdesc+" "+strcatimg);
					out.println("<tr>");
					out.println("<td>"+strcatid+"</td>");
					out.println("<td>"+strcatname+"</td>");
					out.println("<td><img src='images/"+strcatimg+"'height=300px,width=300px/></td>");
					out.println("<td>"+strcatdesc+"</td>");
					out.println("<td><a href='proddetails?catid="+strcatid+"' class='btn btn-outline-primary'>See Products</td>");
					out.println("</tr>");
				}
				out.println("</table>");	
			out.println("</body></html>");
		}
			catch (SQLException e) {
				e.printStackTrace();
				}
		}
		catch(ICSDAuthException e1)
		{
			out.println("<html><head><title>errorpage</title></head><body>");
			out.println("<a><href='loginpage.html'>"+e1.getMessage()+"</a>");
			out.println("</body></html>");
		}
		
		}
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

}
