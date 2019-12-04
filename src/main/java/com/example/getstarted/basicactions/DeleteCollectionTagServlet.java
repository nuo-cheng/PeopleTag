package com.example.getstarted.basicactions;

import com.example.getstarted.daos.PCAssocDao;
import com.example.getstarted.daos.PCAssocDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * delete collection
 */
@WebServlet(name = "DeleteCollectionTagServlet")
public class DeleteCollectionTagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * delete collection tag
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long collectionId=Long.decode(request.getParameter("collectionid"));
        Long postId=Long.decode(request.getParameter("postid"));
        PCAssocDao assocDao=new PCAssocDaoImplement();
        Long assocId=assocDao.getAssocId(collectionId,postId);
        try {
            assocDao.deletePCAssoc(assocId);
        }catch (Exception e){
            throw new ServletException("Error delete collection tag", e);
        }
        response.sendRedirect("/readpost?postid="+postId);
    }
}
