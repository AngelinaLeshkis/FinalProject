package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.TraderDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/traders")
public class TraderController {

    private TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @PostMapping(value = "/addTrader")
    public ResponseEntity<Trader> saveTrader(@Valid @RequestBody Trader trader) {
        Trader traderFromDB = traderService.saveTrader(trader);

        if (traderFromDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(traderFromDB, HttpStatus.OK);
    }

    @GetMapping(value = "/trader/{id}")
    public ResponseEntity<Trader> getTraderById(@PathVariable(name = "id") Long id) {
        Trader trader = traderService.getTraderById(id);

        if (trader == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(trader, HttpStatus.OK);

    }

    @GetMapping(value = "/topOfTraders")
    public List<TraderDTO> getTopOfTraders() {
        return traderService.getTopOfTraders();
    }

    @GetMapping(value = "/approvedTraders")
    public List<Trader> getApprovedTraders() {
        return traderService.getApprovedTraders();
    }

    @GetMapping(value = "/trader/comments/{id}")
    public ResponseEntity<List<Comment>> getApprovedComments(@PathVariable(value = "id") Long id) {
        Trader trader = traderService.getTraderById(id);

        if (trader.isApproved()) {
            return new ResponseEntity<>(traderService.getApprovedCommentsOfTrader(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
