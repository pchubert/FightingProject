package WebIGo.admin.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import WebIGo.admin.Bean.OrderInfo;
import WebIGo.admin.utils.MybatisUtil;

public class OrderDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();

	public List<OrderInfo> listOrderInfos() {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);
		List<OrderInfo> orderInfosList = orderMapper.listOrderInfos();
		return orderInfosList;
	}

	public int addOrder(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
