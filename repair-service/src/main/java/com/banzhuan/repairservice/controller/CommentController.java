package com.banzhuan.repairservice.controller;

import com.banzhuan.repairservice.dto.Result;
import com.banzhuan.repairservice.entity.Comment;
import com.banzhuan.repairservice.service.CommentService;
import com.banzhuan.repairservice.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repair-service")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public Result<Comment> saveComment(@RequestBody String comment){
        Comment comment1 = JacksonUtil.json2pojo(comment,Comment.class);
        return Result.success(commentService.save(comment1));
    }

    @GetMapping
    public Result<Comment> getComment(@RequestParam Integer repairId){
        return Result.success(commentService.getComment(repairId));
    }
}
