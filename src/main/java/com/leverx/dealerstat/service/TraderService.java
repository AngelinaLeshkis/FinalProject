package com.leverx.dealerstat.service;

import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.entity.User;

import java.util.List;
import java.util.Optional;

public interface TraderService {
    Trader saveTrader(Trader trader);

    void deleteTrader(Long id);

    void updateTrader(Trader trader);

    Iterable<Trader> getTraders();

    Trader getTraderById(Long id);

    Trader approveTrader(Long id);

    Trader declineTrader(Long id);

    List<Comment> getApprovedCommentsOfTrader(Long traderId);

    void setTraderRating(Long traderId);

    List<Trader> getTopOfTraders();

    List<Trader> getApprovedTraders();
}
