package WebIGo.admin.Service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import WebIGo.admin.Bean.OrderInfo;
import WebIGo.admin.Dao.OrderDao;

/**
 * Servlet implementation class ListOrderServlet
 */
@WebServlet("/ListOrderServlet")
public class ListOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOrderServlet() {
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
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//依赖 mybatis 获取数据库中的数据，并创建相应的数据对象列表
		List<OrderInfo> orderInfos = new OrderDao().listOrderInfos();
		
		//新建 gson 对象
		Gson gson = new Gson();
		
		//完成 Java 对象和 Json 字符串的转化
		String Jorderinfos = gson.toJson(orderInfos);
		
		//添加入响应
		//以 Json 字符串形式交付前端处理
		response.getWriter().append(Jorderinfos);
		//System.out.println("Jorderinfos : "+Jorderinfos);
				
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
