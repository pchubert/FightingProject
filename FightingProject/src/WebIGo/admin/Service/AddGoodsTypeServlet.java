package WebIGo.admin.Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

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
		GoodsType goodsType = new GoodsType();
		goodsType.setTname( request.getParameter("Tname"));
		goodsType.setTpic(request.getParameter("Tpic"));
		goodsType.setTbrief(request.getParameter("Tbrief"));
		goodsType.setTintro(request.getParameter("Tintro"));
		GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
		int i = goodsTypeDao.addGoodsType(goodsType);
		response.getWriter().write(i);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
