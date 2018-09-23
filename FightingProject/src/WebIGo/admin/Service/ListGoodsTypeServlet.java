package WebIGo.admin.Service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import WebIGo.admin.Bean.GoodsType;
import WebIGo.admin.Dao.GoodsTypeDao;
import WebIGo.admin.Tools.Layui;

import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ListProductTypeServlet
 */
@WebServlet("/ListGoodsTypeServlet")
public class ListGoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListGoodsTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//解决正常中文字符串返回乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//依赖 mybatis 获取数据库中的数据，并创建相应的数据对象列表
		List<GoodsType> goodsTypes = new GoodsTypeDao().listGoodsType();
		
		//新建 Layui 对象
		Layui result = new Layui();
				
		//完成 Json 字符串的转化并转化为 layui 所需要的数据串格式
		//添加入响应，交付前端处理
		response.getWriter().write(result.toResult(goodsTypes));
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
