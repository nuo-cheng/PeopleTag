
package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;

import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * read collection
 */
public class ReadCollectionServlet extends HttpServlet {

    /**
     * do get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startCursor = request.getParameter("cursor");
        Long collectionId=Long.decode(request.getParameter("collectionid"));
        AssocDao assocDao=(AssocDao) this.getServletContext().getAttribute("assocdao");
        DatastoreDao dao=(DatastoreDao) this.getServletContext().getAttribute("dao");
        CollectionDao collectionDao=(CollectionDao) this.getServletContext().getAttribute("collectiondao");
        Collection collection;
        List<Long> personIds;
        List<Person> persons=new ArrayList<>();
        String endCursor = null;
        try{
            collection=collectionDao.readCollection(collectionId);
            Result<Long> personIdsAndCursor=assocDao.readPersons(collectionId,startCursor);
            personIds=personIdsAndCursor.result;
            endCursor=personIdsAndCursor.cursor;
            for(int i=0;i<personIds.size();i++){
                Long id=personIds.get(i);
                Person person=dao.readPerson(id);
                persons.add(person);
            }
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        request.setAttribute("collection",collection);
        request.getSession().getServletContext().setAttribute("persons", persons);
        request.setAttribute("cursor", endCursor);
        request.getSession().setAttribute("page", "collectionview");
        request.getRequestDispatcher("/base.jsp").forward(request, response);
    }
}

