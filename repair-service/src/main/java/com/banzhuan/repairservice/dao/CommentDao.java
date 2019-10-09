package com.banzhuan.repairservice.dao;

import com.banzhuan.repairservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {

    Comment findByRepairId(Integer repairId);
}
