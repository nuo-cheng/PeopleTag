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

import com.example.getstarted.daos.AssocDao;
import com.example.getstarted.daos.CollectionDao;
import com.example.getstarted.daos.PersonDao;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class ReadPersonServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
    Long id = Long.decode(req.getParameter("id"));
    PersonDao dao = (PersonDao) this.getServletContext().getAttribute("dao");
    AssocDao assocDao=(AssocDao) this.getServletContext().getAttribute("assocdao");
    CollectionDao collectionDao=(CollectionDao) this.getServletContext().getAttribute("collectiondao");
    List<Collection> collections=new ArrayList<>();
    List<Long> collectionIds;
    try {
      Person person = dao.readPerson(id);
      collectionIds=assocDao.readCollections(id,null);
      System.out.println(collectionIds);
      for(int i=0;i<collectionIds.size();i++){
        Long collectionId=collectionIds.get(i);
        Collection collection=collectionDao.readCollection(collectionId);
        collections.add(collection);
      }
      System.out.println(collections);
      req.setAttribute("person", person);
      req.setAttribute("page", "view");
      req.getSession().getServletContext().setAttribute("collectionsofperson",collections);
      req.getRequestDispatcher("/base.jsp").forward(req, resp);

    } catch (Exception e) {
      throw new ServletException("Error reading person", e);
    }
  }
}
// [END example]
