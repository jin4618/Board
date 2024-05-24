package org.example.boardproject.service;

import lombok.RequiredArgsConstructor;
import org.example.boardproject.domain.Board;
import org.example.boardproject.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 목록 조회
    /*public Iterable<Board> findAllList() {
        return boardRepository.findAll();
    }*/

    // 페이징 처리 목록
    public Page<Board> findAllList(Pageable pageable) {
        Pageable sortedByDateAsc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createAt"));
        return boardRepository.findAll(sortedByDateAsc);
    }

    // 게시글 상세 조회
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // 게시글 등록
    @Transactional
    public void saveBoard(Board board) {
        board.setCreateAt(LocalDateTime.now((ZoneId.of("Asia/Seoul"))));
        board.setUpdatedAt(LocalDateTime.now((ZoneId.of("Asia/Seoul"))));

        boardRepository.save(board);
    }

    // 게시물 수정
    @Transactional
    public void updateBoard(Board board) {
        board.setUpdatedAt(LocalDateTime.now((ZoneId.of("Asia/Seoul"))));
        boardRepository.save(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }


}
