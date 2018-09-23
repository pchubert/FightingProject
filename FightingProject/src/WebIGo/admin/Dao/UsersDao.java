package WebIGo.admin.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import WebIGo.admin.Bean.User;
import WebIGo.admin.utils.MybatisUtil;

import javax.jws.soap.SOAPBinding;

public class UsersDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();
	
	public List<User> listUsers(){
		SqlSession session = sessionFactory.openSession();
		UsersMapper usersMapper = session.getMapper(UsersMapper.class);
		List<User> usersList = usersMapper.listUsers();
		return usersList;
	}
	
	public int addUser2(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		UsersMapper usersMapper = session.getMapper(UsersMapper.class);
		usersMapper.addUser2(user);
		session.commit();
		return 0;
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		UsersMapper usersMapper = session.getMapper(UsersMapper.class);
		usersMapper.addUser(user);
		session.commit();
		return 0;
	}
	
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
        UsersMapper userMapper = session.getMapper(UsersMapper.class);
        userMapper.deleteUser(user);
        session.commit();
        return 0;
	}
	
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		UsersMapper userMapper = session.getMapper(UsersMapper.class);
		userMapper.updateUser(user);
		session.commit();
		return 0;
	}
	
	public User find(String username, String userpsw) {
        SqlSession session = sessionFactory.openSession();
        UsersMapper userMapper = session.getMapper(UsersMapper.class);
        User user=new User();
        user.setUname(username);
        user.setUpwd(userpsw);
        user =userMapper.find(user);
        session.close();
        return user;
    }

	public User findByName(String Uname){
		SqlSession session=sessionFactory.openSession();
		UsersMapper usersMapper=session.getMapper(UsersMapper.class);
		User user=new User();
		user.setUname(Uname);
		user=usersMapper.findByName(user);
		session.close();
		return  user;
	}

	public boolean existUphone(String Uphone){
		User user=new User();
		user.setUphone(Uphone);

		SqlSession session = sessionFactory.openSession();
		UsersMapper userMapper = session.getMapper(UsersMapper.class);

		return userMapper.existUphone(user.getUphone())!=null;
	}

    public String register(User user){
        SqlSession session = sessionFactory.openSession();
        UsersMapper userMapper = session.getMapper(UsersMapper.class);
        if(userMapper.existUname(user.getUname())!=null){
            //已注册

            return  "用户名已存在";
        }else if(userMapper.existUphone(user.getUphone())!=null){
            return  "手机号已被注册";
        } else{
            //添加用户
            //System.out.println(user.getUname()+" "+user.getUphone()+" "+user.getUpwd());
            int result=userMapper.addUser(user);
            System.out.println(result);
            session.commit();
            session.close();
            return "注册成功";
        }
    }
	
}
