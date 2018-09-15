package WebIGo.admin.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import WebIGo.admin.Bean.Goods;
import WebIGo.admin.utils.MybatisUtil;

public class GoodsDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();

	public List<Goods> listGoods()
	{
		SqlSession session = sessionFactory.openSession();
		GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
		List<Goods> goodsList = goodsMapper.listGoods();
		return goodsList;
		
	}
	
	public int addGoods(Goods  goods)
	{
		SqlSession session = sessionFactory.openSession();
		GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
		goodsMapper.addGoods(goods);
		session.commit();
		return 0;
		
	}
	
	public int deleteGoods(Goods  goods)
	{
		SqlSession session = sessionFactory.openSession();
		GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
		goodsMapper.deleteGoods(goods);
		session.commit();
		return 0;

	}

	public int updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
		goodsMapper.updateGoods(goods);
		session.commit();
		return 0;
	}

}
