package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;
import com.example.getstarted.objects.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * read post
 */
@WebServlet(name = "ReadPostServlet")
public class ReadPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    /**
     * read post
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postid"));
        PostDao postDao=new PostDaoImplement();
        CollectionDao collectionDao=new CollectionDaoImplement();
        PersonDao personDao=new DatastoreDao();
        PPAssocDao ppAssocDao=new PPAssocDaoImplement();
        PCAssocDao pcAssocDao=new PCAssocDaoImplement();
        ComPostAssocDao comPostAssocDao=new ComPostAssocDaoImplement();
        CommentDao commentDao=new CommentDaoImplement();
        List<Collection> collections=new ArrayList<>();
        List<Long> collectionIds;
        List<Person> persons=new ArrayList<>();
        List<Long> personIds;
        List<Long> commentIds;
        List<Comment> comments=new ArrayList<>();
        try {
            Post post=postDao.readPost(postId);
            Result<Long> personIdsAndCursor=ppAssocDao.readPersons(postId,null);
            personIds=personIdsAndCursor.result;
            for(int i=0;i<personIds.size();i++){
                Long personId=personIds.get(i);
                Person person=personDao.readPerson(personId);
                persons.add(person);
            }
            Result<Long> collectionIdsAndCursor=pcAssocDao.readCollections(postId,null);
            collectionIds=collectionIdsAndCursor.result;
            for(int i=0;i<collectionIds.size();i++) {
                Long collectionId = collectionIds.get(i);
                Collection collection = collectionDao.readCollection(collectionId);
                collections.add(collection);
            }
            Result<Long> commentIdsAndCursor=comPostAssocDao.readComments(postId,null);
            commentIds=commentIdsAndCursor.result;
            for(int i=0;i<commentIds.size();i++) {
                Long commentId = commentIds.get(i);
                Comment comment  = commentDao.readComment(commentId);
                comments.add(comment);
            }
            request.setAttribute("post",post);
            request.getSession().getServletContext().setAttribute("persontags",persons);
            request.getSession().getServletContext().setAttribute("collectiontags",collections);
            request.getSession().getServletContext().setAttribute("comments",comments);
            request.setAttribute("page","postview");
            request.getRequestDispatcher("/base.jsp").forward(request,response);
        }catch (Exception e){
            throw new ServletException("Error reading post", e);
        }
    }
}
