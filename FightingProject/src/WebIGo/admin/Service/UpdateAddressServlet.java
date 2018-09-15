package WebIGo.admin.Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebIGo.admin.Bean.Address;
import WebIGo.admin.Dao.AddressDao;

/**
 * Servlet implementation class UpdateAddressServlet
 */
@WebServlet("/UpdateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Address address = new Address();
		
		//修改数据的地址 id
		address.setId(Integer.parseInt(new String(request.getParameter("id").getBytes("ISO8859-1"),"UTF-8")));
		
		//修改数据
		address.setName(new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"));
		address.setTelephone(new String(request.getParameter("telephone").getBytes("ISO8859-1"),"UTF-8"));
		address.setBuildingName(new String(request.getParameter("buildingName").getBytes("ISO8859-1"),"UTF-8"));
		address.setIntroduction(new String(request.getParameter("introduction").getBytes("ISO8859-1"),"UTF-8"));
		
//		address.setId(Integer.parseInt("2"));//测试用
//		address.setTelephone("1215");
		
		AddressDao addressDao = new AddressDao();
		int i= addressDao.updateAddress(address);
		
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
