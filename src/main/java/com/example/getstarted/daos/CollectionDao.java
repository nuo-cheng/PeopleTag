package com.example.getstarted.daos;

import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;

import java.sql.SQLException;

public interface CollectionDao {
    Long createPerson(Collection collection) throws SQLException;

    Person readPerson(Long CollectionId) throws SQLException;

    void updatePerson(Collection collection) throws SQLException;

    void deletePerson(Long collectionId) throws SQLException;

    Result<Collection> listCollections(String startCursor) throws SQLException;

    Result<Collection> listCollecitonsByUser(String collectionId, String startCursor) throws SQLException;
}
