package com.example.getstarted.basicactions;

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
import java.util.List;

/**
 * Add person  to collection
 */
@WebServlet(name = "AddToCollectionFromCollectionServlet")
public class AddToCollectionFromCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    /**
     * doget
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long collectionId=Long.decode(request.getParameter("collectionid"));
        DatastoreDao dao = (DatastoreDao) this.getServletContext().getAttribute("dao");
        String startCursor = request.getParameter("cursor");
        List<Person> personList = null;
        String endCursor = null;
        try {
            Result<Person> result = dao.listPersons(startCursor);
            personList = result.result;
            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        request.getSession().getServletContext().setAttribute("personlist", personList);
        request.getSession().getServletContext().setAttribute("collectionidtoadd",collectionId);
        request.setAttribute("cursor", endCursor);
        request.getSession().setAttribute("page","addtocollectionfromcollection");
        request.getRequestDispatcher("/base.jsp").forward(request,response);
    }
}
