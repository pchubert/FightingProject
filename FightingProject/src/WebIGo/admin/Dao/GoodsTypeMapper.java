package WebIGo.admin.Dao;

import java.util.List;

import WebIGo.admin.Bean.GoodsType;


public interface GoodsTypeMapper {
	 List<GoodsType> listGoodsType();
	 int addGoodsType(GoodsType goodsType);
}
