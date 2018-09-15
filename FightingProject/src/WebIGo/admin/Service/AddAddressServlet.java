package WebIGo.admin.Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.Address;
import WebIGo.admin.Dao.AddressDao;

@WebServlet("/AddAddressServlet")
public class AddAddressServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddAddressServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		//定义 address 对象
		Address address = new Address();
		
		address.setUid(Integer.parseInt(req.getParameter("Uid")));
		address.setName(new String(req.getParameter("name").getBytes("ISO8859-1"),"UTF-8"));
		address.setTelephone(new String(req.getParameter("telephone").getBytes("ISO8859-1"),"UTF-8"));
		address.setBuildingName(new String(req.getParameter("buildingName").getBytes("ISO8859-1"),"UTF-8"));
		address.setIntroduction(new String(req.getParameter("introduction").getBytes("ISO8859-1"),"UTF-8"));
		
		//创建 addressDao 对象
		AddressDao addressDao = new AddressDao();
		
		//调用 add 方法
		int i = addressDao.addAddress(address);
		
		resp.getWriter().write(i);
		resp.getWriter().close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
