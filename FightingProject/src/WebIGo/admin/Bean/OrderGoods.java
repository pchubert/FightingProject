package WebIGo.admin.Bean;

import java.util.Date;

public class OrderGoods {
	private int OGid;
	private int Oid;
	private int Uid;
	private Date OGDate;
	private Goods goods;
	private int OGamount;
	private double OGtotalprice;
	public int getOGid() {
		return OGid;
	}
	public void setOGid(int oGid) {
		OGid = oGid;
	}
	public int getOid() {
		return Oid;
	}
	public void setOid(int oid) {
		Oid = oid;
	}
	public int getUid() {
		return Uid;
	}
	public void setUid(int uid) {
		Uid = uid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getOGamount() {
		return OGamount;
	}
	public void setOGamount(int oGamount) {
		OGamount = oGamount;
	}
	public double getOGtotalprice() {
		return OGtotalprice;
	}
	public void setOGtotalprice(double oGtotalprice) {
		OGtotalprice = oGtotalprice;
	}
	public Date getOGDate() {
		return OGDate;
	}
	public void setOGDate(Date oGDate) {
		OGDate = oGDate;
	}
	
}
