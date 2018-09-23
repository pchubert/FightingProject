package WebIGo.admin.ServiceForAndroid;

import WebIGo.admin.Bean.User;

import WebIGo.admin.Dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static WebIGo.admin.utils.MyUtils.*;

@WebServlet("/android/register.html")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out= response.getWriter();

        //获取参数
        String username = request.getParameter("Uname");
        String psw = request.getParameter("Upwd");
        String Uphone = request.getParameter("Uphone");
        String verifyCode=request.getParameter("Verify");

        if (isParameterError(username)||isParameterError(psw)||isParameterError(Uphone)
                ||isParameterError(verifyCode)
                ) {
            out.print("用户名或密码格式错误");
            return;
        }


        //TODO:在这里验证验证码信息

        //根据参数信息创建User对象
        User user=new User();
        user.setUname(username);
        user.setUpwd(psw);
        user.setUphone(Uphone);


        //注册
        UsersDao userDao=new UsersDao();
        String result=userDao.register(user);

        out.write(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }



}
