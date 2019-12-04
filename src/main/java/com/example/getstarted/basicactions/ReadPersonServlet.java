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

package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Post;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * read person
 */
@SuppressWarnings("serial")
public class ReadPersonServlet extends HttpServlet {

  /**
   * do get
   * @param req
   * @param resp
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
    Long id = Long.decode(req.getParameter("id"));
    String startCursor = req.getParameter("cursor");
    String postStartCursor = req.getParameter("postcursor");
    PersonDao dao = (PersonDao) this.getServletContext().getAttribute("dao");
    AssocDao assocDao=(AssocDao) this.getServletContext().getAttribute("assocdao");
    CollectionDao collectionDao=(CollectionDao) this.getServletContext().getAttribute("collectiondao");
    PostDao postDao = (PostDao) this.getServletContext().getAttribute("postdao");
    PPAssocDao ppAssocDao=new PPAssocDaoImplement();
    PCAssocDao pcAssocDao=new PCAssocDaoImplement();
    List<Collection> collections=new ArrayList<>();
    List<Long> collectionIds;
    List<Long> postIds;
    List<Post> posts = new ArrayList<>();
    String endCursor;
    String postEndCursor;
    Map<Long,List> personTagmap=new HashMap<>();
    Map<Long,List> collectionTagmap=new HashMap<>();
    try {
      Person person = dao.readPerson(id);
      Result<Long> collectionIdsAndCursor=assocDao.readCollections(id,startCursor);
      collectionIds=collectionIdsAndCursor.result;
      endCursor=collectionIdsAndCursor.cursor;
      for(int i=0;i<collectionIds.size();i++){
        Long collectionId=collectionIds.get(i);
        Collection collection=collectionDao.readCollection(collectionId);
        collections.add(collection);
      }

      Result<Long> postIdsAndCursor= ppAssocDao.readPosts(id, postStartCursor);
      postIds = postIdsAndCursor.result;
      postEndCursor = postIdsAndCursor.cursor;
      for (int i = 0; i < postIds.size(); i++){
        Long postId = postIds.get(i);
        Post post = postDao.readPost(postId);
        Result<Long> personTagIdsAndCursor=ppAssocDao.readPersons(postId,null);
        List<Long> personTagIds=personTagIdsAndCursor.result;
        List<Person> personTags=new ArrayList<>();
        for(int j=0;j<personTagIds.size();j++){
          Person personTag=dao.readPerson(personTagIds.get(j));
          personTags.add(personTag);
        }
        Result<Long> collectionTagIdsAndCursor=pcAssocDao.readCollections(postId,null);
        List<Long> collectionTagIds=collectionTagIdsAndCursor.result;
        List<Collection> collectionTags=new ArrayList<>();
        for(int j=0;j<collectionTagIds.size();j++){
          Collection collectionTag=collectionDao.readCollection(collectionTagIds.get(j));
          collectionTags.add(collectionTag);
        }
        collectionTagmap.put(postId,collectionTags);
        personTagmap.put(postId,personTags);
        posts.add(post);
      }

      req.setAttribute("person", person);
      req.setAttribute("page", "view");
      req.getSession().getServletContext().setAttribute("collectionsofperson",collections);
      req.getSession().getServletContext().setAttribute("postsofperson", posts);
      req.getSession().getServletContext().setAttribute("persontagmap", personTagmap);
      req.getSession().getServletContext().setAttribute("collectiontagmap", collectionTagmap);
      req.setAttribute("cursor",endCursor);
      req.setAttribute("postcursor", postEndCursor);
      req.getRequestDispatcher("/base.jsp").forward(req, resp);
    } catch (Exception e) {
      throw new ServletException("Error reading person", e);
    }
  }
}
// [END example]
