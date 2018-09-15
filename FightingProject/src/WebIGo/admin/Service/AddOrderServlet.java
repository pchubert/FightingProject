package WebIGo.admin.Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.OrderInfo;
import WebIGo.admin.Dao.OrderDao;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddOrderServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		//定义对象
		OrderInfo orderInfo = new OrderInfo();
		
		//前端表单提交数据
		
		
		//创建 Dao 对象
		OrderDao orderDao = new OrderDao();
		
		//调用 add 方法
		int i = orderDao.addOrder(orderInfo);
		resp.getWriter().write(i);
		
		resp.getWriter().close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
