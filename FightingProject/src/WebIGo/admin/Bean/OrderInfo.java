package WebIGo.admin.Bean;

import java.util.Date;

public class OrderInfo {
	private int Oid;
	private Date Odate;
	private int Aid;
	private int Ostate;
	private String Orecname;
	private String Orecadr;
	private String Orectel;
	private int Uid;
	private double Ototalprice;
	private String Obrief;
	private String Ointro;
	public int getOid() {
		return Oid;
	}
	public void setOid(int oid) {
		Oid = oid;
	}
	public Date getOdate() {
		return Odate;
	}
	public void setOdate(Date odate) {
		Odate = odate;
	}
	public int getAid() {
		return Aid;
	}
	public void setAid(int aid) {
		Aid = aid;
	}
	public int getOstate() {
		return Ostate;
	}
	public void setOstate(int ostate) {
		Ostate = ostate;
	}
	public String getOrecname() {
		return Orecname;
	}
	public void setOrecname(String orecname) {
		Orecname = orecname;
	}
	public String getOrecadr() {
		return Orecadr;
	}
	public void setOrecadr(String orecadr) {
		Orecadr = orecadr;
	}
	public String getOrectel() {
		return Orectel;
	}
	public void setOrectel(String orectel) {
		Orectel = orectel;
	}
	public int getUid() {
		return Uid;
	}
	public void setUid(int uid) {
		Uid = uid;
	}
	public double getOtotalprice() {
		return Ototalprice;
	}
	public void setOtotalprice(double ototalprice) {
		Ototalprice = ototalprice;
	}
	public String getObrief() {
		return Obrief;
	}
	public void setObrief(String obrief) {
		Obrief = obrief;
	}
	public String getOintro() {
		return Ointro;
	}
	public void setOintro(String ointro) {
		Ointro = ointro;
	}
	
}
