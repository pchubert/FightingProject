package WebIGo.admin.Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.GoodsType;
import WebIGo.admin.Dao.GoodsTypeDao;

/**
 * Servlet implementation class AddGoodsType
 */
@WebServlet("/AddGoodsTypeServlet")
public class AddGoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//新建 GoodType 对象
		GoodsType goodsType = new GoodsType();
		
		//设置各属性值
		goodsType.setTname(new String(request.getParameter("Tname").getBytes("ISO8859-1"),"UTF-8"));
		goodsType.setTpic("/Upload/"+new String(request.getParameter("Tpic").getBytes("ISO8859-1"),"UTF-8"));
		goodsType.setTintro(new String(request.getParameter("Tintro").getBytes("ISO8859-1"),"UTF-8"));
		goodsType.setTbrief(new String(request.getParameter("Tbrief").getBytes("ISO8859-1"),"UTF-8"));

		
		//创建 DAO 对象
		GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
		
		//调用 Add 方法
		int i = goodsTypeDao.addGoodsType(goodsType);
		
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
