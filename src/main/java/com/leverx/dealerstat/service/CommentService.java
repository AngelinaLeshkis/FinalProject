package com.leverx.dealerstat.service;

import com.leverx.dealerstat.entity.Comment;
import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<Comment> getPostsByTraderId(Long id);

    void deleteCommentByCommentId(Long id);

    List<Comment> getComments();

    Comment getCommentByCommentId(Long id);

    Comment updateComment(Comment comment);
}
