package Melon.Controller;

import Melon.Domain.DTO.MemberDTO;
import Melon.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	HttpServletRequest request;

	// 회원가입페이지로
	@GetMapping("/Member/SignUp")
	public String goToSignUp() {
		return "Melon/Member/SignUp";
	}

	// 회원정보페이지로
	@GetMapping("/Member/MyInfo")
	public String goToMyInfo(Model model) {
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("MemberDTO");
		model.addAttribute("MemberDTO", memberDTO);
		return "Melon/Member/MyInfo";
	}

	// 회원가입
	@GetMapping("/Member/SignUpController")
	@ResponseBody
	public String SignUpController(@RequestParam("mid")String mid, @RequestParam("mpw")String mpw, @RequestParam("mname")String mname, @RequestParam("memail")String memail, @RequestParam("mphone")String mphone, @RequestParam("maddress")String maddress) {
		boolean result = memberService.MemberSignUp(mid, mpw, mname, memail, mphone, maddress);
		if(result) {
			return "1";
		} else {
			return "0";
		}
	}

	// 로그인
	@GetMapping("/Member/Login")
	@ResponseBody
	public String Login(@RequestParam("mid")String mid, @RequestParam("mpw")String mpw) {
		MemberDTO memberDTO = memberService.MemberLogin(mid, mpw);

		if(memberDTO != null && memberDTO.getMid().equals(mid)) {
			HttpSession session = request.getSession();
			session.setAttribute("MemberDTO", memberDTO);
			return "1";
		} else {
			return "0";
		}
	}

	// 아이디 찾기


	// 비밀번호 찾기


	// 로그아웃
	@GetMapping("/Member/Logout")
	@ResponseBody
	public String Logout(@RequestParam("mno")int mno) {
		boolean result = memberService.Logout(mno);
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("MemberDTO", null);
			return "1";
		} else {
			return "0";
		}
	}

	// 회원정보수정


	// 회원탈퇴


	// 가수예약하기
	@GetMapping("/Member/Reservation")
	@ResponseBody
	public String Reservation(@RequestParam("reserv")String reserv, @RequestParam("mno")int mno) {
		boolean result = memberService.Reservation(reserv, mno);
		if(result) {
			return "1";
		} else {
			return "0";
		}
	}
}
