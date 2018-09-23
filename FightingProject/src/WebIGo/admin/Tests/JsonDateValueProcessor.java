package WebIGo.admin.Tests;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import WebIGo.admin.Bean.User;
import WebIGo.admin.Dao.UsersDao;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
public class JsonDateValueProcessor implements JsonValueProcessor {
    private String format ="yyyy-MM-dd HH:mm:ss";

    public JsonDateValueProcessor() {
        super();
    }

    public JsonDateValueProcessor(String format) {
        super();
        this.format = format;
    }

    public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    public Object processObjectValue(String paramString, Object paramObject,  JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    private Object process(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }

    public static void main(String[] args) {

        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        UsersDao usersDao=new UsersDao();
        JSONArray jsonArray2 = JSONArray.fromObject(new User(), config);
        System.out.println(jsonArray2);
    }
}
