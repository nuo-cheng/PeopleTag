package com.example.getstarted.daos;

import com.example.getstarted.objects.Comment;

import java.sql.SQLException;

/**
 * comment dao interface
 */
public interface CommentDao {
    /**
     * create comment
     * @param comment
     * @return
     * @throws SQLException
     */
    Long createComment(Comment comment) throws SQLException;

    /**
     * read comment
     * @param commentId
     * @return
     * @throws SQLException
     */
    Comment readComment(Long commentId) throws SQLException;


}
