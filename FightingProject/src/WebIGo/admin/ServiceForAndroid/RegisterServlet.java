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

@WebServlet("/android/register.html")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out= response.getWriter();
        //out.print("register");
        String username = request.getParameter("Uname");
        String psw = request.getParameter("Upwd");
        String Uphone = request.getParameter("Uphone");

        if (username == null ||username .isEmpty() ||  username.length() > 20
                ||psw == null ||psw .isEmpty() ||  psw.length() > 20
                ||Uphone == null ||Uphone.isEmpty() ||  Uphone.length() > 20
                ) {
            out.print("用户名或密码格式错误");
            return;
        }

        User user=new User();
        user.setUname(username);
        user.setUpwd(psw);
        user.setUphone(Uphone);

        UsersDao userDao=new UsersDao();

        String result=userDao.register(user);
        out.write(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
