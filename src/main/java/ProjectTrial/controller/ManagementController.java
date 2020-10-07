package ProjectTrial.controller;

import ProjectTrial.entity.Java;
import ProjectTrial.entity.User;
import ProjectTrial.service.JavaService;
import ProjectTrial.service.UserService;
import ProjectTrial.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/management")
public class ManagementController {

	@Autowired
	private UserService userService;

	@Autowired
	private final JavaService javaService;

	private final Logger logger = Logger.getLogger(getClass().getName());

	public ManagementController(JavaService javaService) {
		this.javaService = javaService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/home")
	public String home() {
		return "/management/home";
	}
	
	@GetMapping("/subject-list")
	public String getSubjectList(Model theModel) {
		List<Java> javaListSize = javaService.findAll();

		int listSize = javaListSize.size();

		theModel.addAttribute("listSize", listSize);

		return "/management/subject-list";
	}

	@GetMapping("/getUsers")
	public String getUsers(Model theModel) {

		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		return "/management/users";
	}

	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {

		theModel.addAttribute("crmUser", new CrmUser());

		return "/management/register-user";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
			BindingResult theBindingResult,
			Model theModel) {

		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);

		// form validation
		if (theBindingResult.hasErrors()){
			return "/management/register-user";
		}

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null){
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return "/management/register-user";
		}

		// create user account
		userService.save(theCrmUser);

		logger.info("Successfully created user: " + userName);

		return "redirect:/management/getUsers";
	}
	
}
