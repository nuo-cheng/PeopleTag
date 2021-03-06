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
import com.example.getstarted.daos.PPAssocDao;
import com.example.getstarted.daos.PPAssocDaoImplement;
import com.example.getstarted.daos.PersonDao;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.PostPersonAssoc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * delete person
 */
@SuppressWarnings("serial")
public class DeletePersonServlet extends HttpServlet {

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
    PersonDao dao = (PersonDao) this.getServletContext().getAttribute("dao");
    AssocDao assocDao=(AssocDao)this.getServletContext().getAttribute("assocdao");
    PPAssocDao ppAssocDao=new PPAssocDaoImplement();
    try {
      List<Long> assocIds=assocDao.getAssocIdsFromPersonId(id);
      if(!assocIds.isEmpty()){
        for(int i=0;i<assocIds.size();i++){
          assocDao.deleteAssoc(assocIds.get(i));
        }
      }
      List<Long> ppAssocIds=ppAssocDao.getPPAssocIdsFromPersonId(id);
      if(!ppAssocIds.isEmpty()){
        for(int i=0;i<ppAssocIds.size();i++){
          ppAssocDao.deletePPAssoc(ppAssocIds.get(i));
        }
      }
      dao.deletePerson(id);
      resp.sendRedirect("/person");
    } catch (Exception e) {
      throw new ServletException("Error deleting person", e);
    }
  }
}
// [END example]
