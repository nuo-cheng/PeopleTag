
package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;

import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReadCollectionServlet")
public class ReadCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long collectionId=Long.decode(request.getParameter("collectionid"));
        AssocDao assocDao=new AssocDaoImplement();
        DatastoreDao dao=new DatastoreDao();
        CollectionDao collectionDao=new CollectionDaoImplement();
        Collection collection;
        List<Long> personIds;
        List<Person> persons=new ArrayList<>();
        try{
            collection=collectionDao.readCollection(collectionId);
            //not using cursor
            personIds=assocDao.readPersons(collectionId,null);
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
        request.getSession().setAttribute("collectionview", "list");
        request.getRequestDispatcher("/base.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

