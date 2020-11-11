package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/traders")
public class TraderController {

    private TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
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
    public ResponseEntity<Trader> getUserById(@PathVariable(name = "id") Long id) {
        Trader trader = traderService.getTraderById(id);

        if (trader == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(trader, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/deleteTrader/{id}")
    public void deleteTrader(@PathVariable(name = "id") Long id) {
        traderService.deleteTrader(id);
    }
}
