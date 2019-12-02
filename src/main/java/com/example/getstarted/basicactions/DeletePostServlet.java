package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postid"));
        PostDao postDao = (PostDao) this.getServletContext().getAttribute("postdao");
//        PostDao postDao=new PostDaoImplement();
        PPAssocDao ppAssocDao=new PPAssocDaoImplement();
        PCAssocDao pcAssocDao=new PCAssocDaoImplement();
        try {
            List<Long> ppAssocIds=ppAssocDao.getPPAssocIdsFromPostId(postId);
            if(!ppAssocIds.isEmpty()){
                for(int i=0;i<ppAssocIds.size();i++){
                    ppAssocDao.deletePPAssoc(ppAssocIds.get(i));
                }
            }
            List<Long> pcAssocIds=pcAssocDao.getPCAssocIdsFromPostId(postId);
            if(!pcAssocIds.isEmpty()){
                for (int i=0;i<pcAssocIds.size();i++){
                    pcAssocDao.deletePCAssoc(pcAssocIds.get(i));
                }
            }
            postDao.deletePost(postId);
            response.sendRedirect("/post");
        }catch (Exception e){
            throw new ServletException("Error deleting Post");
        }
    }
}
