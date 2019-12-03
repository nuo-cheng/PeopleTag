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

@WebServlet(name = "AddCollectionTagServlet")
public class AddCollectionTagServlet extends HttpServlet {
    /**
     * after submit navigate to here
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] collectionIds=request.getParameterValues("collectionid");
        Long postId=(Long)request.getSession().getServletContext().getAttribute("postidtoadd");
        if(collectionIds==null||collectionIds.length==0){
            response.sendRedirect("/addcollectiontag?postid="+postId);
        }else {
            PCAssocDao pcAssocDao=new PCAssocDaoImplement();
            try {
                for(int i=0;i<collectionIds.length;i++){
                    Long collectionId=Long.decode(collectionIds[i]);
                    if(!pcAssocDao.isAlreadyIn(postId,collectionId)){
                        pcAssocDao.createPCAssoc(collectionId,postId);
                    }
                }
                response.sendRedirect("/readpost?postid="+postId);
            }catch (Exception e){
                throw new ServletException("Error adding collection tag", e);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postid"));
        CollectionDao collectionDao=(CollectionDaoImplement)this.getServletContext().getAttribute("collectiondao");
        String startCursor=request.getParameter("cursor");
        List<Collection> collections = null;
        String endCursor = null;
        try {
            Result<Collection> result = collectionDao.listCollections(startCursor);
            collections = result.result;
            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        request.getSession().getServletContext().setAttribute("collections", collections);
        request.getSession().getServletContext().setAttribute("postidtoadd",postId);
        request.setAttribute("cursor", endCursor);
        request.getSession().setAttribute("page","addcollectiontag");
        request.getRequestDispatcher("/base.jsp").forward(request,response);


    }
}
