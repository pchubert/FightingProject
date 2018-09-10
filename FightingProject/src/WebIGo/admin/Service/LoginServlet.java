package WebIGo.admin.Service;

import java.io.IOException;

import WebIGo.admin.Bean.Manager;
import WebIGo.admin.Dao.*;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/heml;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String result = "";

        String username = request.getParameter("username");
        String psw = request.getParameter("password");

        if (username == "" || username == null || username.length() > 20) {
            try {
                result = "请输入用户名（不能超过20个字符）";
                request.setAttribute("message", result);
                request.getRequestDispatcher("login.jsp").forward(request,response);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (psw == "" || psw == null || psw.length() > 20) {
            try {
                result = "请输入密码（不能超过20个字符）";
                request.setAttribute("message", result);
                request.getRequestDispatcher("login.jsp").forward(request,response);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ManagerDao userDao = new ManagerDao();
        Manager user = userDao.find(username, psw);

        HttpSession session = request.getSession();
       

        try {
            if (user != null) {
            	session.setAttribute("username", username);
                response.sendRedirect("index.jsp");
                return;
            } else {
            	 request.setAttribute("message", "用户名或密码不匹配");
            	 request.getRequestDispatcher("login.jsp").forward(request,response);
           
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }

}
