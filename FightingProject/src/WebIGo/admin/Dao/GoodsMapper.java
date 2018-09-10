package WebIGo.admin.Dao;

import java.util.List;

import WebIGo.admin.Bean.Goods;

public interface GoodsMapper {
	List<Goods> listGoods();
	int addGoods(Goods goods);

}
