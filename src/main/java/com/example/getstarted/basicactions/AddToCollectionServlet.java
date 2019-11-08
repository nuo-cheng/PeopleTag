package com.example.getstarted.basicactions;

import com.example.getstarted.daos.CollectionDao;
import com.example.getstarted.daos.CollectionDaoImplement;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddToCollectionServlet")
public class AddToCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long personId=Long.decode(request.getParameter("id"));
        CollectionDao dao = new CollectionDaoImplement();
        String startCursor = request.getParameter("cursor");
        List<Collection> collections = null;
        String endCursor = null;
        try {
            Result<Collection> result = dao.listCollections(startCursor);
            collections = result.result;
            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        request.getSession().getServletContext().setAttribute("collections", collections);
        request.getSession().setAttribute("personid",personId);
        request.getSession().setAttribute("page","addtocollection");
        request.getRequestDispatcher("/base.jsp").forward(request,response);
    }
}
