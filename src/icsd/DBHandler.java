	package icsd;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	import oracle.jdbc.pool.OracleDataSource;
	
	
	public class DBHandler {
	
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
	    
	
		public  clsproduct getRowfromproductsbypid(String strpid) {
		
			clsproduct obj=null;
			Connection con=getDBcon();
			try
			{
				PreparedStatement stmt=con.prepareStatement("select * from proddetails where productid=?");
				stmt.setString(1, strpid);
				ResultSet rset=stmt.executeQuery();
				if(rset.next())
				{
//					create table proddetails
//					(
//					categoryid varchar(10),
//					productid varchar(20),
//					productname varchar(20),
//					prodesc varchar(40),
//					prodimgurl varchar(50),
//					qty varchar(10),
//					price varchar(20)
//					);
					String strprodid=rset.getString("productid");
					String strprodname = rset.getString("productname");
					double dblprice=rset.getDouble("price");
					String strcatid = rset.getString("categoryid");
					String strprodimg=rset.getString("prodimgurl");
					String strproddesc=rset.getString("prodesc");
					int qty=rset.getInt("qty");
					obj=new clsproduct(strprodid,strcatid,strprodname,strprodimg,strproddesc,dblprice,qty);
					System.out.println(obj);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return obj;
		}
		
		public boolean isValiduser(String strunm, String strpwd) {
			con=getDBcon();
			try {
				stmt=con.prepareStatement("select * from tbluser where unm=? and pwd=?");
				stmt.setString(1, strunm);
				stmt.setString(2, strpwd);
				
				rset=stmt.executeQuery();
				
				if(rset.next())
				{
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}