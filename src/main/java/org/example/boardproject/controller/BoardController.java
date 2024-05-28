package org.example.boardproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.boardproject.domain.Board;
import org.example.boardproject.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    // 페이징 처리 목록
    @GetMapping
    public String boardList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Board> boards = boardService.findAllList(pageable);

        model.addAttribute("boards", boards);

        return "board/list";
    }

    // 게시글 상세 조회
    @GetMapping("/view/{id}")
    public String boardView(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "board/view";
    }

    // 게시글 등록 폼
    @GetMapping("/writeForm")
    public String boardWriteForm(Model model) {
        Board board = new Board();
        model.addAttribute("board", board);
        return "board/writeForm";
    }

    // 게시글 등록
    @PostMapping("/write")
    public String boardWrite(@ModelAttribute("board") Board board) {
        boardService.saveBoard(board);
        return "redirect:/board/view/" + board.getId();
    }

    // 게시글 수정 폼
    @GetMapping("/updateForm/{id}")
    public String boardUpdateForm(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "board/updateForm";
    }

    // 게시글 수정
    @PostMapping("/update/{password}")
    public String boardUpdate(@PathVariable String password, @ModelAttribute("board") Board board, Model model) {
        if (password.equals(board.getPassword())) { // 클라이언트가 작성한 비밀번호가 일치하면 수정 진행
            boardService.updateBoard(board);
        } else {
            model.addAttribute("pwCheckMsg", "비밀번호가 일치하지 않습니다.");
            return "board/updateForm";
        }

        return "redirect:/board/view/" + board.getId();
    }

    // 게시글 삭제 폼
    @GetMapping("/deleteForm/{id}")
    public String boardDeleteForm(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "board/deleteForm";
    }

    // 게시글 삭제
    @PostMapping("/delete/{password}")
    public String deleteBoard(@PathVariable String password, @ModelAttribute("board") Board board, Model model) {
        if (password.equals(board.getPassword())) { // 클라이언트가 작성한 비밀번호가 일치하면 삭제 진행
            boardService.deleteBoardById(board.getId());
        } else {
            model.addAttribute("pwCheckMsg", "비밀번호가 일치하지 않습니다.");
            return "board/deleteForm";
        }
        return "redirect:/board";
    }
}
