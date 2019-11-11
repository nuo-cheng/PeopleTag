package com.example.getstarted.basicactions;

import com.example.getstarted.daos.AssocDao;
import com.example.getstarted.daos.CollectionDao;
import com.example.getstarted.daos.DatastoreDao;
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

@WebServlet(name = "MoveFromCollectionServlet")
public class MoveFromCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long collectionId=Long.decode(request.getParameter("id"));
        AssocDao dao = (AssocDao) this.getServletContext().getAttribute("assocdao");
        CollectionDao collectiondao = (CollectionDao) this.getServletContext().getAttribute("collectiondao");
        DatastoreDao personDao=(DatastoreDao) this.getServletContext().getAttribute("dao");
        String startCursor = request.getParameter("cursor");
        List<Long> personIds = null;
        String endCursor = null;
        try {
            personIds = dao.readPersons(collectionId, startCursor).result;
//            persons = result.result;
//            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }

        List<Person> persons=new ArrayList<>();
        try{
            for(int i=0;i<personIds.size();i++){
                Long id=personIds.get(i);
                Person person=personDao.readPerson(id);
                persons.add(person);
            }
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }

        request.getSession().getServletContext().setAttribute("persons", persons);
        request.getSession().getServletContext().setAttribute("moveFromCollectionId",collectionId);
        request.getSession().setAttribute("page","movefromcollection");
        request.getRequestDispatcher("/base.jsp").forward(request,response);
    }
}
