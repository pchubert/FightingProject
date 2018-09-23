package WebIGo.admin.ServiceForAndroid;


import WebIGo.admin.Bean.User;
import WebIGo.admin.Dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static WebIGo.admin.utils.MyUtils.isParameterError;



@WebServlet("/android/login.html")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out= response.getWriter();

        //获取两个参数：Uname和Uname
        String username = request.getParameter("Uname");
        String psw = request.getParameter("Upwd");


        //参数合法性校验
        if (isParameterError(username)||isParameterError(psw)
                ) {
                out.print("用户名或密码格式错误");
                return;
        }

        //调用dao检查用户名密码是否匹配
        UsersDao userDao=new UsersDao();
        User user=userDao.find(username,psw);


        HttpSession session = request.getSession();


        try {
            if (user != null) {

                //用session保存用户登录状态
                session.setAttribute("username", username);

                out.print("登录成功");
            } else {
                out.print("用户名或密码不匹配");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
