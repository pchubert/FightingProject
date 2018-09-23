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
import WebIGo.admin.Tools.Layui;

@WebServlet("/ListAddressesServlet")
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

		
		//获取查询地址的用户信息
		Address address = new Address();
		
		//System.out.println("kks");测试代码
		
		if (req.getParameter("Uid")!=null) {
			address.setUid(Integer.parseInt(req.getParameter("Uid")));
		}
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
		
		//新建 Layui 对象
		Layui result = new Layui();
		
		//完成 Json 字符串的转化并转化为 layui 所需要的数据串格式
		//添加入响应，交付前端处理
		resp.getWriter().write(result.toResult(addresses));
		resp.getWriter().close();
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
