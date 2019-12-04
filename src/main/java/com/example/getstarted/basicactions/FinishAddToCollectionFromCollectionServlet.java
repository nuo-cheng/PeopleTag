package com.example.getstarted.basicactions;

import com.example.getstarted.daos.AssocDao;
import com.example.getstarted.daos.AssocDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * finish add to collection
 */
@WebServlet(name = "FinishAddToCollectionFromCollectionServlet")
public class FinishAddToCollectionFromCollectionServlet extends HttpServlet {
    /**
     * do post
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * do get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] personIds=request.getParameterValues("personid");
        Long collectionId=(Long)request.getSession().getServletContext().getAttribute("collectionidtoadd");
        if(personIds==null||personIds.length==0){
            response.sendRedirect("/addtocollectionfromcollection?collectionid="+collectionId);
        }else {
            AssocDao assocDao = new AssocDaoImplement();
            try {
                for (int i = 0; i < personIds.length; i++) {
                    Long personId = Long.decode(personIds[i]);
                    if (!assocDao.isAlreadyIn(personId, collectionId)) {
                        assocDao.createAssoc(personId, collectionId);
                    }
                }
                response.sendRedirect("/readcollection?collectionid=" + collectionId);

            } catch (Exception e) {
                throw new ServletException("Error adding person to collection", e);
            }
        }
    }
}
