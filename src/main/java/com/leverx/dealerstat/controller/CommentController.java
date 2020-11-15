package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.CommentForNewTraderDTO;
import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/anonymous")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody CreateCommentDTO commentDTO) {
        Comment savedComment = commentService.saveComment(commentDTO);

        if (savedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<Iterable<Comment>> getCommentsByTraderId(@PathVariable(name = "id") Long id) {
        Iterable<Comment> comments = commentService.getCommentsByTraderId(id);

        if (comments == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping(value = "/commentWithTrader")
    public ResponseEntity<Comment> saveCommentForNewTrader(@Valid @RequestBody
                                                                   CommentForNewTraderDTO commentForNewTraderDTO) {
        Comment savedComment = commentService.saveCommentWithAddingNewTrader(commentForNewTraderDTO);

        if (savedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping(value = "/comments/{id}")
    public ResponseEntity<CreateCommentDTO> getCommentById(@PathVariable(name = "id") Long id) {
        CreateCommentDTO commentDTO = commentService.getCommentByCommentId(id);

        if (commentDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

}
