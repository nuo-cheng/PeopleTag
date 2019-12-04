package com.example.getstarted.daos;

import com.example.getstarted.objects.CommentPostAssoc;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * implement comment post association dao
 */
public class ComPostAssocDaoImplement implements ComPostAssocDao {
    private DatastoreService datastore;
    private static final String COMPOSTASSOCKIND="compostassoc";

    /**
     * constructor
     */
    public ComPostAssocDaoImplement(){
        datastore= DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * create comment post association
     * @param commentId
     * @param postId
     * @return
     * @throws SQLException
     */
    @Override
    public Long createComPostAssoc(Long commentId, Long postId) throws SQLException {
        Entity pCAssocEntity=new Entity(COMPOSTASSOCKIND);
        pCAssocEntity.setProperty(CommentPostAssoc.COMMENTID,commentId);
        pCAssocEntity.setProperty(CommentPostAssoc.POST_ID,postId);
        pCAssocEntity.setProperty(CommentPostAssoc.TIMECREATED,new Date());
        Key assocKey=datastore.put(pCAssocEntity);
        return assocKey.getId();
    }


    /**
     * read comments
     * @param postId
     * @param startCursorString
     * @return
     * @throws SQLException
     */
    @Override
    public Result<Long> readComments(Long postId, String startCursorString) throws SQLException {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(COMPOSTASSOCKIND)
                .setFilter(new Query.FilterPredicate(
                        CommentPostAssoc.POST_ID, Query.FilterOperator.EQUAL,postId))
                .addSort(CommentPostAssoc.TIMECREATED, Query.SortDirection.DESCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<CommentPostAssoc> resultAssocs = entitiesToComPostAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<CommentPostAssoc> resultAssoc;
        if (cursor != null && resultAssocs.size() == 9) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> commentIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            commentIds.add(resultAssoc.result.get(i).getCommentId());
        }
//        Result<Long> personIdAndCursor=new Result<>(personIds,cursor.toWebSafeString());
        if (cursor != null && commentIds.size() == 9) {         // Are we paging? Save Cursor
            return new Result<>(commentIds, cursor.toWebSafeString());
        } else {
            return new Result<>(commentIds);
        }
    }


    /**
     * entity to comment post association
     * @param entity
     * @return
     */
    public CommentPostAssoc entityToComPostAssoc(Entity entity){
        return new CommentPostAssoc(entity.getKey().getId(),(Long)entity.getProperty(CommentPostAssoc.COMMENTID)
                ,(Long)entity.getProperty(CommentPostAssoc.POST_ID));
    }

    /**
     * entities to comment post associations
     * @param results
     * @return
     */
    public List<CommentPostAssoc> entitiesToComPostAssocs(Iterator<Entity> results){
        List<CommentPostAssoc> resultAssocs=new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultAssocs.add(entityToComPostAssoc(results.next()));      // Add the Person to the List
        }
        return resultAssocs;
    }
}
