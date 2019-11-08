package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListCollectionServlet")
public class ListCollectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CollectionDao dao = new CollectionDaoImplement();
        String startCursor = req.getParameter("cursor");
        List<Collection> collections = null;
        String endCursor = null;
        try {
            Result<Collection> result = dao.listCollections(startCursor);
            collections = result.result;
            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        req.getSession().getServletContext().setAttribute("collections", collections);
        req.setAttribute("cursor", endCursor);
        req.setAttribute("page", "collectionslist");
        req.getRequestDispatcher("/base.jsp").forward(req, resp);
    }
}
