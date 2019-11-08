package com.example.getstarted.daos;

import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public class CollectionDaoImplement implements CollectionDao{
    @Override
    public Long createCollection(Collection collection) throws SQLException {
        return null;
    }

    @Override
    public Collection readCollection(Long CollectionId) throws SQLException {
        return null;
    }

    @Override
    public void updateCollection(Collection collection) throws SQLException {

    }

    @Override
    public void deleteCollection(Long collectionId) throws SQLException {

    }

    @Override
    public Result<Collection> listCollections(String startCursor) throws SQLException {
        return null;
    }

    @Override
    public Result<Collection> listCollecitonsByUser(String collectionId, String startCursor) throws SQLException {
        return null;
    }
}
