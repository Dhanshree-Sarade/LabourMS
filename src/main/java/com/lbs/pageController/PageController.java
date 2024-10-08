package com.lbs.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/index")
		public String indexPage() {
			return "admin/index";
		}
	
	@GetMapping("/empRegistration")
		public String empRegistartion() {
			return "admin/empRegistration";
	}
	
	@GetMapping("/empList")
		public String empList() {
			return "admin/empList";
	}
	
	@GetMapping("/empStatus")
		public String empStatus() {
			return "admin/empStatus";
	}
}
