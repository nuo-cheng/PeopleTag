package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;

/**
 * comment post association dao
 */
public interface ComPostAssocDao {
    /**
     * create comment post association
     * @param commentId
     * @param postId
     * @return
     * @throws SQLException
     */
    Long createComPostAssoc(Long commentId, Long postId) throws SQLException;

    /**
     * read comments
     * @param postId
     * @param startCursorString
     * @return
     * @throws SQLException
     */
    Result<Long> readComments(Long postId, String startCursorString) throws SQLException;


}
