package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.TraderDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;

import java.util.List;

public interface TraderService {
    Trader saveTrader(Trader trader);

    void deleteTrader(Long id);

    Iterable<Trader> getTraders();

    Trader getTraderById(Long id);

    Trader approveTrader(Long id);

    Trader declineTrader(Long id);

    List<Comment> getApprovedCommentsOfTrader(Long traderId);

    List<TraderDTO> getTopOfTraders();

    List<Trader> getApprovedTraders();
}
