package WebIGo.admin.Service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.Address;
import WebIGo.admin.Dao.AddressDao;

@WebServlet("/DeleteAddress")
public class DeleteAddressServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeleteAddressServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		//获取查询地址的用户信息
		Address address = new Address();
		
		//按照地址 id 删除地址
		address.setId(Integer.parseInt(new String(req.getParameter("id").getBytes(),"UTF-8")));
		//address.setId(Integer.parseInt("4"));//测试用
		
		//依赖 mybatis 获取数据库中的数据，并创建相应的数据对象列表
		int i  = new AddressDao().deleteAddress(address);
		
		resp.getWriter().write(i);
		resp.getWriter().close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
