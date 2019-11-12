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
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] collectionIds=request.getParameterValues("collectionid");
        Long personId=(Long)request.getSession().getServletContext().getAttribute("personidtoadd");
        if(collectionIds==null||collectionIds.length==0){
            response.sendRedirect("/addtocollection?id="+personId);
        }else {
            AssocDao assocDao = new AssocDaoImplement();
            try {
                for (int i = 0; i < collectionIds.length; i++) {
                    Long collectionId = Long.decode(collectionIds[i]);
                    if (!assocDao.isAlreadyIn(personId, collectionId)) {
                        assocDao.createAssoc(personId, collectionId);
                    }
                }
                response.sendRedirect("/read?id=" + personId);

            } catch (Exception e) {
                throw new ServletException("Error adding person to collection", e);
            }
        }
    }
}
