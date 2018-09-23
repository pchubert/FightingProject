package WebIGo.admin.Tools;

import java.util.LinkedHashMap;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import WebIGo.admin.Bean.Address;

public class Layui {
    
    public String toResult(List<?> datas) {
        //申明map
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", 100);
        result.put("data", datas);
        
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String jsonStr = gson.toJson(result);
        
        return jsonStr;
    	
    }
}