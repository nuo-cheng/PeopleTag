package com.example.getstarted.daos;

import com.example.getstarted.objects.Assoc;
import com.example.getstarted.objects.PostCollectionAssoc;
import com.example.getstarted.objects.PostPersonAssoc;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PCAssocDaoImplement implements PCAssocDao{
    private DatastoreService datastore;
    private static final String PCASSOC_KIND="PCASSOC";

    public PCAssocDaoImplement(){
        datastore= DatastoreServiceFactory.getDatastoreService();
    }

    public PostCollectionAssoc entityToPCAssoc(Entity entity){
        return new PostCollectionAssoc(entity.getKey().getId(),(Long)entity.getProperty(PostCollectionAssoc.COLLECTION_ID)
                ,(Long)entity.getProperty(PostPersonAssoc.POST_ID));
    }

    public List<PostCollectionAssoc> entitiesToPCAssocs(Iterator<Entity> results){
        List<PostCollectionAssoc> resultAssocs=new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultAssocs.add(entityToPCAssoc(results.next()));      // Add the Person to the List
        }
        return resultAssocs;
    }


    @Override
    public Long createPCAssoc(Long collectionId, Long postId) throws SQLException {
        Entity pCAssocEntity=new Entity(PCASSOC_KIND);
        pCAssocEntity.setProperty(PostCollectionAssoc.COLLECTION_ID,collectionId);
        pCAssocEntity.setProperty(PostCollectionAssoc.POST_ID,postId);
        Key assocKey=datastore.put(pCAssocEntity);
        return assocKey.getId();
    }

    @Override
    public void deletePCAssoc(Long pcAssocId) throws SQLException {
        Key key= KeyFactory.createKey(PCASSOC_KIND,pcAssocId);
        datastore.delete(key);
    }

    @Override
    public Result<Long> readCollections(Long postId, String startCursorString) throws SQLException {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(PCASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        PostCollectionAssoc.POST_ID, Query.FilterOperator.EQUAL,postId))
                .addSort(PostCollectionAssoc.COLLECTION_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<PostCollectionAssoc> resultAssocs = entitiesToPCAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<PostCollectionAssoc> resultAssoc;
        if (cursor != null && resultAssocs.size() == 9) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> collectionIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            collectionIds.add(resultAssoc.result.get(i).getCollectionId());
        }
//        Result<Long> personIdAndCursor=new Result<>(personIds,cursor.toWebSafeString());
        if (cursor != null && collectionIds.size() == 9) {         // Are we paging? Save Cursor
            return new Result<>(collectionIds, cursor.toWebSafeString());
        } else {
            return new Result<>(collectionIds);
        }
    }

    @Override
    public Result<Long> readPosts(Long collectionId, String startCursorString) throws SQLException {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(PCASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        PostCollectionAssoc.COLLECTION_ID, Query.FilterOperator.EQUAL,collectionId))
                .addSort(PostCollectionAssoc.POST_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<PostCollectionAssoc> resultAssocs = entitiesToPCAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<PostCollectionAssoc> resultAssoc;
        if (cursor != null && resultAssocs.size() == 9) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> postIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            postIds.add(resultAssoc.result.get(i).getCollectionId());
        }
//        Result<Long> personIdAndCursor=new Result<>(personIds,cursor.toWebSafeString());
        if (cursor != null && postIds.size() == 9) {         // Are we paging? Save Cursor
            return new Result<>(postIds, cursor.toWebSafeString());
        } else {
            return new Result<>(postIds);
        }
    }

    @Override
    public boolean isAlreadyIn(Long postId, Long collectionId) {
        Query.CompositeFilter advancedFilter = new Query.CompositeFilter(
                Query.CompositeFilterOperator.AND, Arrays.<Query.Filter>asList(
                new Query.FilterPredicate(PostCollectionAssoc.COLLECTION_ID, Query.FilterOperator.EQUAL, collectionId),
                new Query.FilterPredicate(PostCollectionAssoc.POST_ID, Query.FilterOperator.EQUAL, postId)));
        Query query=new Query(PCASSOC_KIND).setFilter(advancedFilter);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results=preparedQuery.asQueryResultIterator();
        if (results.hasNext()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long getAssocId(Long collectionId, Long postId) {
        Query.CompositeFilter advancedFilter = new Query.CompositeFilter(
                Query.CompositeFilterOperator.AND, Arrays.<Query.Filter>asList(
                new Query.FilterPredicate(PostCollectionAssoc.COLLECTION_ID, Query.FilterOperator.EQUAL, collectionId),
                new Query.FilterPredicate(PostCollectionAssoc.POST_ID, Query.FilterOperator.EQUAL, postId)));
        Query query = new Query(PCASSOC_KIND).setFilter(advancedFilter);
        //System.out.println(query);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results=preparedQuery.asQueryResultIterator();
        assert(results.hasNext());
        Entity entity=results.next();
        assert(null != entity);
        System.out.println(entity.getKey().getId());
//        return (Long)entity.getProperty(Assoc.ID);
        return entity.getKey().getId();
    }
}
