package WebIGo.admin.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import WebIGo.admin.Bean.User;
import WebIGo.admin.utils.MybatisUtil;

public class UsersDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();
	
	public List<User> listUsers(){
		SqlSession session = sessionFactory.openSession();
		UsersMapper usersMapper = session.getMapper(UsersMapper.class);
		List<User> usersList = usersMapper.listUsers();
		return usersList;
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		UsersMapper usersMapper = session.getMapper(UsersMapper.class);
		usersMapper.addUser(user);
		session.commit();
		return 0;
	}
}
