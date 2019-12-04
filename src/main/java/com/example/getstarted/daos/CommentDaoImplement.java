package com.example.getstarted.daos;

import com.example.getstarted.objects.Comment;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.util.Date;

/**
 * implement comment dao
 */
public class CommentDaoImplement implements CommentDao {
    private DatastoreService datastore;
    private static final String COMMENTKIND = "comment";

    /**
     * constructor
     */
    public CommentDaoImplement() {
        datastore = DatastoreServiceFactory.getDatastoreService(); // Lastized Datastore service
    }

    /**
     * create comment
     * @param comment
     * @return
     * @throws SQLException
     */
    @Override
    public Long createComment(Comment comment) throws SQLException {
        Entity incPostEntity = new Entity(COMMENTKIND);
        incPostEntity.setProperty(Comment.CONTENT,comment.getContent());
        incPostEntity.setProperty(Comment.CREATEDBY,comment.getCreatedBy());
        incPostEntity.setProperty(Comment.CREATEDBYID,comment.getCreatedById());
        incPostEntity.setProperty(Comment.TIMECREATED,comment.getTimeCreated());
        Key commentKey=datastore.put(incPostEntity);
        return  commentKey.getId();
    }

    /**
     * readComment
     * @param commentId
     * @return
     * @throws SQLException
     */
    @Override
    public Comment readComment(Long commentId) throws SQLException {
        try{
            Entity commentEntity=datastore.get(KeyFactory.createKey(COMMENTKIND,commentId));
            return entityToComment(commentEntity);
        }catch (EntityNotFoundException e){
            return null;
        }
    }

    /**
     * entity to comment
     * @param entity
     * @return
     */
    public Comment entityToComment(Entity entity){
        return new Comment.Builder()
                .content((String)entity.getProperty(Comment.CONTENT))
                .createdBy((String)entity.getProperty(Comment.CREATEDBY))
                .createdById((String)entity.getProperty(Comment.CREATEDBYID))
                .timeCreated((Date)entity.getProperty(Comment.TIMECREATED))
                .id(entity.getKey().getId())
                .build();
    }
}
