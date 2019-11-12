package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;
import java.util.List;

/**
 * methods of communicate Association object with google datastore
 * CRUD
 * concrete in AssocDaoImplement
 */
public interface AssocDao {
    /**
     * Add
     * @param personId
     * @param collectionId
     * @return
     */
    Long createAssoc(Long personId, Long collectionId) throws SQLException;

    /**
     * Delete
     * @param assocId
     * @throws SQLException
     */
    void deleteAssoc(Long assocId) throws SQLException;

    /**
     * Read list of collection ids according to personId
     * @param personId
     * @return
     */
    Result<Long> readCollections(Long personId, String startCursorString);

    /**
     * Read list of person ids according to collecionId;
     * @param collectionId
     * @return
     */
    Result<Long> readPersons(Long collectionId, String startCursorString);


    /**
     * get the associate id by personId and collectionId
     * @param personId
     * @param collectionId
     * @return
     */
     Long getAssocId(Long personId, Long collectionId);

    /**
     * check is a person already in a collection when adding
     * @param personId
     * @param collectionId
     * @return
     */
    boolean isAlreadyIn(Long personId, Long collectionId);

    /**
     * get AssocId from collectionId
     * @param collectionId
     * @return
     */
    List<Long> getAssocIdsFromCollectionId(Long collectionId);

    /**
     * get AssocId from personId
     * @param personId
     * @return
     */
    List<Long> getAssocIdsFromPersonId(Long personId);

}
