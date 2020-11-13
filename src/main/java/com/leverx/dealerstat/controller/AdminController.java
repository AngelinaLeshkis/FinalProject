package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.service.CommentService;
import com.leverx.dealerstat.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private TraderService traderService;
    private CommentService commentService;

    @Autowired
    public AdminController(TraderService traderService, CommentService commentService) {
        this.traderService = traderService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/traders/approveTrader/{id}")
    public ResponseEntity<Trader> approveTrader(@PathVariable(value = "id") Long id) {
        Trader savedTrader = traderService.getTraderById(id);

        if (savedTrader == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        traderService.approveTrader(id);

        return  new ResponseEntity<>(savedTrader, HttpStatus.OK);
    }

    @GetMapping(value = "/traders/declineTrader/{id}")
    public ResponseEntity<Trader> declineTrader(@PathVariable(value = "id") Long id) {
        Trader savedTrader = traderService.getTraderById(id);

        if (savedTrader == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        traderService.declineTrader(id);

        return  new ResponseEntity<>(savedTrader, HttpStatus.OK);
    }

    @GetMapping(value = "/comments/approveComment/{id}")
    public ResponseEntity<CreateCommentDTO> approveComment(@PathVariable(value = "id") Long id) {
        CreateCommentDTO savedComment = commentService.getCommentByCommentId(id);

        if (savedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        commentService.approveComment(id);

        return  new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping(value = "/comments/declineComment/{id}")
    public ResponseEntity<CreateCommentDTO> declineComment(@PathVariable(value = "id") Long id) {
        CreateCommentDTO savedComment = commentService.getCommentByCommentId(id);

        if (savedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        commentService.declineComment(id);

        return  new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping(value = "/trader/comments/{id}")
    public List<Comment> getApprovedComments(@PathVariable(value = "id") Long id) {
        Trader trader = traderService.getTraderById(id);
        List<Comment> comments = traderService.getApprovedCommentsOfTrader(id);

        if(trader.isApproved()) {
            return comments;
        }

        return null;
    }

    @GetMapping(value = "/topOfTraders")
    public List<Trader> getTopOfTraders() {
        return traderService.getTopOfTraders();
    }

    @GetMapping(value = "/traders/approvedTraders")
    public List<Trader> getApprovedTraders() {
        return traderService.getApprovedTraders();
    }
    
//    @GetMapping(value = "/trader/rating/{id}")
//    public Float getTraderRating(@PathVariable(value = "id") Long id) {
//        return traderService.getTraderRating(id);
//    }


}
