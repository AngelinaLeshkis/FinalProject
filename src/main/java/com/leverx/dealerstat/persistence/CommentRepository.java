package com.leverx.dealerstat.persistence;

import com.leverx.dealerstat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
