package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.persistence.CommentRepository;
import com.leverx.dealerstat.persistence.TraderRepository;
import com.leverx.dealerstat.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraderServiceImpl implements TraderService {

    private TraderRepository traderRepo;
    private CommentRepository commentRepo;

    @Autowired
    public TraderServiceImpl(TraderRepository traderRepo, CommentRepository commentRepo) {
        this.traderRepo = traderRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public Trader saveTrader(Trader trader) {
        return traderRepo.save(trader);
    }

    @Override
    public void deleteTrader(Long id) {
        traderRepo.deleteById(id);
    }

    @Override
    public void updateTrader(Trader trader) {
        traderRepo.save(trader);
    }

    @Override
    public Iterable<Trader> getTraders() {
        return traderRepo.findAll();
    }

    @Override
    public Trader getTraderById(Long id) {
        return traderRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Trader not found with id = " + id));
    }

    @Override
    public Trader approveTrader(Long id) {
        Trader savedTrader = traderRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Trader not found with id = " + id));
        savedTrader.setApproved(true);

        return traderRepo.save(savedTrader);
    }

    @Override
    public Trader declineTrader(Long id) {
        Trader savedTrader = traderRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Trader not found with id = " + id));
        savedTrader.setApproved(false);

        return traderRepo.save(savedTrader);
    }

    @Override
    public void setTraderRating(Long traderId) {
        Trader savedTrader = traderRepo.findById(traderId).orElseThrow(() ->
                new RuntimeException("Trader not found with id = " + traderId));
        List<Comment> comments;
        float sumOfTraderRating = 0;

        if (savedTrader.isApproved()) {
            comments = getApprovedCommentsOfTrader(traderId);

            for (Comment comment : comments) {
                sumOfTraderRating += comment.getRating();
            }

            savedTrader.setRatingOfTrader(sumOfTraderRating / comments.size());
            traderRepo.save(savedTrader);
        }
    }

    @Override
    public List<Comment> getApprovedCommentsOfTrader(Long traderId) {
        List<Comment> commentsOfTrader = commentRepo.findCommentsByTraderId(traderId);
        return commentsOfTrader.stream().filter(Comment::isApproved).collect(Collectors.toList());
    }

    @Override
    public List<Trader> getTopOfTraders() {
        List<Trader> traders = getApprovedTraders();

        return traders.stream()
                .sorted(Comparator.comparing(Trader::getRatingOfTrader).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Trader> getApprovedTraders() {
        List<Trader> traders = traderRepo.findAll();
        return traders.stream().filter(Trader::isApproved).collect(Collectors.toList());
    }
}
