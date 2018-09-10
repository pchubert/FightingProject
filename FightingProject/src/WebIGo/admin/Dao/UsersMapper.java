package WebIGo.admin.Dao;

import java.util.List;

import WebIGo.admin.Bean.User;

public interface UsersMapper {
	List<User> listUsers();
	void addUser(User user);

}
