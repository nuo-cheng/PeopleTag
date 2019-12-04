package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;
import java.util.List;

/**
 * post collection association dao
 */
public interface PCAssocDao {
    /**
     * create pcassoc
     * @param collectionId
     * @param postId
     * @return
     * @throws SQLException
     */
    Long createPCAssoc(Long collectionId, Long postId) throws SQLException;

    /**
     * delete pc assoc
     * @param pcAssocId
     * @throws SQLException
     */
    void deletePCAssoc(Long pcAssocId) throws SQLException;

    /**
     * read collections
     * @param postId
     * @param startCursorString
     * @return
     * @throws SQLException
     */
    Result<Long> readCollections(Long postId, String startCursorString) throws SQLException;

    /**
     * read posts
     * @param collectionId
     * @param startCursorString
     * @return
     * @throws SQLException
     */
    Result<Long> readPosts(Long collectionId, String startCursorString) throws SQLException;

    /**
     * is already stored
     * @param postId
     * @param collectionId
     * @return
     */
    boolean isAlreadyIn(Long postId, Long collectionId);

    /**
     * get association Id by collection id and post id
     * @param collectionId
     * @param postId
     * @return
     */
    Long getAssocId(Long collectionId, Long postId);

    /**
     * get pc assoc ids from post id
     * @param postId
     * @return
     */
    List<Long> getPCAssocIdsFromPostId(Long postId);

    /**
     * get pc assoc ids from collection id
     * @param collectionId
     * @return
     */
    List<Long> getPCAssocIdsFromCollectionId(Long collectionId);
}
