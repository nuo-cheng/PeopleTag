package com.example.getstarted.daos;

import com.example.getstarted.objects.Comment;

import java.sql.SQLException;

public interface CommentDao {
    Long createComment(Comment comment) throws SQLException;

    Comment readComment(Long commentId) throws SQLException;


}
