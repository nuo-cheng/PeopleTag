package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public interface PPAssocDao {
    Long createPPAssoc(Long personId, Long postId) throws SQLException;

    void deletePPAssoc(Long postId) throws SQLException;

    Result<Long> readPersons(Long postId, String startCursorString) throws SQLException;

    Result<Long> readPosts(Long personId, String startCursorString) throws SQLException;
}
