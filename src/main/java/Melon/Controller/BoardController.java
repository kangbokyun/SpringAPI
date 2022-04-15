package Melon.Controller;

import Melon.Domain.DTO.BoardDTO;
import Melon.Domain.DTO.MiddleCategoryDTO;
import Melon.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    HttpServletRequest request;

    // 게시판 메인으로
    @GetMapping("/Board/BoardMain")
    public String goToBoardMain(Model model) {
        boardService.CreateCategory();
        List<MiddleCategoryDTO> middleCategoryDTOS = boardService.getMiddleC();
        model.addAttribute("MCategory", middleCategoryDTOS);
        return "Melon/Board/BoardMain";
    }
}
