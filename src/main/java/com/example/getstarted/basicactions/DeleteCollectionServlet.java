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
import com.example.getstarted.daos.PCAssocDao;
import com.example.getstarted.daos.PCAssocDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// [START example]

/**
 * delete collection
 */
@SuppressWarnings("serial")
public class DeleteCollectionServlet extends HttpServlet {

  /**
   * do get
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    Long id = Long.decode(req.getParameter("id"));
    CollectionDao collectiondaodao = (CollectionDao) this.getServletContext().getAttribute("collectiondao");
    AssocDao assocDao=(AssocDao)this.getServletContext().getAttribute("assocdao");
    PCAssocDao pcAssocDao=new PCAssocDaoImplement();
    try {
      //delete related assocs
      List<Long> assocIds=assocDao.getAssocIdsFromCollectionId(id);
      if(!assocIds.isEmpty()){
        for(int i=0;i<assocIds.size();i++){
          assocDao.deleteAssoc(assocIds.get(i));
        }
      }

      List<Long> pcAssocIds=pcAssocDao.getPCAssocIdsFromCollectionId(id);
      if(!pcAssocIds.isEmpty()){
        for(int i=0;i<pcAssocIds.size();i++){
          pcAssocDao.deletePCAssoc(pcAssocIds.get(i));
        }
      }

      //delete collection
      collectiondaodao.deleteCollection(id);
      resp.sendRedirect("/collections");
    } catch (Exception e) {
      throw new ServletException("Error deleting person", e);
    }
  }
}
// [END example]
