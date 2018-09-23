package WebIGo.admin.Tests;

import WebIGo.admin.Bean.Goods;
import WebIGo.admin.Bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class GsonTest {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        System.out.println(gson.toJson(new Goods()));

    }
}
