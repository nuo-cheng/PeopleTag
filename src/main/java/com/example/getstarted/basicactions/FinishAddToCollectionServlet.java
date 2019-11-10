package com.example.getstarted.basicactions;

import com.example.getstarted.daos.AssocDao;
import com.example.getstarted.daos.AssocDaoImplement;
import com.example.getstarted.objects.Assoc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FinishAddToCollectionServlet")
public class FinishAddToCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long collectionId=Long.decode(request.getParameter("collectionid"));
        Long personId=(Long)request.getSession().getServletContext().getAttribute("personidtoadd");
        AssocDao assocDao=new AssocDaoImplement();
        try {
            assocDao.createAssoc(personId, collectionId);
            response.sendRedirect("/readcollection?collectionid="+collectionId);
        }catch (Exception e){
            throw new ServletException("Error adding person to collection", e);
        }

    }
}
