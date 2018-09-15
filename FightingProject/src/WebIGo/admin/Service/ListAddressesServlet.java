package WebIGo.admin.Service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import WebIGo.admin.Bean.Address;
import WebIGo.admin.Dao.AddressDao;

@WebServlet("/ListAddress")
public class ListAddressesServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ListAddressesServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//解决正常中文字符串返回乱码
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		//获取查询地址的用户信息
		Address address = new Address();
		
		address.setUid(Integer.parseInt(req.getParameter("Uid")));
		//address.setUid(Integer.parseInt("14"));//测试用
		
		//依赖 mybatis 获取数据库中的数据，并创建相应的数据对象列表
		List<Address> addresses;
		
		if(address.getUid()==0) {
			addresses = new AddressDao().listAddresses();
			
			//System.out.println("listAddresses : OK!");//测试用
		}else {
			addresses = new AddressDao().listAddressesOfUser(address);
	
			//System.out.println("listAddressesofUser : OK!");//测试用
		}
		
		//新建 gson 对象
		Gson gson = new Gson();
		
		//完成 Java 对象和 Json 字符串的转化
		String Jaddresses=gson.toJson(addresses);
		
		//添加入响应
		//以 Json 字符串形式交付前端处理
		resp.getWriter().append(Jaddresses);
		resp.getWriter().close();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
