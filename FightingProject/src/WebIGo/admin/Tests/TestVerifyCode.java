package WebIGo.admin.Tests;

import WebIGo.admin.utils.Sms.SmsManager;

import java.util.Scanner;

public class TestVerifyCode {
    public static void main(String[] args) {
        String phoneNumber="18856363208";
        String IP="127.0.0.1";
        SmsManager smsManager=SmsManager.getInstance();
        while (true){
            String result=smsManager.send(phoneNumber,IP);
            System.out.println(result);
            Scanner scanner=new Scanner(System.in);
            scanner.next();
        }
    }
}
