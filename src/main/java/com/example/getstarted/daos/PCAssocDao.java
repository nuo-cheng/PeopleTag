package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public interface PCAssocDao {
    Long createPCAssocDao(Long collectionId, Long postId) throws SQLException;

    void deletePCAssoc(Long postId) throws SQLException;

    Result<Long> readCollections(Long poatId, String startCursorString) throws SQLException;

    Result<Long> readPosts(Long personId, String startCursorString) throws SQLException;
}
