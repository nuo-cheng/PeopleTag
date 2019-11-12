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

import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultIterator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// [START example]
/**
 * methods of communicate Person object with google datastore
 * CRUD
 */
public class DatastoreDao implements PersonDao {

  // [START constructor]
  private DatastoreService datastore;
  private static final String PERSON_KIND = "Person4";

  /**
   * prepare datastore
   */
  public DatastoreDao() {
    datastore = DatastoreServiceFactory.getDatastoreService(); // Lastized Datastore service
  }
  // [END constructor]

  /**
   * entity to person
   * @param entity
   * @return
   */
  public Person entityToPerson(Entity entity) {
    return new Person.Builder()                                     // Convert to Person form
        .last((String) entity.getProperty(Person.LAST))
        .description((String) entity.getProperty(Person.DESCRIPTION))
        .id(entity.getKey().getId())
        .imageUrl((String) entity.getProperty(Person.IMAGE_URL))
        .createdBy((String) entity.getProperty(Person.CREATED_BY))
        .createdById((String) entity.getProperty(Person.CREATED_BY_ID))
        .first((String) entity.getProperty(Person.FIRST))
        .gender((String) entity.getProperty(Person.GENDER))
        .jobTitle((String) entity.getProperty(Person.JOB_TITLE))
        .interest((String) entity.getProperty(Person.INTEREST))
        .build();
  }

  /**
   * add person
   * @param person
   * @return
   * @throws SQLException
   */
  @Override
  public Long createPerson(Person person) {
    Entity incPersonEntity = new Entity(PERSON_KIND);  // Key will be assigned once written
    incPersonEntity.setProperty(Person.LAST, person.getLast());
    incPersonEntity.setProperty(Person.DESCRIPTION, person.getDescription());
    incPersonEntity.setProperty(Person.FIRST, person.getFirst());
    incPersonEntity.setProperty(Person.IMAGE_URL, person.getImageUrl());
    incPersonEntity.setProperty(Person.CREATED_BY, person.getCreatedBy());
    incPersonEntity.setProperty(Person.CREATED_BY_ID, person.getCreatedById());
    incPersonEntity.setProperty(Person.GENDER, person.getGender());
    incPersonEntity.setProperty(Person.JOB_TITLE, person.getJobTitle());
    incPersonEntity.setProperty(Person.INTEREST, person.getInterest());

    Key personKey = datastore.put(incPersonEntity); // Save the Entity
    return personKey.getId();                     // The ID of the Key
  }

  /**
   * read person
   * @param personId
   * @return
   * @throws SQLException
   */
  @Override
  public Person readPerson(Long personId) {
    try {
      Entity personEntity = datastore.get(KeyFactory.createKey(PERSON_KIND, personId));
      return entityToPerson(personEntity);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  /**
   * update person
   * @param person
   * @throws SQLException
   */
  @Override
  public void updatePerson(Person person) {
    Key key = KeyFactory.createKey(PERSON_KIND, person.getId());  // From a person, create a Key
    Entity entity = new Entity(key);         // Convert Person to an Entity
    entity.setProperty(Person.LAST, person.getLast());
    entity.setProperty(Person.DESCRIPTION, person.getDescription());
    entity.setProperty(Person.FIRST, person.getFirst());
    entity.setProperty(Person.IMAGE_URL, person.getImageUrl());
    entity.setProperty(Person.CREATED_BY, person.getCreatedBy());
    entity.setProperty(Person.CREATED_BY_ID, person.getCreatedById());
    entity.setProperty(Person.GENDER, person.getGender());
    entity.setProperty(Person.JOB_TITLE, person.getJobTitle());
    entity.setProperty(Person.INTEREST, person.getInterest());

    datastore.put(entity);                   // Update the Entity
  }

  /**
   * delete person
   * @param personId
   * @throws SQLException
   */
  @Override
  public void deletePerson(Long personId) {
    Key key = KeyFactory.createKey(PERSON_KIND, personId);        // Create the Key
    datastore.delete(key);                      // Delete the Entity
  }

  /**
   * entities to persons
   * @param results
   * @return
   */
  public List<Person> entitiesToPersons(Iterator<Entity> results) {
    List<Person> resultPersons = new ArrayList<>();
    while (results.hasNext()) {  // We still have data
      resultPersons.add(entityToPerson(results.next()));      // Add the Person to the List
    }
    return resultPersons;
  }

  /**
   * list all persons
   * @param startCursorString
   * @return
   * @throws SQLException
   */
  @Override
  public Result<Person> listPersons(String startCursorString) {
    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
    if (startCursorString != null && !startCursorString.equals("")) {
      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
    }
    Query query = new Query(PERSON_KIND) // We only care about Persons
        .addSort(Person.FIRST, SortDirection.ASCENDING); // Use default Index "first"
    PreparedQuery preparedQuery = datastore.prepare(query);
    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
    List<Person> resultPersons = entitiesToPersons(results);     // Retrieve and convert Entities
    Cursor cursor = results.getCursor();              // Where to start next time
    if (cursor != null && resultPersons.size() == 9) {         // Are we paging? Save Cursor
      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
      return new Result<>(resultPersons, cursorString);
    } else {
      return new Result<>(resultPersons);
    }
  }

  /**
   * list all persons added by a specify user
   * @param userId
   * @param startCursorString
   * @return
   * @throws SQLException
   */
  @Override
  public Result<Person> listPersonsByUser(String userId, String startCursorString) {
    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
    if (startCursorString != null && !startCursorString.equals("")) {
      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
    }
    Query query = new Query(PERSON_KIND) // We only care about Persons
        // Only for this user
        .setFilter(new Query.FilterPredicate(
            Person.CREATED_BY_ID, Query.FilterOperator.EQUAL, userId))
        // a custom datastore index is required since you are filtering by one property
        // but ordering by another
        .addSort(Person.LAST, SortDirection.ASCENDING);
    PreparedQuery preparedQuery = datastore.prepare(query);
    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
    List<Person> resultPersons = entitiesToPersons(results);     // Retrieve and convert Entities
    Cursor cursor = results.getCursor();              // Where to start next time
    if (cursor != null && resultPersons.size() == 9) {         // Are we paging? Save Cursor
      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
      return new Result<>(resultPersons, cursorString);
    } else {
      return new Result<>(resultPersons);
    }
  }
}
