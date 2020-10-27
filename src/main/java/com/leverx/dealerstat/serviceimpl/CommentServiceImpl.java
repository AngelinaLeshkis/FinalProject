package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.persistence.CommentRepository;
import com.leverx.dealerstat.persistence.UserRepository;
import com.leverx.dealerstat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;
    private UserRepository userRepo;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo, UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Comment saveComment(CreateCommentDTO commentDTO) {
        User user = userRepo.findById(commentDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id = " + commentDTO.getUserId()));
        Comment comment = new Comment();
        comment.setApproved(commentDTO.isApproved());
        comment.setUser(user);
        comment.setDateOfCreation(commentDTO.getDateOfCreation());
        comment.setRating(commentDTO.getRating());
        comment.setText(commentDTO.getText());
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getCommentsByTraderId(Long id) {
        return commentRepo.findCommentsByUserId(id);
    }

    @Override
    public void deleteCommentByCommentId(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<CreateCommentDTO> getComments() {
        Iterable<Comment> comments = new ArrayList<>();
        comments = commentRepo.findAll();
        List<CreateCommentDTO> commentsWithUsers = new ArrayList<>();
        for (Comment comment: comments) {
            CreateCommentDTO commentWithUser = new CreateCommentDTO();
            commentWithUser.setId(comment.getId());
            commentWithUser.setDateOfCreation(comment.getDateOfCreation());
            commentWithUser.setApproved(comment.isApproved());
            commentWithUser.setRating(comment.getRating());
            commentWithUser.setText(comment.getText());
            commentWithUser.setUserId(comment.getUser().getId());
            commentsWithUsers.add(commentWithUser);
        }
        return commentsWithUsers;
    }

    @Override
    public Optional<Comment> getCommentByCommentId(Long id) {
        return commentRepo.findById(id);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepo.save(comment);
    }
}
