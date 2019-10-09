package com.banzhuan.repairservice.service.impl;

import com.banzhuan.repairservice.dao.CommentDao;
import com.banzhuan.repairservice.entity.Comment;
import com.banzhuan.repairservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public Comment getComment(Integer repairId) {
        return commentDao.findByRepairId(repairId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }
}
