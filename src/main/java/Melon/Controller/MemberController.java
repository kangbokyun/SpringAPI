package Melon.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("/Member/SignUp")
	public String goToSignUp() {
		return "Melon/Member/SignUp";
	}
}
