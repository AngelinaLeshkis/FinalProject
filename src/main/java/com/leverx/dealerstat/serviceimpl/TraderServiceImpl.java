package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.dto.TraderDTO;
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
        Trader traderFromDB = traderRepo.findTraderByNameOfTrader(trader.getNameOfTrader());

        if (traderFromDB != null) {
            return null;
        }

        return traderRepo.save(trader);
    }

    @Override
    public void deleteTrader(Long id) {
        traderRepo.deleteById(id);
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
    public List<Comment> getApprovedCommentsOfTrader(Long traderId) {
        List<Comment> commentsOfTrader = commentRepo.findCommentsByTraderId(traderId);
        return commentsOfTrader.stream().filter(Comment::isApproved).collect(Collectors.toList());
    }

    @Override
    public List<TraderDTO> getTopOfTraders() {
        List<Object[]> tradersFromDB = commentRepo.findAllTradersByRating();
        return tradersFromDB.stream()
                .map(TraderDTO::new)
                .collect(Collectors.toList());

    }

    @Override
    public List<Trader> getApprovedTraders() {
        List<Trader> traders = traderRepo.findAll();
        return traders.stream().filter(Trader::isApproved).collect(Collectors.toList());
    }
}
