package com.example.getstarted.basicactions;

import com.example.getstarted.daos.AssocDao;
import com.example.getstarted.daos.AssocDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FinishMoveFromCollectionServlet")
public class FinishMoveFromCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long personId=Long.decode(request.getParameter("personid"));
        Long collectionId=(Long)request.getSession().getServletContext().getAttribute("moveFromCollectionId");
//        System.out.println("personId: " + personId);
//        System.out.println("collectionId: "+ collectionId);
        AssocDao assocDao=(AssocDao) this.getServletContext().getAttribute("assocdao");
        Long assocId = assocDao.getAssocId(personId, collectionId);
//        System.out.println("=============" + assocId);
        try {
            assocDao.deleteAssoc(assocId);
        }catch (Exception e){
            throw new ServletException("Error moving person to collection", e);
        }
        response.sendRedirect("/readcollection?collectionid="+collectionId);

    }
}
