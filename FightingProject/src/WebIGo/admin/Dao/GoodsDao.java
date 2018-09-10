package WebIGo.admin.Dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import WebIGo.admin.Bean.Goods;
import WebIGo.admin.Bean.GoodsType;
import WebIGo.admin.utils.MybatisUtil;

public class GoodsDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();

	public int addGoods(Goods  goods)
	{
		SqlSession session = sessionFactory.openSession();
		GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
		goodsMapper.addGoods(goods);
		session.commit();
		return 0;
		
	}
}
