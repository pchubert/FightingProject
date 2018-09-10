package WebIGo.admin.Bean;

import java.util.Date;

public class User {
	private  int  Uid;
	private  String Uname;
	private String Upwd;
	private String Uemail;
	private String Urealname;
	private Date Udate;
	private String Uphone;
	private int Utype;
	private int Ucread;
	private double Umoney;
	
	public int getUid() {
		return Uid;
	}
	public void setUid(int uid) {
		Uid = uid;
	}
	public String getUname() {
		return Uname;
	}
	public void setUname(String uname) {
		Uname = uname;
	}
	public String getUpwd() {
		return Upwd;
	}
	public void setUpwd(String upwd) {
		Upwd = upwd;
	}
	public String getUemail() {
		return Uemail;
	}
	public void setUemail(String uemail) {
		Uemail = uemail;
	}
	public String getUrealname() {
		return Urealname;
	}
	public void setUrealname(String urealname) {
		Urealname = urealname;
	}
	public String getUphone() {
		return Uphone;
	}
	public void setUphone(String uphone) {
		Uphone = uphone;
	}
	public int getUtype() {
		return Utype;
	}
	public void setUtype(int utype) {
		Utype = utype;
	}
	public int getUcread() {
		return Ucread;
	}
	public void setUcread(int ucread) {
		Ucread = ucread;
	}
	public double getUmoney() {
		return Umoney;
	}
	public void setUmoney(double umoney) {
		Umoney = umoney;
	}
	public Date getUdate() {
		return Udate;
	}
	public void setUdate(Date udate) {
		Udate = udate;
	}
}
