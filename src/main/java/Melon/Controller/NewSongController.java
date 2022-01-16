package Melon.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewSongController {
	@GetMapping("/")
	public String goToMain() {
		return "Melon/Main";
	}
}
