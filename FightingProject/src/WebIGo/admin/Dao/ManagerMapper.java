package WebIGo.admin.Dao;
import java.util.List;

import WebIGo.admin.Bean.*;

/**
 * Created by yyr on 15-11-27.
 */
public interface ManagerMapper {
	List<Manager> listManagers();
	
    /**
     * 查找(单件)
     **/
    Manager find(Manager manager);

}