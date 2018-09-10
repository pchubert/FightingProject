package WebIGo.admin.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import WebIGo.admin.Bean.GoodsType;
import WebIGo.admin.utils.MybatisUtil;

public class GoodsTypeDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();
	public List<GoodsType> listGoodsType()
	{
		SqlSession session = sessionFactory.openSession();
		GoodsTypeMapper goodsTypeMapper = session.getMapper(GoodsTypeMapper.class);
		List<GoodsType> typeList = goodsTypeMapper.listGoodsType();
		return typeList;
		
	}
	public int addGoodsType(GoodsType  goodsType)
	{
		SqlSession session = sessionFactory.openSession();
		GoodsTypeMapper goodsTypeMapper = session.getMapper(GoodsTypeMapper.class);
		goodsTypeMapper.addGoodsType(goodsType);
		session.commit();
		return 0;
		
	}


}
