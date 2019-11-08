/*
 * Copyright 2016 Google Inc.
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

import com.example.getstarted.daos.CollectionDao;
import com.example.getstarted.daos.PersonDao;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// [START example]
@SuppressWarnings("serial")
public class ListCollectionByUserServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
        ServletException {
    CollectionDao dao = (CollectionDao) this.getServletContext().getAttribute("dao");
    String startCursor = req.getParameter("cursor");
    List<Collection> collections = null;
    String endCursor = null;
    try {
      Result<Collection> result =
          dao.listCollectionsByUser((String) req.getSession().getAttribute("userId"), startCursor);
      collections = result.result;
      endCursor = result.cursor;
    } catch (Exception e) {
      throw new ServletException("Error listing collections", e);
    }
    req.getSession().getServletContext().setAttribute("collections", collections);
    StringBuilder collectionNames = new StringBuilder();
    for (Collection collection : collections) {
      collectionNames.append(collection.getCollectionName() + " ");
    }
    req.getSession().setAttribute("cursor", endCursor);
    req.getSession().setAttribute("page", "list");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}
// [END example]
