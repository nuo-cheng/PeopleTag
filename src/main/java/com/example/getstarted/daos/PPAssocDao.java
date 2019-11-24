package com.example.getstarted.daos;

import com.example.getstarted.objects.Result;

import java.sql.SQLException;
import java.util.List;

public interface PPAssocDao {
    Long createPPAssoc(Long personId, Long postId) throws SQLException;

    void deletePPAssoc(Long ppAssocId) throws SQLException;

    Result<Long> readPersons(Long postId, String startCursorString) throws SQLException;

    Result<Long> readPosts(Long personId, String startCursorString) throws SQLException;

    boolean isAlreadyIn(Long postId, Long personId);

    Long getAssocId(Long personId, Long postId);

    List<Long> getPPAssocIdsFromPostId(Long postId);

    List<Long> getPPAssocIdsFromPersonId(Long personId);
}
