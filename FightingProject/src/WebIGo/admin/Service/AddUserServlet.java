package WebIGo.admin.Service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.User;
import WebIGo.admin.Dao.UsersDao;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddUserServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		//定义 User 对象
		User user = new User();
		
		//前端表单提交数据
		user.setUname(new String(req.getParameter("Uname").getBytes("ISO8859-1"),"UTF-8"));
		user.setUpwd(new String(req.getParameter("Upwd").getBytes("ISO8859-1"),"UTF-8"));
		user.setUemail(new String(req.getParameter("Uemail").getBytes("ISO8859-1"),"UTF-8"));
		user.setUrealname(new String(req.getParameter("Urealname").getBytes("ISO8859-1"),"UTF-8"));
		user.setUdate(new Date());
		user.setUphone(new String(req.getParameter("Uphone").getBytes("ISO8859-1"),"UTF-8"));
		user.setUtype(Integer.parseInt(req.getParameter("Utype")));
		user.setUmoney(Double.parseDouble(req.getParameter("Umoney")));
		
		//创建 UsersDao 对象
		UsersDao uesrsDao = new UsersDao();
		
		//调用 add 方法
		int i = uesrsDao.addUser2(user);
		resp.getWriter().write(i);
		
		resp.getWriter().close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
