package WebIGo.admin.ServiceForAndroid;

import WebIGo.admin.Bean.Manager;
import WebIGo.admin.Bean.User;
import WebIGo.admin.Dao.ManagerDao;
import WebIGo.admin.Dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */

@WebServlet("/android/login.html")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out= response.getWriter();

        String result = "";

        String username = request.getParameter("Uname");
        String psw = request.getParameter("Upwd");

        if (username == null ||username == "" ||  username.length() > 20
                ||psw == null ||psw == "" ||  psw.length() > 20
                ) {
                out.print("用户名或密码格式错误");
                return;
        }

        UsersDao userDao=new UsersDao();
        User user=userDao.find(username,psw);

        HttpSession session = request.getSession();


        try {
            if (user != null) {
                session.setAttribute("username", username);
                out.print("登录成功");
                return;
            } else {
                out.print("用户名或密码不匹配");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
