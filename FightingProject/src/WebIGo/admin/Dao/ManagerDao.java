package WebIGo.admin.Dao;
import WebIGo.admin.Bean.*;
import WebIGo.admin.utils.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class ManagerDao {
    private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();
    
	public List<Manager> listManagers() {
	    //创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession();
		ManagerMapper managerMapper = session.getMapper(ManagerMapper.class);
		List<Manager> managerList = managerMapper.listManagers();
		return managerList;
	}
   
    public Manager find(String username, String userpsw) {
    	SqlSession session = sessionFactory.openSession();
        ManagerMapper managerMapper = session.getMapper(ManagerMapper.class);
        Manager manager= new Manager();
    	manager.setAname(username);
    	manager.setApwd(userpsw);
    	manager = managerMapper.find(manager);
    	session.close();
        return manager;
  
    }


}