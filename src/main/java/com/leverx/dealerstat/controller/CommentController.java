package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.CreateCommentDTO;
import com.leverx.dealerstat.entity.Comment;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.serviceimpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class CommentController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/comment")
    public Comment saveComment(@RequestBody CreateCommentDTO commentDTO) {
        return commentService.saveComment(commentDTO);
    }

    @GetMapping("/comments")
    Iterable<CreateCommentDTO> getAllComments() {
        return commentService.getComments();
    }


}
