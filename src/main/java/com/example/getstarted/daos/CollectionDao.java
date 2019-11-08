package com.example.getstarted.daos;

import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public interface CollectionDao {
    Long createCollection(Collection collection) throws SQLException;

    Collection readCollection(Long CollectionId) throws SQLException;

    void updateCollection(Collection collection) throws SQLException;

    void deleteCollection(Long collectionId) throws SQLException;

    Result<Collection> listCollections(String startCursor) throws SQLException;

    Result<Collection> listCollecitonsByUser(String collectionId, String startCursor) throws SQLException;
}
