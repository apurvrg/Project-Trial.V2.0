package ProjectTrial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ManagementController {
	
	@GetMapping("/home")
	public String home() {
		return "/management/home";
	}
	
	@GetMapping("/subject-list")
	public String getSubjectList() {		
		return "/management/subject-list";
	}
	
}
