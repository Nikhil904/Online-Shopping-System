package icsd;

import java.io.IOException;
import java.io.PrintWriter;
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

import oracle.jdbc.pool.OracleDataSource;

@WebServlet("/proddetails")
public class proddetails extends HttpServlet {
    public proddetails() {
    	super();
    }
    Connection con=null;
    PreparedStatement stmt=null;
    ResultSet rset=null;
    
    public Connection getDBcon()
    {
    Connection con=null;
    try {
OracleDataSource ods=new OracleDataSource();
ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
con=ods.getConnection("nikhil","icsd");
System.out.println("connection established ");
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
    return con;
    
    }
//    create table proddetails
//    (
//    categoryid varchar(10),
//    productid varchar(20),
//    productname varchar(20),
//    prodesc varchar(40),
//    prodimgurl varchar(50),
//    qty varchar(10),
//    price varchar(20)
//    );


    public void init(ServletConfig config) throws ServletException
    {
    
    con = getDBcon();
    try {
stmt=con.prepareStatement("select * from proddetails where categoryid=?");
} catch (SQLException e) {

e.printStackTrace();
}
    System.out.println("Auth init fired ");
    }
    public void destroy()
    {
    System.out.println("destroy called ");
    }

    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter out=response.getWriter();
try
{

ICSDCheckAuth.ICSDCheckAuthofuser(request);
out.println("<html><head>");
out.println("<title>Product Details page</title>");
out.println(" <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>\r\n" + 
		"<script src='https://code.jquery.com/jquery-3.3.1.slim.min.js' integrity='sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo' crossorigin='anonymous'></script>\r\n" + 
		"<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js' integrity='sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1' crossorigin='anonymous'></script>\r\n" + 
		"<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js' integrity='sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM' crossorigin='anonymous'></script>\r\n" + 
		"");

out.println("</head><body>");
DBHandler objDH=new DBHandler();
String strcatid=request.getParameter("catid");
Connection con=objDH.getDBcon();
try
{
//	categoryid varchar(10),
//	productid varchar(20),
//	productname varchar(20),
//	prodesc varchar(40),
//	prodimgurl varchar(50),
//	qty varchar(10),
//	price varchar(20)

	String strCatId=request.getParameter("catid");
	stmt.setString(1, strCatId);
	rset=stmt.executeQuery();
	
	out.println("<table class='table table-border table-hover'>");
	out.println("<tr class='bg-warning'>");
	out.println("<th>categoryid</th>");
	out.println("<th>productid</th>");
	out.println("<th>productname</th>");
	out.println("<th>proddesc</th>");
	out.println("<th>prodimgurl</th>");
	out.println("<th>qty</th>");
	out.println("<th>price</th>");
	out.println("<th>Addtocart</th>");
	out.println("</tr>");
	while(rset.next())
	{
		String strprodid=rset.getString("productid");
		String strprodname=rset.getString("productname");
		String strproddesc=rset.getString("prodesc");
		String strprodimg=rset.getString("prodimgurl");
		String strprodqty=rset.getString("qty");
		String strprodprice=rset.getString("price");
		out.println("<tr>");
		System.out.println(strprodimg);
		out.println("<td>"+ strCatId+"</td>");
		out.println("<td>"+ strprodid+"</td>");
		out.println("<td>"+ strprodname+"</td>"); 
		out.println("<td>"+ strproddesc+"</td>");
		out.println("<td><img src='images/"+strprodimg+"'height=300px,width=300px/></td>");
		out.println("<td>"+strprodqty+"</td>");
		out.println("<td>"+strprodprice+"</td>");
		//out.println("<td><a href='proddetails?catid="+strcatid+"' class='btn btn-outline-primary'>See Products</td>");
		out.println("<td><a href='Addtocart?prodid="+strprodid+ "'class='btn btn-outline-danger'> Addtocart</a></td>");
		out.println("</tr>");
		
	}
	out.println("</table>");
}
catch(SQLException e)
{
	e.printStackTrace();
}
out.println("</body></html>");
}
catch(ICSDAuthException e1)
{
	out.println("<html><head><title> errormessage</title></head><body>");
	out.println("<a href='login page.html'>"+e1.getMessage()+"</a>");
	out.println("</body></html>");
}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
