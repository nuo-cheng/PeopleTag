package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public interface PCAssocDao {
    Long createPCAssocDao(Long collectionId, Long postId) throws SQLException;

    void deletePCAssoc(Long pcAssocId) throws SQLException;

    Result<Long> readCollections(Long postId, String startCursorString) throws SQLException;

    Result<Long> readPosts(Long collectionId, String startCursorString) throws SQLException;
}
