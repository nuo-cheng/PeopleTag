
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

import java.sql.SQLException;


// [START example]
/**
 * methods of communicate Collection object with google datastore
 * CRUD
 *  * concrete in CollectionDaoImplement
 */
public interface CollectionDao {

  /**
   * add collection
   * @param collection
   * @return
   * @throws SQLException
   */
  Long createCollection(Collection collection) throws SQLException;

  /**
   * read collection
   * @param collectionId
   * @return
   * @throws SQLException
   */
  Collection readCollection(Long collectionId) throws SQLException;

  /**
   * update collection
   * @param collection
   * @throws SQLException
   */
  void updateCollection(Collection collection) throws SQLException;

  /**
   * delete collection
   * @param collectionId
   * @throws SQLException
   */
  void deleteCollection(Long collectionId) throws SQLException;

  /**
   * list all collections
   * @param startCursor
   * @return
   * @throws SQLException
   */
  Result<Collection> listCollections(String startCursor) throws SQLException;

  /**
   * list all collections created by a specific user
   * @param userId
   * @param startCursor
   * @return
   * @throws SQLException
   */
  Result<Collection> listCollectionsByUser(String userId, String startCursor) throws SQLException;
}
// [END example]

