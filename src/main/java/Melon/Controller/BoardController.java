package Melon.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    // 게시판 메인으로
    @GetMapping("/Board/BoardMain")
    public String goToBoardMain() {
        return "Melon/Board/BoardMain";
    }
}
