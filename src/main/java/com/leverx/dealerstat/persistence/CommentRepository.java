package com.leverx.dealerstat.persistence;

import com.leverx.dealerstat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByTraderId(Long id);

    @Query(value = " select trader_id, name_of_trader as trader_name, avg(rating) as trader_rating from comment " +
            "inner join trader " +
            "on comment.trader_id = trader.id where trader.approved = true and comment.approved = true " +
            "group by trader_id order by trader_rating desc", nativeQuery = true)
    List<Object[]> findAllTradersByRating();
}
