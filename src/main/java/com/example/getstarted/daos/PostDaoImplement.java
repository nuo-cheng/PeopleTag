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
import com.example.getstarted.objects.Post;
import com.example.getstarted.objects.Result;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// [START example]

/**
 * methods of communicate Person object with google datastore
 * CRUD
 */
public class PostDaoImplement implements PostDao {

  // [START constructor]
  private DatastoreService datastore;
  private static final String POST_KIND = "myPost";

  /**
   * prepare datastore
   */
  public PostDaoImplement() {
    datastore = DatastoreServiceFactory.getDatastoreService(); // Lastized Datastore service
  }
  // [END constructor]

  /**
   * entity to person
   * @param entity
   * @return
   */
  public Post entityToPost(Entity entity) {
    return new Post.Builder()                                     // Convert to Person form
        .title((String) entity.getProperty(Post.TITLE))
        .content((String) entity.getProperty(Post.CONTENT))
        .id(entity.getKey().getId())
        .imageUrl((String) entity.getProperty(Post.IMAGE_URL))
        .createdBy((String) entity.getProperty(Post.CREATED_BY))
        .createdById((String) entity.getProperty(Post.CREATED_BY_ID))

        .url1((String) entity.getProperty(Post.URL_1))
        .url2((String) entity.getProperty(Post.URL_2))
        .url3((String) entity.getProperty(Post.URL_3))
        .averageScore((Double)entity.getProperty(Post.AVERAGESCORE))
        .numOfScores((Long)entity.getProperty(Post.NUMOFSCORES))
        .build();
  }

  /**
   * add post
   * @param post
   * @return
   * @throws SQLException
   */
  @Override
  public Long createPost(Post post) {
    Entity incPostEntity = new Entity(POST_KIND);  // Key will be assigned once written
    incPostEntity.setProperty(Post.TITLE, post.getTitle());
    incPostEntity.setProperty(Post.CONTENT, post.getContent());
    incPostEntity.setProperty(Post.URL_1, post.getUrl1());
    incPostEntity.setProperty(Post.URL_2, post.getUrl2());
    incPostEntity.setProperty(Post.URL_3, post.getUrl3());
    incPostEntity.setProperty(Post.IMAGE_URL, post.getImageUrl());
    incPostEntity.setProperty(Post.CREATED_BY, post.getCreatedBy());
    incPostEntity.setProperty(Post.CREATED_BY_ID, post.getCreatedById());
    incPostEntity.setProperty(Post.AVERAGESCORE,0.0);
    incPostEntity.setProperty(Post.NUMOFSCORES,0);
    Key postKey = datastore.put(incPostEntity); // Save the Entity
    return postKey.getId();                     // The ID of the Key
  }

  /**
   * read post
   * @param postId
   * @return
   * @throws SQLException
   */
  @Override
  public Post readPost(Long postId) {
    try {
      Entity postEntity = datastore.get(KeyFactory.createKey(POST_KIND, postId));
      return entityToPost(postEntity);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  /**
   * update post
   * @param post
   * @throws SQLException
   */
  @Override
  public void updatePost(Post post) {
    Key key = KeyFactory.createKey(POST_KIND, post.getId());  // From a person, create a Key
    Entity entity = new Entity(key);         // Convert Person to an Entity
    entity.setProperty(Post.TITLE, post.getTitle());
    entity.setProperty(Post.CONTENT, post.getContent());
    entity.setProperty(Post.URL_1, post.getUrl1());
    entity.setProperty(Post.URL_2, post.getUrl2());
    entity.setProperty(Post.URL_3, post.getUrl3());
    entity.setProperty(Post.IMAGE_URL, post.getImageUrl());
    entity.setProperty(Post.CREATED_BY, post.getCreatedBy());
    entity.setProperty(Post.CREATED_BY_ID, post.getCreatedById());
    entity.setProperty(Post.NUMOFSCORES,post.getNumOfScores());
    entity.setProperty(Post.AVERAGESCORE,post.getAverageScore());
    datastore.put(entity);                   // Update the Entity
  }

  /**
   * delete post
   * @param postId
   * @throws SQLException
   */
  @Override
  public void deletePost(Long postId) {
    Key key = KeyFactory.createKey(POST_KIND, postId);        // Create the Key
    datastore.delete(key);                      // Delete the Entity
  }

  /**
   * entities to post
   * @param results
   * @return
   */
  public List<Post> entitiesToPost(Iterator<Entity> results) {
    List<Post> resultPost = new ArrayList<>();
    while (results.hasNext()) {  // We still have data
      resultPost.add(entityToPost(results.next()));      // Add the Person to the List
    }
    return resultPost;
  }

  /**
   * list all persons
   * @param startCursorString
   * @return
   * @throws SQLException
   */
  @Override
  public Result<Post> listPost(String startCursorString) {
    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
    if (startCursorString != null && !startCursorString.equals("")) {
      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
    }
    Query query = new Query(POST_KIND) // We only care about Persons
        .addSort(Post.TITLE, SortDirection.ASCENDING); // Use default Index "first"
    PreparedQuery preparedQuery = datastore.prepare(query);
    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
    List<Post> resultPost = entitiesToPost(results);     // Retrieve and convert Entities
    Cursor cursor = results.getCursor();              // Where to start next time
    if (cursor != null && resultPost.size() == 9) {         // Are we paging? Save Cursor
      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
      return new Result<>(resultPost, cursorString);
    } else {
      return new Result<>(resultPost);
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
  public Result<Post> listPostByUser(String userId, String startCursorString) {
    FetchOptions fetchOptions = FetchOptions.Builder.withLimit(9); // Only show 10 at a time
    if (startCursorString != null && !startCursorString.equals("")) {
      fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
    }
    Query query = new Query(POST_KIND) // We only care about Persons
        // Only for this user
        .setFilter(new Query.FilterPredicate(
            Post.CREATED_BY_ID, Query.FilterOperator.EQUAL, userId))
        // a custom datastore index is required since you are filtering by one property
        // but ordering by another
        .addSort(Post.TITLE, SortDirection.ASCENDING);
    PreparedQuery preparedQuery = datastore.prepare(query);
    QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
    List<Post> resultPost = entitiesToPost(results);     // Retrieve and convert Entities
    Cursor cursor = results.getCursor();              // Where to start next time
    if (cursor != null && resultPost.size() == 9) {         // Are we paging? Save Cursor
      String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
      return new Result<>(resultPost, cursorString);
    } else {
      return new Result<>(resultPost);
    }
  }

}
