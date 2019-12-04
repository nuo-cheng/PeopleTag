package com.example.getstarted.daos;

import com.example.getstarted.objects.Comment;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.util.Date;

public class CommentDaoImplement implements CommentDao {
    private DatastoreService datastore;
    private static final String COMMENTKIND = "comment";

    public CommentDaoImplement() {
        datastore = DatastoreServiceFactory.getDatastoreService(); // Lastized Datastore service
    }
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

    @Override
    public Comment readComment(Long commentId) throws SQLException {
        try{
            Entity commentEntity=datastore.get(KeyFactory.createKey(COMMENTKIND,commentId));
            return entityToComment(commentEntity);
        }catch (EntityNotFoundException e){
            return null;
        }
    }

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
