package ProjectTrial.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ProjectTrial.entity.User;
import ProjectTrial.user.CrmUser;

public interface UserService extends UserDetailsService{
	
	public User findByUserName(String userName);

	public void save(CrmUser crmUser);

	public List<User> findAll();
	
}
