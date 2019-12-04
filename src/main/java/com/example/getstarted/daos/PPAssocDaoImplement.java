package com.example.getstarted.daos;

import com.example.getstarted.objects.PostPersonAssoc;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PPAssocDaoImplement implements PPAssocDao{
    private DatastoreService datastore;
    private static final String PPASSOC_KIND="ppAssoc";

    public PPAssocDaoImplement(){
        datastore= DatastoreServiceFactory.getDatastoreService();
    }

    public PostPersonAssoc entityToPPAssoc(Entity entity){
        return new PostPersonAssoc(entity.getKey().getId(),(Long)entity.getProperty(PostPersonAssoc.PERSON_ID)
                ,(Long)entity.getProperty(PostPersonAssoc.POST_ID));
    }

    public List<PostPersonAssoc> entitiesToPPAssocs(Iterator<Entity> results){
        List<PostPersonAssoc> resultAssocs=new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultAssocs.add(entityToPPAssoc(results.next()));      // Add the Person to the List
        }
        return resultAssocs;
    }

    @Override
    public Long createPPAssoc(Long personId, Long postId) throws SQLException {
        Entity pPAssocEntity=new Entity(PPASSOC_KIND);
        pPAssocEntity.setProperty(PostPersonAssoc.PERSON_ID,personId);
        pPAssocEntity.setProperty(PostPersonAssoc.POST_ID,postId);
        Key assocKey=datastore.put(pPAssocEntity);
        return assocKey.getId();
    }

    @Override
    public void deletePPAssoc(Long ppAssocId) throws SQLException {
        Key key= KeyFactory.createKey(PPASSOC_KIND,ppAssocId);
        datastore.delete(key);
    }

    @Override
    public Result<Long> readPersons(Long postId, String startCursorString) throws SQLException {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(PPASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        PostPersonAssoc.POST_ID, Query.FilterOperator.EQUAL,postId))
                .addSort(PostPersonAssoc.PERSON_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<PostPersonAssoc> resultAssocs = entitiesToPPAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<PostPersonAssoc> resultAssoc;
        if (cursor != null && resultAssocs.size() == 9) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> personIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            personIds.add(resultAssoc.result.get(i).getPersonId());
        }
//        Result<Long> personIdAndCursor=new Result<>(personIds,cursor.toWebSafeString());
        if (cursor != null && personIds.size() == 9) {         // Are we paging? Save Cursor
            return new Result<>(personIds, cursor.toWebSafeString());
        } else {
            return new Result<>(personIds);
        }
    }

    @Override
    public Result<Long> readPosts(Long personId, String startCursorString) throws SQLException {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(PPASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        PostPersonAssoc.PERSON_ID, Query.FilterOperator.EQUAL,personId))
                .addSort(PostPersonAssoc.POST_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<PostPersonAssoc> resultAssocs = entitiesToPPAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<PostPersonAssoc> resultAssoc;
        if (cursor != null && resultAssocs.size() == 9) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> postIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            postIds.add(resultAssoc.result.get(i).getPostId());
        }
//        Result<Long> personIdAndCursor=new Result<>(personIds,cursor.toWebSafeString());
        if (cursor != null && postIds.size() == 9) {         // Are we paging? Save Cursor
            return new Result<>(postIds, cursor.toWebSafeString());
        } else {
            return new Result<>(postIds);
        }
    }

    @Override
    public boolean isAlreadyIn(Long postId, Long personId) {
        Query.CompositeFilter advancedFilter = new Query.CompositeFilter(
                Query.CompositeFilterOperator.AND, Arrays.<Query.Filter>asList(
                new Query.FilterPredicate(PostPersonAssoc.PERSON_ID, Query.FilterOperator.EQUAL, personId),
                new Query.FilterPredicate(PostPersonAssoc.POST_ID, Query.FilterOperator.EQUAL, postId)));
        Query query=new Query(PPASSOC_KIND).setFilter(advancedFilter);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results=preparedQuery.asQueryResultIterator();
        if (results.hasNext()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long getAssocId(Long personId, Long postId) {
        Query.CompositeFilter advancedFilter = new Query.CompositeFilter(
                Query.CompositeFilterOperator.AND, Arrays.<Query.Filter>asList(
                new Query.FilterPredicate(PostPersonAssoc.PERSON_ID, Query.FilterOperator.EQUAL, personId),
                new Query.FilterPredicate(PostPersonAssoc.POST_ID, Query.FilterOperator.EQUAL, postId)));
        Query query = new Query(PPASSOC_KIND).setFilter(advancedFilter);
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

    @Override
    public List<Long> getPPAssocIdsFromPostId(Long postId) {
        Query query=new Query(PPASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        PostPersonAssoc.POST_ID, Query.FilterOperator.EQUAL,postId))
                .addSort(PostPersonAssoc.PERSON_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();
        List<PostPersonAssoc> resultAssocs = entitiesToPPAssocs(results);
        List<Long> ppAssocIds=new ArrayList<>();
        for(int i=0;i<resultAssocs.size();i++){
            ppAssocIds.add(resultAssocs.get(i).getId());
        }
        return ppAssocIds;
    }

    @Override
    public List<Long> getPPAssocIdsFromPersonId(Long personId) {
        Query query=new Query(PPASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        PostPersonAssoc.PERSON_ID, Query.FilterOperator.EQUAL,personId))
                .addSort(PostPersonAssoc.POST_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();
        List<PostPersonAssoc> resultAssocs = entitiesToPPAssocs(results);
        List<Long> ppAssocIds=new ArrayList<>();
        for(int i=0;i<resultAssocs.size();i++){
            ppAssocIds.add(resultAssocs.get(i).getId());
        }
        return ppAssocIds;
    }
}
