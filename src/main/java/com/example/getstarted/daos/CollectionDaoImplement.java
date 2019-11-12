
/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.daos;

import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// [START example]
/**
 * methods of communicate Collection object with google datastore
 * CRUD
 *
 */
public class CollectionDaoImplement implements CollectionDao {

  // [START constructor]
  private DatastoreService datastore;
  private static final String COLLECTION_KIND = "MyCollection";

  /**
   * prepare datastore
   */
  public CollectionDaoImplement() {
    datastore = DatastoreServiceFactory.getDatastoreService(); // Lastized Datastore service
  }
  // [END constructor]

  /**
   * entity to collection
   * @param entity
   * @return
   */
  public Collection entityToCollection(Entity entity) {
    return new Collection.Builder()                                     // Convert to Person form
        .collectionName((String) entity.getProperty(Collection.COLLECTION_NAME))
        .id(entity.getKey().getId())
        .description((String) entity.getProperty(Collection.DESCRIPTION))
        .createdBy((String) entity.getProperty(Collection.CREATED_BY))
        .createdById((String) entity.getProperty(Collection.CREATED_BY_ID))
        .build();
  }

  /**
   * add collection
   * @param collection
   * @return
   * @throws SQLException
   */
  @Override
  public Long createCollection(Collection collection) {
    Entity incCollectionEntity = new Entity(COLLECTION_KIND);  // Key will be assigned once written
    incCollectionEntity.setProperty(Collection.COLLECTION_NAME, collection.getCollectionName());
    incCollectionEntity.setProperty(Collection.DESCRIPTION, collection.getDescription());
    incCollectionEntity.setProperty(Collection.CREATED_BY, collection.getCreatedBy());
    incCollectionEntity.setProperty(Collection.CREATED_BY_ID, collection.getCreatedById());

    Key collectionKey = datastore.put(incCollectionEntity); // Save the Entity
    return collectionKey.getId();                     // The ID of the Key
  }

  /**
   * read collection
   * @param collectionId
   * @return
   * @throws SQLException
   */
  @Override
  public Collection readCollection(Long collectionId) {
    try {
      Entity collectionEntity = datastore.get(KeyFactory.createKey(COLLECTION_KIND, collectionId));
      return entityToCollection(collectionEntity);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  /**
   * update collection
   * @param collection
   * @throws SQLException
   */
  @Override
  public void updateCollection(Collection collection) {
    Key key = KeyFactory.createKey(COLLECTION_KIND, collection.getId());  // From a person, create a Key
    Entity entity = new Entity(key);         // Convert Person to an Entity
    entity.setProperty(Collection.COLLECTION_NAME, collection.getCollectionName());
    entity.setProperty(Collection.DESCRIPTION, collection.getDescription());
    entity.setProperty(Collection.CREATED_BY, collection.getCreatedBy());
    entity.setProperty(Collection.CREATED_BY_ID, collection.getCreatedById());

    datastore.put(entity);                   // Update the Entity
  }

  /**
   * delete collection
   * @param collectionId
   * @throws SQLException
   */
  @Override
  public void deleteCollection(Long collectionId) {
    Key key = KeyFactory.createKey(COLLECTION_KIND, collectionId);        // Create the Key
    datastore.delete(key);                      // Delete the Entity
  }


  /**
   * entities to collections
   * @param results
   * @return
   */
  public List<Collection> entitiesToCollections(Iterator<Entity> results) {
    List<Collection> resultCollections = new ArrayList<>();
    while (results.hasNext()) {  // We still have data
      resultCollections.add(entityToCollection(results.next()));      // Add the Person to the List
    }
    return resultCollections;
  }

  /**
   * list all collections
   * @param startCursorString
   * @return
   * @throws SQLException
   */
  @Override
  public Result<Collection> listCollections(String startCursorString) {
    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10); // Only show 10 at a time
    if (startCursorString != null && !startCursorString.equals("")) {
      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
    }
    Query query = new Query(COLLECTION_KIND) // We only care about Persons
        .addSort(Collection.COLLECTION_NAME, SortDirection.ASCENDING); // Use default Index "first"
    PreparedQuery preparedQuery = datastore.prepare(query);
    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);

    List<Collection> resultCollections = entitiesToCollections(results);     // Retrieve and convert Entities
    Cursor cursor = results.getCursor();              // Where to start next time
    if (cursor != null && resultCollections.size() == 10) {         // Are we paging? Save Cursor
      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
      return new Result<>(resultCollections, cursorString);
    } else {
      return new Result<>(resultCollections);
    }
  }

  /**
   * list all collections created by a specific user
   * @param userId
   * @param startCursorString
   * @return
   * @throws SQLException
   */
  @Override
  public Result<Collection> listCollectionsByUser(String userId, String startCursorString) {
    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10); // Only show 10 at a time
    if (startCursorString != null && !startCursorString.equals("")) {
      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
    }
    Query query = new Query(COLLECTION_KIND) // We only care about Persons
        // Only for this user
        .setFilter(new Query.FilterPredicate(
            Collection.CREATED_BY_ID, Query.FilterOperator.EQUAL, userId))
        // a custom datastore index is required since you are filtering by one property
        // but ordering by another
        .addSort(Collection.COLLECTION_NAME, SortDirection.ASCENDING);
    PreparedQuery preparedQuery = datastore.prepare(query);
    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);

    List<Collection> resultCollections = entitiesToCollections(results);     // Retrieve and convert Entities
    Cursor cursor = results.getCursor();              // Where to start next time
    if (cursor != null && resultCollections.size() == 10) {         // Are we paging? Save Cursor
      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
      return new Result<>(resultCollections, cursorString);
    } else {
      return new Result<>(resultCollections);
    }
  }
}

