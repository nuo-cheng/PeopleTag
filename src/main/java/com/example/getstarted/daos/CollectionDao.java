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
public interface CollectionDao {
  Long createCollection(Collection collection) throws SQLException;

  Collection readCollection(Long collectionId) throws SQLException;

  void updateCollection(Collection collection) throws SQLException;

  void deleteCollection(Long collectionId) throws SQLException;

  Result<Collection> listCollections(String startCursor) throws SQLException;

//  Result<Person> listPersonOfCollection(String startCursor) throws SQLException;

  Result<Collection> listCollectionsByUser(String userId, String startCursor) throws SQLException;
}
// [END example]
