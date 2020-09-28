package ProjectTrial.controller;

import ProjectTrial.entity.User;
import ProjectTrial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private final UserService userService;
	
	public UserController(UserService theUserService) {
		userService = theUserService;
	}
	
	@GetMapping("/getUsers")
	public String getUsers(Model theModel) {
		
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		return "/management/users";
	}

}
