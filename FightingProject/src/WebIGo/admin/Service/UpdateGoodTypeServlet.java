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
 * Servlet implementation class UpdateGoodTypeServlet
 */
@WebServlet("/UpdateGoodTypeServlet")
public class UpdateGoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		GoodsType goodsType = new GoodsType();
		
		//设置需要修改的商品类型 id
		goodsType.setTid(Integer.parseInt(new String(request.getParameter("Tid").getBytes("ISO8859-1"),"UTF-8")));
		
		//设置需要修改的的商品信息
		goodsType.setTname(new String(request.getParameter("Tname").getBytes("ISO8859-1"),"UTF-8"));
		goodsType.setTpic(new String(request.getParameter("Tpic").getBytes("ISO8859-1"),"UTF-8"));
		goodsType.setTintro(new String(request.getParameter("Tintro").getBytes("ISO8859-1"),"UTF-8"));
		goodsType.setTbrief(new String(request.getParameter("Tbrief").getBytes("ISO8859-1"),"UTF-8"));
		
//		goodsType.setTid(7);//测试用
//		goodsType.setTname("太空水果");
//		goodsType.setTintro("非本地产");
//		goodsType.setTbrief("来自外太空");
		
		//调用 update() 方法
		GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
		int i = goodsTypeDao.updateGoodsType(goodsType);
		
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
