package WebIGo.admin.Service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import WebIGo.admin.Bean.Manager;
import WebIGo.admin.Dao.ManagerDao;
import WebIGo.admin.Tools.Layui;

import com.google.gson.GsonBuilder;

@WebServlet("/ListManager")
public class ListManagersServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ListManagersServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//解决正常中文字符串返回乱码
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
				
		//resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		//依赖 mybatis 获取数据库中的数据，并创建相应的数据对象列表
		List<Manager> managers = new ManagerDao().listManagers();
		
		//新建 Layui 对象
		Layui result = new Layui();
						
		//完成 Json 字符串的转化并转化为 layui 所需要的数据串格式
		//添加入响应，交付前端处理
		resp.getWriter().write(result.toResult(managers));
		resp.getWriter().close();

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
