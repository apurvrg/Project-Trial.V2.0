package ProjectTrial.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ProjectTrial.entity.User;
import ProjectTrial.service.AdminService;
import ProjectTrial.user.Admin;

@Controller
@RequestMapping("/adminRegister")
public class AdminRegistrationController {
	
	@Autowired
	private AdminService adminService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	public AdminRegistrationController(AdminService theAdminService) {
		adminService = theAdminService;
	}
	
	@GetMapping("/showAdminRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("admin", new Admin());
		
		return "admin-registration-form";
	}
	
	@PostMapping("/processAdminRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("admin") Admin theAdmin, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theAdmin.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "admin-registration-form";
	        }

		// check the database if user already exists
        User existing = adminService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("admin", new Admin());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "admin-registration-form";
        }

        // create user account        						
        adminService.save(theAdmin);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";	
	}
}
