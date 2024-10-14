package com.lbs.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/adminLogin")
		public String adminLogin() {
			return "admin/AdminLogin";
	}
	
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
	
	@GetMapping("/leaveManagement")
		public String leaveManagement() {
			return "admin/leaveManagement";
	}
	
	@GetMapping("/empAttendance")
		public String empAttendance() {
			return "admin/empAttendance";
	}
	
	@GetMapping("/empLogin")
		public String empLogin() {
			return "admin/EmpLogin";
	}
	
	@GetMapping("/empIndex")
		public String empIndex() {
			return "admin/empIndex";
	}
	
}
