package WebIGo.admin.utils.Sms;

public class SmsIPVerify {
	private int count;

	SmsIPVerify(){
		count=1;
	}

	public void addCount(){
		count++;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "SmsIPVerify [count=" + count + "]";
	}
	
}
