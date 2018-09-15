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
@WebServlet("/DeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodsServlet() {
        super();
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
        //按照 Gid 删除商品集
        goods.setGid(Integer.parseInt(new String(request.getParameter("Gid").getBytes("ISO8859-1"),"UTF-8")));
        //goods.setGid(2);//测试用

        //创建 GoodsDao 对象
        GoodsDao goodsDao = new GoodsDao();
        
        //调用 delete() 方法
        int i = goodsDao.deleteGoods(goods);
        
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
