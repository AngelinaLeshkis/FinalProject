package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.persistence.CommentRepository;
import com.leverx.dealerstat.persistence.TraderRepository;
import com.leverx.dealerstat.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Trader savedTrader = traderRepo.findById(id).orElse(null);
        savedTrader.setApproved(true);
        traderRepo.save(savedTrader);

        return savedTrader;
    }

    @Override
    public Trader declineTrader(Long id) {
        Trader savedTrader = traderRepo.findById(id).orElse(null);
        savedTrader.setApproved(false);
        traderRepo.save(savedTrader);

        return savedTrader;
    }

//    public float getTraderRating(Long id) {
//        Trader savedTrader = traderRepo.findById(id).orElse(null);
//        List<Comment> comments = commentRepo.findCommentsByTraderId(id);
//        long countOfComments = 0;
//        long sumOfRating = 0;
//
//        if (savedTrader.isApproved() ) {
//                comments = comments.stream()
//                        .filter(comment -> comment.isApproved())
//                        .collect(Collectors.toList());
//        }
//
//    }
}
