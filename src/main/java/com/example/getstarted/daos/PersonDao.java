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

import java.sql.SQLException;

// [START example]

/**
 * methods of communicate Person object with google datastore
 * CRUD
 *  * concrete in DatastoreDao
 */
public interface PersonDao {
  /**
   * add person
   * @param person
   * @return
   * @throws SQLException
   */
  Long createPerson(Person person) throws SQLException;

  /**
   * read person
   * @param personId
   * @return
   * @throws SQLException
   */
  Person readPerson(Long personId) throws SQLException;

  /**
   * update person
   * @param person
   * @throws SQLException
   */
  void updatePerson(Person person) throws SQLException;

  /**
   * delete person
   * @param personId
   * @throws SQLException
   */
  void deletePerson(Long personId) throws SQLException;

  /**
   * list all persons
   * @param startCursor
   * @return
   * @throws SQLException
   */
  Result<Person> listPersons(String startCursor) throws SQLException;

  /**
   * list all persons added by a specify user
   * @param userId
   * @param startCursor
   * @return
   * @throws SQLException
   */
  Result<Person> listPersonsByUser(String userId, String startCursor) throws SQLException;
}
// [END example]
