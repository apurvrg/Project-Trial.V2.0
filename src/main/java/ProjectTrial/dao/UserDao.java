package ProjectTrial.dao;

import java.util.List;

import ProjectTrial.entity.User;

public interface UserDao{
	
	public User findByUserName(String userName);
    
    public void save(User user);
    
    public List<User> findAll();
    
    
}
