package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;
import java.util.List;

/**
 * post person association dao
 */
public interface PPAssocDao {
    /**
     * create pp association
     * @param personId
     * @param postId
     * @return
     * @throws SQLException
     */
    Long createPPAssoc(Long personId, Long postId) throws SQLException;

    /**
     * delete pp association
     * @param ppAssocId
     * @throws SQLException
     */
    void deletePPAssoc(Long ppAssocId) throws SQLException;

    /**
     * read persons by post id
     * @param postId
     * @param startCursorString
     * @return
     * @throws SQLException
     */
    Result<Long> readPersons(Long postId, String startCursorString) throws SQLException;

    /**
     * read posts by person id
     * @param personId
     * @param startCursorString
     * @return
     * @throws SQLException
     */
    Result<Long> readPosts(Long personId, String startCursorString) throws SQLException;

    /**
     * is already stored
     * @param postId
     * @param personId
     * @return
     */
    boolean isAlreadyIn(Long postId, Long personId);

    /**
     * get association id
     * @param personId
     * @param postId
     * @return
     */
    Long getAssocId(Long personId, Long postId);

    /**
     * get pp association ids from post id
     * @param postId
     * @return
     */
    List<Long> getPPAssocIdsFromPostId(Long postId);

    /**
     * get pp assoc ids form person ids
     * @param personId
     * @return
     */
    List<Long> getPPAssocIdsFromPersonId(Long personId);
}
