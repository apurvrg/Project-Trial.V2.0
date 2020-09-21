package ProjectTrial.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ProjectTrial.dao.AdminDao;
import ProjectTrial.dao.RoleDao;
import ProjectTrial.entity.Role;
import ProjectTrial.entity.User;
import ProjectTrial.user.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	// need to inject user dao
			@Autowired
			private AdminDao adminDao;

			@Autowired
			private RoleDao roleDao;
			
			@Autowired
			private BCryptPasswordEncoder passwordEncoder;

			@Override
			@Transactional
			public User findByUserName(String userName) {
				// check the database if the user already exists
				return adminDao.findByUserName(userName);
			}

			@Override
			@Transactional
			public void save(Admin admin) {
				User user = new User();
				 // assign user details to the user object
				user.setUserName(admin.getUserName());
				user.setPassword(passwordEncoder.encode(admin.getPassword()));
				user.setFirstName(admin.getFirstName());
				user.setLastName(admin.getLastName());
				user.setEmail(admin.getEmail());

				// give user default role of "employee"
				user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_ADMIN")));

				 // save user in the database
				adminDao.save(user);
			}

			@Override
			@Transactional
			public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
				User user = adminDao.findByUserName(userName);
				if (user == null) {
					throw new UsernameNotFoundException("Invalid username or password.");
				}
				return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
						mapRolesToAuthorities(user.getRoles()));
			}

			private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
				return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
			}		

}
