package WebIGo.admin.ServiceForAndroid;

import WebIGo.admin.Bean.User;
import WebIGo.admin.Dao.UsersDao;
import WebIGo.admin.utils.Sms.SmsManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static WebIGo.admin.utils.MyUtils.isParameterError;

@WebServlet("/android/verify.html")
public class verifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out= response.getWriter();

        String Uphone=request.getParameter("Uphone");

        if (isParameterError(Uphone)) {
            out.print("手机号错误");
            return;
        }

        UsersDao usersDao=new UsersDao();
        if(usersDao.existUphone(Uphone)){
            out.write("此手机号已被注册");
            return;
        }

        //发送短信验证码，并且得到验证码

        SmsManager smsManager=SmsManager.getInstance();
        String result=smsManager.send(Uphone,request.getRemoteAddr());
        out.write(result);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
