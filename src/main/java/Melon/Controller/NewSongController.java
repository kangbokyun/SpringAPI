package Melon.Controller;

import Melon.Domain.DTO.MemberDTO;
import Melon.Domain.DTO.NewSongDTO; 
import Melon.Domain.DTO.TJNewSongDTO;
import Melon.Domain.Entity.MemberEntity;
import Melon.Domain.Entity.NewSongEntity;
import Melon.Domain.Entity.NewSongRepository;
import Melon.Service.NewSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class NewSongController {
	@Autowired
	NewSongService newSongService;
	@Autowired
	NewSongRepository newSongRepository;
	@Autowired
	HttpServletRequest request;

	@GetMapping("/")
	public String goToMain(Model model) {
		HttpSession session = request.getSession();
		// ip가져오기
		// System.out.println("request.getRemoteAddr() : " + request.getRemoteAddr());

		ArrayList<NewSongDTO> newChartSong = newSongService.getNewSong();
		ArrayList<NewSongDTO> TJnewChart = newSongService.CheckTJSong(newChartSong);
		model.addAttribute("TJnewChart", TJnewChart);


		if(session.getAttribute("MemberDTO") != null) {
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("MemberDTO");
			ArrayList<String> getReserve = newSongService.FindReservationSinger(memberDTO.getMno(), newChartSong);
			if(getReserve != null) {
				model.addAttribute("ReserveSinger", getReserve);
			} else {
				model.addAttribute("ReserveSinger", "예약 정보가 없습니다.");
			}
			model.addAttribute("MemberDTO", memberDTO);
			return "Melon/Main";
		} else {
			session.setAttribute("MemberDTO", null);
			return "Melon/Main";
		}
	}

	@GetMapping("/CountSong") @ResponseBody
	public ArrayList<Integer> getSongCount() {
		ArrayList<Integer> count_month = newSongService.getSongYear();
		return count_month;
	}

	// 신곡 리스트
	@GetMapping("/NewSong/NewSongList")
	public String goToNewSong(@PageableDefault Pageable pageable, Model model) {
		HttpSession session = request.getSession();
		Page<NewSongEntity> newsongDTOS = newSongService.NewSongPaging(pageable);
		model.addAttribute("newSongDTOS", newsongDTOS);

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

	// tj 노래방 신곡 리스트 긁기
//	@GetMapping("/NewSong/NewSong")
}
