package com.example.getstarted.daos;

import com.example.getstarted.objects.Assoc;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * methods of communicate Association object with google datastore
 * CRUD
 */
public class AssocDaoImplement implements AssocDao{
    private DatastoreService datastore;
    private static final String ASSOC_KIND="ASSOC";

    /**
     * entity To Assoc
     * @param entity
     * @return
     */
    public Assoc entityToAssoc(Entity entity){
        return new Assoc(entity.getKey().getId(),(Long)entity.getProperty(Assoc.PERSON_ID)
        ,(Long)entity.getProperty(Assoc.COLLECTION_ID));
    }

    /**
     * prepare datastore
     */
    public AssocDaoImplement(){
        datastore= DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * Add
     * @param personId
     * @param collectionId
     * @return
     */
    @Override
    public Long createAssoc(Long personId, Long collectionId) throws SQLException {
        Entity assocEntity=new Entity(ASSOC_KIND);
        assocEntity.setProperty(Assoc.COLLECTION_ID,collectionId);
        assocEntity.setProperty(Assoc.PERSON_ID,personId);
        Key assocKey=datastore.put(assocEntity);
        return assocKey.getId();
    }

    /**
     * get the associate id by personId and collectionId
     * @param personId
     * @param collectionId
     * @return
     */
    public Long getAssocId(Long personId, Long collectionId){
        Query.CompositeFilter advancedFilter = new Query.CompositeFilter(
                Query.CompositeFilterOperator.AND, Arrays.<Query.Filter>asList(
                new Query.FilterPredicate(Assoc.PERSON_ID, Query.FilterOperator.EQUAL, personId),
                new Query.FilterPredicate(Assoc.COLLECTION_ID, Query.FilterOperator.EQUAL, collectionId)));
        Query query = new Query(ASSOC_KIND).setFilter(advancedFilter);
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

    /**
     * check is a person already in a collection when adding
     * @param personId
     * @param collectionId
     * @return
     */
    public boolean isAlreadyIn(Long personId, Long collectionId){
        Query.CompositeFilter advancedFilter = new Query.CompositeFilter(
                Query.CompositeFilterOperator.AND, Arrays.<Query.Filter>asList(
                new Query.FilterPredicate(Assoc.PERSON_ID, Query.FilterOperator.EQUAL, personId),
                new Query.FilterPredicate(Assoc.COLLECTION_ID, Query.FilterOperator.EQUAL, collectionId)));
        Query query = new Query(ASSOC_KIND).setFilter(advancedFilter);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results=preparedQuery.asQueryResultIterator();
        if (results.hasNext()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete
     * @param assocId
     * @throws SQLException
     */
    @Override
    public void deleteAssoc(Long assocId) throws SQLException {
        Key key= KeyFactory.createKey(ASSOC_KIND,assocId);
        datastore.delete(key);
    }

    /**
     * entities to Assocs
     * @param results
     * @return
     */
    public List<Assoc> entitiesToAssocs(Iterator<Entity> results){
        List<Assoc> resultAssocs=new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultAssocs.add(entityToAssoc(results.next()));      // Add the Person to the List
        }
        return resultAssocs;
    }

    /**
     * Read list of collection ids according to personId
     * @param personId
     * @return
     */
    @Override
    public Result<Long> readCollections(Long personId, String startCursorString) {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(ASSOC_KIND)
            .setFilter(new Query.FilterPredicate(
                    Assoc.PERSON_ID, Query.FilterOperator.EQUAL,personId))
                .addSort(Assoc.COLLECTION_ID, Query.SortDirection.ASCENDING);

        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<Assoc> resultAssocs = entitiesToAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<Assoc> resultAssoc;
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
        if (cursor != null && collectionIds.size() == 9) {         // Are we paging? Save Cursor
            return new Result<>(collectionIds, cursor.toWebSafeString());
        } else {
            return new Result<>(collectionIds);
        }
    }

    /**
     * Read list of person ids according to collecionId;
     * @param collectionId
     * @return
     */
    @Override
    public Result<Long> readPersons(Long collectionId, String startCursorString) {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query=new Query(ASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        Assoc.COLLECTION_ID, Query.FilterOperator.EQUAL,collectionId))
                .addSort(Assoc.PERSON_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
        List<Assoc> resultAssocs = entitiesToAssocs(results);
        Cursor cursor = results.getCursor();// Where to start next time
        Result<Assoc> resultAssoc;
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
//        return personIdAndCursor;
    }

    /**
     * get AssocId from collectionId
     * @param collectionId
     * @return
     */
    @Override
    public List<Long> getAssocIdsFromCollectionId(Long collectionId) {
        Query query=new Query(ASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        Assoc.COLLECTION_ID, Query.FilterOperator.EQUAL,collectionId))
                .addSort(Assoc.PERSON_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();
        List<Assoc> resultAssocs = entitiesToAssocs(results);
        List<Long> assocIds=new ArrayList<>();
        for(int i=0;i<resultAssocs.size();i++){
            assocIds.add(resultAssocs.get(i).getId());
        }
        return assocIds;
    }

    /**
     * get AssocId from personId
     * @param personId
     * @return
     */
    @Override
    public List<Long> getAssocIdsFromPersonId(Long personId) {
        Query query=new Query(ASSOC_KIND)
                .setFilter(new Query.FilterPredicate(
                        Assoc.PERSON_ID, Query.FilterOperator.EQUAL,personId))
                .addSort(Assoc.COLLECTION_ID, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery=datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator();
        List<Assoc> resultAssocs = entitiesToAssocs(results);
        List<Long> assocIds=new ArrayList<>();
        for(int i=0;i<resultAssocs.size();i++){
            assocIds.add(resultAssocs.get(i).getId());
        }
        return assocIds;
    }
}
