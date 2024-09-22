package com.example.commonproject.board.repository;

import com.example.commonproject.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByWriterContaining(String writer, Pageable pageable);
    Page<Board> findByTitleContaining(String title, Pageable pageable);
}
