package WebIGo.admin.utils.Sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.util.Date;
import java.util.Hashtable;

public class SmsManager {
    Hashtable<String , SmsVerify> verifyTable = new Hashtable<>();
    Hashtable<String, SmsIPVerify> ipTable = new Hashtable<>();


    //内部类单例模式
    private static class SmsManagerHolder{
        private static final  SmsManager INSTANCE = new SmsManager();
    }

    public static final SmsManager getInstance(){
        return SmsManagerHolder.INSTANCE;
    }

    private SmsManager(){}


    /**
     *
     * @param 手机号
     * @param Ip地址
     * @return 错误信息或者验证码
     */
    public String send(String phoneNumber,String IP){
        if (!verifyIP(IP)) {
            return  "此ip注册发送次数过多";
        }
        if(!veryfyPhoneNumber(phoneNumber)){
            return "此手机号发送次数过多或发送过于频繁";
        }

        SmsData smsData=new SmsData(phoneNumber,IP);
        try {
            SendSmsResponse response = SmsSender.sendSms(smsData);
        } catch (ClientException e) {
            e.printStackTrace();
            return "服务器错误";
        }

        SmsVerify smsVerify=verifyTable.get(phoneNumber);
        if(smsVerify==null){
            verifyTable.put(phoneNumber,new SmsVerify());
        }else{
            System.out.println(smsVerify);
            smsVerify.addCount();
            smsVerify.setDate(new Date());
            smsVerify.setCode(smsData.getVerifyCode());
        }

        SmsIPVerify smsIPVerify=ipTable.get(IP);
        if(smsIPVerify==null){
            ipTable.put(IP,new SmsIPVerify());
        }else {
            smsIPVerify.addCount();
        }

        return smsData.getVerifyCode();
    }

    boolean verifyIP(String IP){
        SmsIPVerify smsIPVerify=ipTable.get(IP);
        if(smsIPVerify==null){
            return true;
        }
        System.out.println(smsIPVerify.getCount());
        return smsIPVerify.getCount()<20;
    }

    boolean veryfyPhoneNumber(String phoneNumber){
        SmsVerify smsVerify=verifyTable.get(phoneNumber);


        if(smsVerify==null){
            return true;
        }

        //此号码发送次数太多
        if(smsVerify.getCount()>10){
            return false;
        }

        //上次发送时间不超过1分钟
        if(smsVerify.getDate().getTime()+60000>new Date().getTime()){
            return false;
        }
        return true;
    }




}
