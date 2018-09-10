package WebIGo.admin.Service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.Goods;
import WebIGo.admin.Dao.GoodsDao;

/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//定义 Goods 对象
		Goods goods = new Goods();
		
		//前端表单提交数据
		goods.setGname(new String(request.getParameter("Gname").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGtype(Integer.parseInt(request.getParameter("Gtype")));
		goods.setGprice(Double.parseDouble(request.getParameter("Gprice")));
		goods.setGamount(Integer.parseInt(request.getParameter("Gamount")));
		goods.setGunit(new String(request.getParameter("Gunit").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGdiscount(Double.parseDouble(request.getParameter("Gdiscount")));
		goods.setGimgurl("/Upload/"+new String(request.getParameter("Gimgurl").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGintro(new String(request.getParameter("Gintro").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGbrief(new String(request.getParameter("Gbrief").getBytes("ISO8859-1"),"UTF-8"));
		goods.setGdate(new Date());
		goods.setGlook(1);
		
		//创建 GoodsDao 对象
		GoodsDao goodsDao = new GoodsDao();
		
		//调用 add 方法
		int i = goodsDao.addGoods(goods);
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
