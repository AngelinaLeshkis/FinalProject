package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.CommentForNewTraderDTO;
import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment saveComment(CreateCommentDTO comment);

    List<Comment> getCommentsByTraderId(Long id);

    void deleteCommentByCommentId(Long id);

    List<CreateCommentDTO> getComments();

    CreateCommentDTO getCommentByCommentId(Long id);

    Comment approveComment(Long id);

    Comment declineComment(Long id);

    Comment saveCommentWithAddingNewTrader(CommentForNewTraderDTO commentForNewTrader);
}
