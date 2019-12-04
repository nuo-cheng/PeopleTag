package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public interface ComPostAssocDao {
    Long createComPostAssoc(Long commentId, Long postId) throws SQLException;

    Result<Long> readComments(Long postId, String startCursorString) throws SQLException;


}
