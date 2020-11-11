package com.leverx.dealerstat.service;

import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.entity.User;

import java.util.Optional;

public interface TraderService {
    Trader saveTrader(Trader trader);

    void deleteTrader(Long id);

    void updateTrader(Trader trader);

    Iterable<Trader> getTraders();

    Trader getTraderById(Long id);

    Trader approveTrader(Long id);

    Trader declineTrader(Long id);
}
