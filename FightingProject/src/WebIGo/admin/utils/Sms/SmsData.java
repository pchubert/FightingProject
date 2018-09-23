package WebIGo.admin.utils.Sms;


import java.util.Random;

public class SmsData {
	private String phoneNumbers;//电话号码，多个用逗号分割，批量上限为1000个手机号码
	
	private String templateCode;//短信模板id
	
	private String templateParam;//短信模板内容
	
	private String ipAddress;

	private String verifyCode;



	public SmsData(String phoneNumbers, String ipAddress){
		setphoneNumbers(phoneNumbers);
		setIpAddress(ipAddress);
		settemplateCode("SMS_144940959");
		verifyCode=new Random().nextInt(900000)+100000+"";
		settemplateParam("{\"code\":\""+verifyCode+"\"}");
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getphoneNumbers()
	{
		return phoneNumbers;
	}
	
	public void setphoneNumbers(String phoneNumbers)
	{
		this.phoneNumbers=phoneNumbers;
	}
	
	public String gettemplateCode()
	{
		return templateCode;
	}
	
	public void settemplateCode(String templateCode)
	{
		this.templateCode = templateCode;
	}
	
	public String gettemplateParam()
	{
		return templateParam;
	}
	
	public void settemplateParam(String templateParam)
	{
		this .templateParam = templateParam;
	}
	
	
}
