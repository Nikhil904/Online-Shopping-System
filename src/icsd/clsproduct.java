package icsd;

public class clsproduct {
String strprodid,strcatid,strprodname,strprodimg, strproddesc;

@Override
public String toString() {
	return "clsproduct [strprodid=" + strprodid + ", strcatid=" + strcatid + ", strprodname=" + strprodname
			+ ", strprodimg=" + strprodimg + ", strproddesc=" + strproddesc + ", dblprice=" + dblprice + ", qty=" + qty
			+ "]";
}
public clsproduct(String strprodid, String strcatid, String strprodname, String strprodimg, String strproddesc,
		double dblprice, int qty) {
	super();
	this.strprodid = strprodid;
	this.strcatid = strcatid;
	this.strprodname = strprodname;
	this.strprodimg = strprodimg;
	this.strproddesc = strproddesc;
	this.dblprice = dblprice;
	this.qty = qty;
}
public String getStrprodid() {
	return strprodid;
}
public String getStrcatid() {
	return strcatid;
}
public void setStrcatid(String strcatid) {
	this.strcatid = strcatid;
}
public String getStrproddesc() {
	return strproddesc;
}
public void setStrproddesc(String strproddesc) {
	this.strproddesc = strproddesc;
}
public double getDblprice() {
	return dblprice;
}
public void setDblprice(double dblprice) {
	this.dblprice = dblprice;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public void setStrprodid(String strprodid) {
	this.strprodid = strprodid;
}
public String getStrprodname() {
	return strprodname;
}
public void setStrprodname(String strprodname) {
	this.strprodname = strprodname;
}
public String getStrprodimg() {
	return strprodimg;
}
public void setStrprodimg(String strprodimg) {
	this.strprodimg = strprodimg;
}
double dblprice;
int qty;
}
