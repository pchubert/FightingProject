package WebIGo.admin.utils.Sms;

import java.util.Date;
import java.util.Random;

public class SmsVerify {
	
	private String code;
	private int count;
	private Date date;

	public SmsVerify(){
		setDate(new Date());
		setCount(1);
	}
	public void addCount(){
		count++;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "SmsVerify [code=" + code + ", count=" + count + ", date=" + date + "]";
	}
	
	
}
