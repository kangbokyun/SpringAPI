package Melon.Controller;

import Melon.Domain.DTO.NewSongDTO;
import Melon.Domain.Entity.NewSongRepository;
import Melon.Service.NewSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class NewSongController {
	@Autowired
	NewSongService newSongService;
	@Autowired
	HttpSession session;
	@Autowired
	NewSongRepository newSongRepository;

	@GetMapping("/")
	public String goToMain() {
		return "Melon/Main";
	}

	@GetMapping("/NewSong/NewSongList")
	public String goToNewSong(ArrayList<NewSongDTO> newSongDTO, Model model) {
		if(newSongDTO != null) {
			newSongDTO = newSongService.getNewSong();
			session.setAttribute("newSong", newSongDTO);
			model.addAttribute("newSongDTO", newSongDTO);
		}

		return "Melon/NewSong/NewSongList";
	}

	@GetMapping("/NewSong/NewSongUpdate")
	@ResponseBody
	public String NewSongUpdate() {
		boolean result = newSongService.NewSongSave();
		if(result) {
			return "1";
		} else {
			return "2";
		}
	}
}
