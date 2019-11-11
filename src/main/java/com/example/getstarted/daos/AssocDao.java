package com.example.getstarted.daos;

import java.sql.SQLException;
import java.util.List;

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
    List<Long> readCollections(Long personId, String startCursorString);

    /**
     * Read list of person ids according to collecionId;
     * @param collectionId
     * @return
     */
    List<Long> readPersons(Long collectionId, String startCursorString);


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

    List<Long> getAssocIdsFromCollectionId(Long collectionId);

    List<Long> getAssocIdsFromPersonId(Long personId);

}
