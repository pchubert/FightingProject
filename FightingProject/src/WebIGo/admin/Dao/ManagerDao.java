package WebIGo.admin.Dao;
import WebIGo.admin.Bean.*;
import WebIGo.admin.utils.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class ManagerDao {
    private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();
    //创建能执行映射文件中sql的sqlSession
   
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