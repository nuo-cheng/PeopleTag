package com.example.getstarted.basicactions;

import com.example.getstarted.daos.PPAssocDao;
import com.example.getstarted.daos.PPAssocDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePersonTagServlet")
public class DeletePersonTagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long personId=Long.decode(request.getParameter("personid"));
        Long postId=Long.decode(request.getParameter("postid"));
        PPAssocDao assocDao=new PPAssocDaoImplement();
        Long assocId=assocDao.getAssocId(personId,postId);
        try {
            assocDao.deletePPAssoc(assocId);
        }catch (Exception e){
            throw new ServletException("Error delete person tag", e);
        }
        response.sendRedirect("/readpost?postid="+postId);
    }
}
