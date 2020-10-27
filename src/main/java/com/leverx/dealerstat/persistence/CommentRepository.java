package com.leverx.dealerstat.persistence;

import com.leverx.dealerstat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByUserId(Long id);
}
