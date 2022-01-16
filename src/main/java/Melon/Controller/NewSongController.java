package Melon.Controller;

import Melon.Service.NewSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewSongController {
	@Autowired
	NewSongService newSongService;

	@GetMapping("/")
	public String goToMain() {
		return "Melon/Main";
	}

	@GetMapping("NewSong/NewSongList")
	public String goToNewSong() {
		newSongService.NewSongSave();
		return "Melon/NewSong/NewSongList";
	}
}
