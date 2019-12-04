package com.example.getstarted.basicactions;

import com.example.getstarted.daos.ComPostAssocDao;
import com.example.getstarted.daos.ComPostAssocDaoImplement;
import com.example.getstarted.daos.CommentDao;
import com.example.getstarted.daos.CommentDaoImplement;
import com.example.getstarted.objects.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "CreateCommentServlet")
public class CreateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postid"));
        String content=request.getParameter("content");
        Date date=new Date();
        String createdBy="";
        String createdById="";
        HttpSession session = request.getSession();
        if (session.getAttribute("userEmail") != null) { // Does the user have a logged in session?
            createdBy = (String) session.getAttribute("userEmail");
            createdById = (String) session.getAttribute("userId");
        }
        Comment comment=new Comment.Builder()
                .content(content)
                .createdBy(createdBy)
                .createdById(createdById)
                .timeCreated(date)
                .build();
        CommentDao commentDao=new CommentDaoImplement();
        ComPostAssocDao comPostAssocDao=new ComPostAssocDaoImplement();
        try {
            Long id=commentDao.createComment(comment);
            comPostAssocDao.createComPostAssoc(id,postId);
            response.sendRedirect("/readpost?postid="+postId);
        }catch (Exception e) {
            throw new ServletException("Error creating comment", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postid"));
        request.setAttribute("action","Create");
        request.setAttribute("destination","createcomment?postid="+postId);
        request.setAttribute("page", "commentform");           // Tells base.jsp to include form.jsp
        request.getRequestDispatcher("/base.jsp").forward(request, response);
    }
}
