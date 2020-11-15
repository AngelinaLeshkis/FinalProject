package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.service.CommentService;
import com.leverx.dealerstat.service.TraderService;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private TraderService traderService;
    private CommentService commentService;
    private UserService userService;

    @Autowired
    public AdminController(TraderService traderService, CommentService commentService, UserService userService) {
        this.traderService = traderService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping(value = "/traders/approveTrader/{id}")
    public ResponseEntity<Trader> approveTrader(@PathVariable(value = "id") Long id) {
        Trader savedTrader = traderService.getTraderById(id);

        if (savedTrader == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        traderService.approveTrader(id);

        return new ResponseEntity<>(savedTrader, HttpStatus.OK);
    }

    @GetMapping(value = "/traders/declineTrader/{id}")
    public ResponseEntity<Trader> declineTrader(@PathVariable(value = "id") Long id) {
        Trader savedTrader = traderService.getTraderById(id);

        if (savedTrader == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        traderService.declineTrader(id);

        return new ResponseEntity<>(savedTrader, HttpStatus.OK);
    }

    @GetMapping(value = "/comments/approveComment/{id}")
    public ResponseEntity<CreateCommentDTO> approveComment(@PathVariable(value = "id") Long id) {
        CreateCommentDTO savedComment = commentService.getCommentByCommentId(id);

        if (savedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        commentService.approveComment(id);

        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping(value = "/comments/declineComment/{id}")
    public ResponseEntity<CreateCommentDTO> declineComment(@PathVariable(value = "id") Long id) {
        CreateCommentDTO savedComment = commentService.getCommentByCommentId(id);

        if (savedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        commentService.declineComment(id);

        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/comments/{id}")
    public void deleteComment(@PathVariable(name = "id") Long id) {
        commentService.deleteCommentByCommentId(id);
    }

    @GetMapping("/comments")
    Iterable<CreateCommentDTO> getAllComments() {
        return commentService.getComments();
    }

    @GetMapping(value = "/allTraders")
    public Iterable<Trader> getTraders() {
        return traderService.getTraders();
    }

    @DeleteMapping(value = "/deleteTrader/{id}")
    public void deleteTrader(@PathVariable(name = "id") Long id) {
        traderService.deleteTrader(id);
    }

    @GetMapping(value = "/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }
}
