package Melon.Controller;

import Melon.Domain.DTO.MemberDTO;
import Melon.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@GetMapping("/Member/SignUp")
	public String goToSignUp() {
		return "Melon/Member/SignUp";
	}

	// 회원가입
	@GetMapping("/Member/SignUpController") @ResponseBody
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
	public String Login(@RequestParam("mid")String mid, @RequestParam("mpw")String mpw) {
		MemberDTO memberDTO = memberService.MemberLogin(mid, mpw);

		HttpSession session = request.getSession();
		session.setAttribute("MemberDTO", memberDTO);

		if(memberDTO.getMid() != null && memberDTO.getMname() != null && memberDTO.getMemail() != null) {
			return "1";
		} else {
			return "0";
		}
	}

	// 아이디 찾기


	// 비밀번호 찾기


	// 회원정보수정


	// 회원탈퇴퇴
}
