package WebIGo.admin.Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.Goods;
import WebIGo.admin.Dao.GoodsDao;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
@WebServlet("/UpdateGoodsServlet")
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Goods goods = new Goods();
		
		//获取需要更改信息的商品 id
		goods.setGid(Integer.parseInt(new String(request.getParameter("Gid").getBytes("ISO8859-1"),"UTF-8")));
		
		//填入更改信息
		goods.setGname(new String(request.getParameter("Gname").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGimgurl(new String(request.getParameter("Gimgurl").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGdiscount(Double.parseDouble(request.getParameter("Gdiscount")));
		goods.setGamount(Integer.parseInt(request.getParameter("Gamount")));
		
//		goods.setGid(4);//测试用
//		goods.setGname("水果");
//		goods.setGimgurl("567");
		
		//调用 update() 方法
		GoodsDao goodsDao = new GoodsDao();
		int i = goodsDao.updateGoods(goods);
		
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
