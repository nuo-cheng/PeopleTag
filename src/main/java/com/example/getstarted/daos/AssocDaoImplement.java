package com.example.getstarted.daos;

import com.example.getstarted.objects.Assoc;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssocDaoImplement implements AssocDao{
    private DatastoreService datastore;
    private static final String ASSOC_KIND="ASSOC";

    public Assoc entityToAssoc(Entity entity){
        return new Assoc(entity.getKey().getId(),(Long)entity.getProperty(Assoc.PERSON_ID)
        ,(Long)entity.getProperty(Assoc.COLLECTION_ID));
    }

    public AssocDaoImplement(){
        datastore= DatastoreServiceFactory.getDatastoreService();
    }


    @Override
    public Long createAssoc(Long personId, Long collectionId) throws SQLException {
        Entity assocEntity=new Entity(ASSOC_KIND);
        assocEntity.setProperty(Assoc.COLLECTION_ID,collectionId);
        assocEntity.setProperty(Assoc.PERSON_ID,personId);
        Key assocKey=datastore.put(assocEntity);
        return assocKey.getId();
    }

    @Override
    public void deleteAssoc(Long assocId) throws SQLException {
        Key key= KeyFactory.createKey(ASSOC_KIND,assocId);
        datastore.delete(key);
    }

    public List<Assoc> entitiesToAssocs(Iterator<Entity> results){
        List<Assoc> resultAssocs=new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultAssocs.add(entityToAssoc(results.next()));      // Add the Person to the List
        }
        return resultAssocs;
    }

    @Override
    public List<Long> readCollections(Long personId, String startCursorString) {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10); // Only show 10 at a time
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
        if (cursor != null && resultAssocs.size() == 10) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> collectionIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            collectionIds.add(resultAssoc.result.get(i).getCollectionId());
        }
        return collectionIds;
    }

    @Override
    public List<Long> readPersons(Long collectionId, String startCursorString) {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10); // Only show 10 at a time
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
        if (cursor != null && resultAssocs.size() == 10) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            resultAssoc= new Result<>(resultAssocs, cursorString);
        } else {
            resultAssoc=new  Result<>(resultAssocs);
        }
        List<Long> personIds=new ArrayList<>();
        for(int i=0;i<resultAssoc.result.size();i++){
            personIds.add(resultAssoc.result.get(i).getPersonId());
        }
        return personIds;
    }
}
