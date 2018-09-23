package WebIGo.admin.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class MyUtils {
    public static String getChineseParam(HttpServletRequest request) throws UnsupportedEncodingException {
        return new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
    }

    public static boolean isParameterError(String parameter){
        return  parameter == null ||parameter.trim().isEmpty() ||  parameter.length() > 20;
    }
}
