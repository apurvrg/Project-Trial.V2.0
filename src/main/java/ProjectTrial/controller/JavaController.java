package ProjectTrial.controller;

import ProjectTrial.entity.Java;
import ProjectTrial.service.JavaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/java")
public class JavaController {
	
	private JavaService javaService;
	public int theJavaId = 0;

	public JavaController(JavaService theJavaService) {
		javaService = theJavaService;
	}
	
	
	
	@GetMapping("/javaQueList")
	public String listJavaQue(Model theModel) {
		
		List<Java> theJavaQue = javaService.findAll();
		
		theModel.addAttribute("javaQue", theJavaQue);
		return "/java/list-java";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Java theJava = new Java();
		
		theModel.addAttribute("java", theJava);
		
		return "/java/add-question";
	}
	
	@PostMapping("/save")
	public String saveQuestion(@ModelAttribute("java") Java theJava) {
		
		// save the question
		javaService.save(theJava);
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/java/javaQueList";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("javaId") int javaId,
									Model theModel) {
		
		// get the question from the service
		Java theJava = javaService.findById(javaId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("java", theJava);
		
		// send over to our form
		return "/java/add-question";			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("javaId") int javaId) {
		
		// delete the employee
		javaService.deleteById(javaId);
		
		// redirect to /java/javaQueList
		return "redirect:/java/javaQueList";
		
	}
	
	@GetMapping("/javaInstructions")
	public String javaInstuctions(Model theModel) {
		
		List<Java> javaListSize = javaService.findAll();

		int listSize = javaListSize.size();

		System.out.println("javaListSize" +listSize);
		
		theModel.addAttribute("listSize", listSize);
		
		theJavaId = 0;
		return "/java/java-instructions";
	}
	
	@GetMapping("/javaNext")
	public String javaNext(Model theModel ) {

		System.out.println("javaId before- "+ theJavaId);
		theJavaId++;
		System.out.println("javaId after- "+ theJavaId);

		int javaListSize = javaService.findAll().size();
		theModel.addAttribute("javaListSize", javaListSize);
		System.out.println("javaListSize - "+ javaListSize);

		if((theJavaId > 0) && (theJavaId < javaListSize)) {
			Java theJavaNext = javaService.findById(theJavaId);
			theModel.addAttribute("javaById", theJavaNext);
			System.out.println("java - "+theJavaNext);
			System.out.println("javaId - "+ theJavaId);

		}
		else {
			theJavaId = javaListSize;
			Java theJavaNext = javaService.findById(theJavaId);
			theModel.addAttribute("javaById", theJavaNext);

		}

		return "/java/start-java";
	}


	
	@GetMapping("/javaPrevious")
	public String javaPrevious(Model theModel) {
		
		System.out.println("javaId before- "+ theJavaId);
		
		int javaListSize = javaService.findAll().size();
		theModel.addAttribute("javaListSize", javaListSize);
		System.out.println("javaListSize - "+ javaListSize);
		
		theJavaId--;
		System.out.println("javaId previous -"+ theJavaId);
		
		if(theJavaId < 1) {
			theJavaId = 1;
			Java theJavaNext = javaService.findById(theJavaId);
			theModel.addAttribute("javaById", theJavaNext);
		}
		else {
		Java theJavaNext = javaService.findById(theJavaId);
		theModel.addAttribute("javaById", theJavaNext);
		}
		
		return "/java/start-java";
		
	}
	

}
