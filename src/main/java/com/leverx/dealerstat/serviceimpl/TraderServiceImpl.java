package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.persistence.TraderRepository;
import com.leverx.dealerstat.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraderServiceImpl implements TraderService {

    private TraderRepository traderRepo;

    @Autowired
    public TraderServiceImpl(TraderRepository traderRepo) {
        this.traderRepo = traderRepo;
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
    public Optional<Trader> getTraderById(Long id) {
        return traderRepo.findById(id);
    }
}
