package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment saveComment(CreateCommentDTO comment);

    List<Comment> getCommentsByTraderId(Long id);

    void deleteCommentByCommentId(Long id);

    List<CreateCommentDTO> getComments();

    CreateCommentDTO getCommentByCommentId(Long id);

    Comment updateComment(Comment comment);

}
