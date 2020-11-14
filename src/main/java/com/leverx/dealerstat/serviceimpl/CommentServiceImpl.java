package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.dto.CommentForNewTraderDTO;
import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.persistence.CommentRepository;
import com.leverx.dealerstat.persistence.TraderRepository;
import com.leverx.dealerstat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;
    private TraderRepository traderRepo;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo, TraderRepository traderRepo) {
        this.commentRepo = commentRepo;
        this.traderRepo = traderRepo;
    }

    @Override
    public Comment saveComment(CreateCommentDTO commentDTO) {
        Trader trader = traderRepo.findById(commentDTO.getTraderId())
                .orElseThrow(() -> new RuntimeException("Trader not found with id = " + commentDTO.getTraderId()));

        if (trader.isApproved()) {
            Comment comment = new Comment();
            comment.setApproved(commentDTO.isApproved());
            comment.setTrader(trader);
            comment.setDateOfCreation(commentDTO.getDateOfCreation());
            comment.setRating(commentDTO.getRating());
            comment.setText(commentDTO.getText());
            return commentRepo.save(comment);
        }

        return null;

    }

    @Override
    public List<Comment> getCommentsByTraderId(Long id) {
        return commentRepo.findCommentsByTraderId(id);
    }

    @Override
    public void deleteCommentByCommentId(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<CreateCommentDTO> getComments() {
        Iterable<Comment> comments = commentRepo.findAll();
        List<CreateCommentDTO> commentsWithUsers = new ArrayList<>();
        for (Comment comment : comments) {
            CreateCommentDTO commentWithUser = new CreateCommentDTO();
            commentWithUser.setId(comment.getId());
            commentWithUser.setDateOfCreation(comment.getDateOfCreation());
            commentWithUser.setApproved(comment.isApproved());
            commentWithUser.setRating(comment.getRating());
            commentWithUser.setText(comment.getText());
            commentWithUser.setTraderId(comment.getTrader().getId());
            commentsWithUsers.add(commentWithUser);
        }
        return commentsWithUsers;
    }

    @Override
    public CreateCommentDTO getCommentByCommentId(Long id) {
        Optional<Comment> comment = commentRepo.findById(id);
        CreateCommentDTO commentDTO = new CreateCommentDTO();
        commentDTO.setId(comment.get().getId());
        commentDTO.setText(comment.get().getText());
        commentDTO.setRating(comment.get().getRating());
        commentDTO.setApproved(comment.get().isApproved());
        commentDTO.setDateOfCreation(comment.get().getDateOfCreation());
        commentDTO.setTraderId(comment.get().getTrader().getId());
        return commentDTO;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Comment approveComment(Long id) {
        Comment savedComment = commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id = " + id));

        if (savedComment.getTrader().isApproved()) {
            savedComment.setApproved(true);
        }

        return commentRepo.save(savedComment);
    }

    @Override
    public Comment declineComment(Long id) {
        Comment savedComment = commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id = " + id));

        if (savedComment.getTrader().isApproved()) {
            savedComment.setApproved(false);
        }

        return commentRepo.save(savedComment);
    }

    @Override
    public Comment saveCommentWithAddingNewTrader(CommentForNewTraderDTO commentForNewTrader) {

        if (traderRepo.findTraderByNameOfTrader(commentForNewTrader.getNameOfTrader()) != null) {
            return saveCommentOfExistedTrader(commentForNewTrader);
        }

        Trader newTrader = new Trader();
        newTrader.setApproved(commentForNewTrader.isApproved());
        newTrader.setCreatedAt(commentForNewTrader.getDateOfCreation());
        newTrader.setNameOfTrader(commentForNewTrader.getNameOfTrader());
        traderRepo.save(newTrader);

        Comment comment = saveCommentAndNewTrader(commentForNewTrader);
        comment.setTrader(newTrader);

        return commentRepo.save(comment);
    }

    public Comment saveCommentOfExistedTrader(CommentForNewTraderDTO commentForNewTrader) {
        Trader traderFromDB = traderRepo.findTraderByNameOfTrader(commentForNewTrader.getNameOfTrader());
        Comment comment = new Comment();
        comment.setTrader(traderFromDB);
        comment.setId(commentForNewTrader.getId());
        comment.setRating(commentForNewTrader.getRating());
        comment.setText(commentForNewTrader.getText());
        comment.setDateOfCreation(commentForNewTrader.getDateOfCreation());
        comment.setApproved(commentForNewTrader.isApproved());

        commentRepo.save(comment);

        return comment;
    }

    public Comment saveCommentAndNewTrader(CommentForNewTraderDTO commentForNewTrader) {
        Comment comment = new Comment();
        comment.setId(commentForNewTrader.getId());
        comment.setRating(commentForNewTrader.getRating());
        comment.setText(commentForNewTrader.getText());
        comment.setDateOfCreation(commentForNewTrader.getDateOfCreation());
        comment.setApproved(commentForNewTrader.isApproved());

        return comment;
    }


}
