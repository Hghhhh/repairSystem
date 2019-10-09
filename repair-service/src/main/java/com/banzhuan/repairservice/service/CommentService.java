package com.banzhuan.repairservice.service;

import com.banzhuan.repairservice.entity.Comment;

public interface CommentService {

    Comment getComment(Integer repairId);

    Comment save(Comment comment);
}
