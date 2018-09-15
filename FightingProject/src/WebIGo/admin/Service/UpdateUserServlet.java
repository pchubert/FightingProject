package WebIGo.admin.Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.User;
import WebIGo.admin.Dao.UsersDao;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User user = new User();
		
		//指定需要更改的用户 id
		user.setUid(Integer.parseInt(new String(request.getParameter("Uid").getBytes("ISO8859-1"),"UTF-8")));
		
		//填入需要修改的用户信息
		user.setUpwd(new String(request.getParameter("Upwd").getBytes("ISO8859-1"),"UTF-8"));
		user.setUemail(new String(request.getParameter("Uemail").getBytes("ISO8859-1"),"UTF-8"));
		
//		user.setUid(1);//测试用
//		user.setUpwd("7894555");
//		user.setUemail("auska123456@qq.com");
		
		//调用 update() 方法
		UsersDao userDao = new UsersDao();
		int i = userDao.updateUser(user);
		
		response.getWriter().write(i);
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
