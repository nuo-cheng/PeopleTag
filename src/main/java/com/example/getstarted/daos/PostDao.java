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

import java.sql.SQLException;

// [START example]

/**
 * methods of communicate Post object with google datastore
 * CRUD
 *  * concrete in DatastoreDao
 */
public interface PostDao {
  /**
   * add post
   * @param post
   * @return
   * @throws SQLException
   */
  Long createPost(Post post) throws SQLException;

  /**
   * read post
   * @param postId
   * @return
   * @throws SQLException
   */
  Post readPost(Long postId) throws SQLException;

  /**
   * update post
   * @param post
   * @throws SQLException
   */
  void updatePost(Post post) throws SQLException;

  /**
   * delete post
   * @param postId
   * @throws SQLException
   */
  void deletePost(Long postId) throws SQLException;

  /**
   * list all post
   * @param startCursor
   * @return
   * @throws SQLException
   */
  Result<Post> listPost(String startCursor) throws SQLException;

  /**
   * list all persons added by a specify user
   * @param userId
   * @param startCursor
   * @return
   * @throws SQLException
   */
  Result<Post> listPostByUser(String userId, String startCursor) throws SQLException;

}
// [END example]
