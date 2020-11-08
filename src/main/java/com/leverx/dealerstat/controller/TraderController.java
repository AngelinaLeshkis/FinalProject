package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.serviceimpl.TraderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/traders")
public class TraderController {

    private TraderServiceImpl traderService;

    @Autowired
    public TraderController(TraderServiceImpl traderService) {
        this.traderService = traderService;
    }

    @PostMapping(value = "/addTrader")
    public Trader saveTrader(@RequestBody Trader trader) {
        traderService.saveTrader(trader);
        return trader;
    }

    @GetMapping(value = "")
    public Iterable<Trader> getTraders() {
        return traderService.getTraders();
    }

    @GetMapping(value = "/trader/{id}")
    public ResponseEntity<Trader> getUserById(@PathVariable long id) {
        Optional<Trader> trader = traderService.getTraderById(id);
        return trader.isPresent() ? ResponseEntity.ok(trader.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/deleteTrader/{id}")
    public void deleteTrader(@PathVariable Long id) {
        traderService.deleteTrader(id);
    }
}
