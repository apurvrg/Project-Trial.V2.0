package ProjectTrial.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ProjectTrial.entity.User;
import ProjectTrial.user.Admin;

public interface AdminService extends UserDetailsService {
	
	public User findByUserName(String userName);

	public void save(Admin admin);
}
