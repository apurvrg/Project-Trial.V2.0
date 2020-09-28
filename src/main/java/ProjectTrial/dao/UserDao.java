package ProjectTrial.dao;

import ProjectTrial.entity.User;

import java.util.List;

public interface UserDao{
	
	User findByUserName(String userName);
    
	void save(User user);
    
	List<User> findAll();
    
    
}
