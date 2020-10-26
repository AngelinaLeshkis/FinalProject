package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.persistence.CommentRepository;
import com.leverx.dealerstat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> getPostsByTraderId(Long id) {
        return null;
    }

    @Override
    public void deleteCommentByCommentId(Long id) {

    }

    @Override
    public List<Comment> getComments() {
        return null;
    }

    @Override
    public Comment getCommentByCommentId(Long id) {
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return null;
    }
}
