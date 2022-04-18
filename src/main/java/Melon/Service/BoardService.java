package Melon.Service;

import Melon.Domain.DTO.BoardDTO;
import Melon.Domain.DTO.CategoryDTO;
import Melon.Domain.DTO.MemberDTO;
import Melon.Domain.DTO.MiddleCategoryDTO;
import Melon.Domain.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MiddleCategoryRepository middleCategoryRepository;
    @Autowired
    HttpServletRequest request;

    // 어디서 시작하든 카테고리 Database 채우기
    public List<CategoryDTO> CreateCategory() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        if(categoryEntities == null) {
            CategoryEntity categoryEntity1 = CategoryEntity.builder().catename("노래").catecode("0001").build();
            CategoryEntity categoryEntity2 = CategoryEntity.builder().catename("가수").catecode("0002").build();
            CategoryEntity categoryEntity3 = CategoryEntity.builder().catename("자유").catecode("0003").build();
            categoryRepository.save(categoryEntity1); categoryRepository.save(categoryEntity2); categoryRepository.save(categoryEntity3);
        } else {
            for(CategoryEntity categoryEntity : categoryEntities) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCateno(categoryEntity.getCateno());
                categoryDTO.setCatename(categoryEntity.getCatename());
                categoryDTO.setCatecode(categoryEntity.getCatecode());
                categoryDTOS.add(categoryDTO);
            }
            return categoryDTOS;
        }
        List<MiddleCategoryEntity> middleCategoryEntities = middleCategoryRepository.findAll();
        if(middleCategoryEntities == null) {
            MiddleCategoryEntity middleCategory1 = MiddleCategoryEntity.builder().mcname("발라드").mccode("0001").build();
            MiddleCategoryEntity middleCategory2 = MiddleCategoryEntity.builder().mcname("댄스").mccode("0002").build();
            MiddleCategoryEntity middleCategory3 = MiddleCategoryEntity.builder().mcname("아이돌").mccode("0003").build();
            MiddleCategoryEntity middleCategory4 = MiddleCategoryEntity.builder().mcname("락").mccode("0004").build();
            MiddleCategoryEntity middleCategory5 = MiddleCategoryEntity.builder().mcname("재즈").mccode("0005").build();

            middleCategoryRepository.save(middleCategory1);
            middleCategoryRepository.save(middleCategory2);
            middleCategoryRepository.save(middleCategory3);
            middleCategoryRepository.save(middleCategory4);
            middleCategoryRepository.save(middleCategory5);
        }
        return null;
    }

    // 작은 카테고리 가져오기
    public List<MiddleCategoryDTO> getMiddleC() {
        List<MiddleCategoryEntity> middleCategoryEntities = middleCategoryRepository.findAll();
        List<MiddleCategoryDTO> middleCategoryDTOS = new ArrayList<>();
        for(MiddleCategoryEntity middleCategory : middleCategoryEntities) {
            MiddleCategoryDTO middleCategoryDTO = new MiddleCategoryDTO();
            middleCategoryDTO.setMcno(middleCategory.getMcno());
            middleCategoryDTO.setMcname(middleCategory.getMcname());
            middleCategoryDTO.setMccode(middleCategory.getMccode());
            middleCategoryDTOS.add(middleCategoryDTO);
        }
        return middleCategoryDTOS;
    }

    // 작은 카테고리 클릭 시 반환되는 번호로 카테고리 이름 가져오기
    public String MiddleCategoryName(int indexNo) {
        Optional<MiddleCategoryEntity> mc = middleCategoryRepository.findById(indexNo);
        String mcn = mc.get().getMcname();
        return mcn;
    }

    // 글쓰기
    public boolean BoardWrite(String title, String contents, int indexNo) {
        HttpSession session = request.getSession();
        int cateNo = (int)session.getAttribute("cateNo");
        Optional<CategoryEntity> categoryEntities = categoryRepository.findById(cateNo + 1);
        Optional<MiddleCategoryEntity> middleCategoryEntities = middleCategoryRepository.findById(indexNo + 1);
        MemberDTO member = (MemberDTO) session.getAttribute("MemberDTO");
        BoardEntity boardEntity = BoardEntity.builder()
                .btitle(title)
                .bcontents(contents)
                .categoryEntity(categoryEntities.get())
                .middleCategory(middleCategoryEntities.get())
                .bview("0")
                .bwriter(member.getMid())
                .build();
        boardRepository.save(boardEntity);
        return true;
    }

    // 카테고리에 맞는 글 목록 가져오기
    public List<BoardDTO> BoardList() {
        HttpSession session = request.getSession();
        int cateNo = (int)session.getAttribute("cateNo");
        int midcateNo = (int)session.getAttribute("MiddleCategorySelectNo");
        List<BoardDTO> boardDTOS = new ArrayList<>();
        List<BoardEntity> boardEntities = boardRepository.findAll();
        for(BoardEntity boardEntity : boardEntities) {
            if(boardEntity.getCategoryEntity().getCateno() == cateNo + 1 && boardEntity.getMiddleCategory().getMcno() == midcateNo + 1) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setBno(boardEntity.getBno());
                boardDTO.setBtitle(boardEntity.getBtitle());
                boardDTO.setBcontents(boardEntity.getBcontents());
                boardDTO.setBwriter(boardEntity.getBwriter());
                boardDTO.setBview(boardEntity.getBview());
                boardDTOS.add(boardDTO);
            }
        }
        return boardDTOS;
    }
}
