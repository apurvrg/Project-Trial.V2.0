package ProjectTrial.dao;

import ProjectTrial.entity.User;

public interface AdminDao {
	
	public User findByUserName(String userName);
    
    public void save(User user);
    

}
