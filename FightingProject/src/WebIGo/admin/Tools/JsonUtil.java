package WebIGo.admin.Tools;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import java.util.Date;

public class JsonUtil {
    static private JsonConfig jsonConfig=new JsonConfig();
    static {
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss"}));
    }

    public static String toJson(Object object){
        return JSONObject.fromObject(object,jsonConfig).toString();
    }

    public static Object toObject(String json,Class beamClass){
        return JSONObject.toBean(JSONObject.fromObject(json), beamClass);
    }
}
