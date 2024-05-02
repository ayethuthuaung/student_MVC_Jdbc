package student.com.controllers.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	@GetMapping({"/","/login"})
	public String login(HttpSession session) {
		session.invalidate();
		return "login";		
	}
	
	@GetMapping("/dashboard")
	public String dashbord(HttpSession session) {
		if(session.getAttribute("userLogin")==null) {
			
			return "redirect:/login";
		}
		return "dashboard";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/addAdmin")
	public String addAdmin() {
		
		return "addAdmin";
	}
	
	
	@GetMapping("/viewUser")
	public String viewUser() {
		
		return "viewUser";
	}
	
	
	@GetMapping("/addCourse")
	public String addCourse() {
		
		return "addCourse";
	}
	
	
	@GetMapping("/viewCourse")
	public String viewCourse() {
		
		return "viewCourse";
	}
	
	
	@GetMapping("/editCourse")
	public String editCourse() {
		
		return "editCourse";
	}
	@GetMapping("/addStudent")
	public String addStudent() {
	
		return "addStudent";
	}
	
	@GetMapping("/viewStudent")
	public String viewStudent() {
		
		return "viewStudent";
	}
	
	@GetMapping("/editStudent")
	public String editStudent() {
		
		return "editStudent";
	}
	
	

}
